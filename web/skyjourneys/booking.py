# coding: utf-8
from flask import (
    Blueprint, flash, g, redirect, render_template, request, session, url_for, jsonify
)
from werkzeug.exceptions import abort

import boto3
client = boto3.client('sns')

import random
import json
import requests
import time
import datetime

import os

from skyjourneys.auth import login_required
from . import connector

bp = Blueprint('booking', __name__)

SITE_ROOT = os.path.realpath(os.path.dirname(__file__))
json_url = os.path.join(SITE_ROOT, "locations.json")
locations = json.load(open(json_url))['Cities']

@bp.route('/start', methods=('GET', 'POST'))
def start():
    initialValues = {
        'from': '',
        'to': '',
        'depart': (datetime.datetime.now() + datetime.timedelta(days=2)).strftime('%Y-%m-%d'),
        'returnbound': (datetime.datetime.now() + datetime.timedelta(days=4)).strftime('%Y-%m-%d'),
        'adults': '1',
        'children': '0',
        'returnReturn': 'checked',
        'returnOneWay': '',
    }

    if request.method == 'POST':
        initialValues = {
            'from': request.form.get('from', ''),
            'to': request.form.get('to', ''),
            'depart': request.form.get('depart', (datetime.datetime.now() + datetime.timedelta(days=2)).strftime('%Y-%m-%d')),
            'returnbound': request.form.get('returnbound', (datetime.datetime.now() + datetime.timedelta(days=4)).strftime('%Y-%m-%d')),
            'adults': request.form.get('adults', '1'),
            'children': request.form.get('children', '0'),
            'returnReturn': request.form.get('returnReturn', 'checked'),
            'returnOneWay': request.form.get('returnOneWay', ''),
        }

    return render_template('booking/start.html', initialValues=initialValues)

@bp.route('/choose', methods=('GET', 'POST'))
def choose():
    formValues = {}
    if request.method == 'POST':
        g.choice = request.form.to_dict()
        formValues = {
            'from': request.form.get('from', ''),
            'to': request.form.get('to', ''),
            'depart': request.form.get('depart', (datetime.datetime.now() + datetime.timedelta(days=2)).strftime('%Y-%m-%d')),
            'returnbound': request.form.get('returnbound', (datetime.datetime.now() + datetime.timedelta(days=4)).strftime('%Y-%m-%d')),
            'adults': request.form.get('adults', '1'),
            'children': request.form.get('children', '0'),
            'returnReturn': 'checked' if request.form.get('return') == 'Return' else '',
            'returnOneWay': '' if request.form.get('return') == 'Return' else 'checked',
        }

        if formValues['from']:
            formValues['from'] = getCityIATA(formValues['from'])
        if formValues['to']:
            formValues['to'] = getCityIATA(formValues['to'])

    choiceString = formatChoice()

    accoms = connector.getAccoms()
    activities = connector.getActivity()
    return render_template('booking/choose.html', accoms=accoms, activities=activities,  choiceString=choiceString, formValues=formValues)

@bp.route('/_get_flights', methods=['POST'])
def _get_flights():
    formValues = request.form.to_dict()
    flights = connector.getFlights(formValues)
    return jsonify(flights)

def getCityIATA(city):
    for location in locations:
        if city == location['CityName']:
            return location['CityCode']
    return city


def formatChoice():
    if not hasattr(g, 'choice'):
        return ''
    
    choice = 'Showing results for '
    choice += g.choice['from']
    choice += ' to '
    choice += g.choice['to']
    choice += ' for '
    choice += g.choice['adults']
    choice += ' adults and '
    choice += g.choice['children']
    choice += ' children. Out: '
    choice += datetime.datetime.strptime(
        g.choice['depart'],
        '%Y-%m-%d'
    ).strftime('%d/%m/%Y')
    if g.choice['return'] == 'Return':
        choice += '. Return: '
        choice += datetime.datetime.strptime(
            g.choice['returnbound'],
            '%Y-%m-%d'
        ).strftime('%d/%m/%Y')
    return choice


@bp.route('/confirmation')
@login_required
def confirmation():
    client.set_sms_attributes(
        attributes = {
            'DefaultSenderID': 'SkyJourneys',
            'DefaultSMSType': 'Transactional'
        }
    )
    details = connector.getConfirmationDetails()
    response = client.publish(
        PhoneNumber = details['phoneNo'],
        Message = 'You\'re all booked!\n' \
                  'Booking Ref: ' + details['bookingID'] + '\n' \
                  'Download app: [Link]'
    )
    print(response)
    return render_template('booking/confirmation.html')

@bp.route('/location/dubrovnik')
@bp.route('/featured')
def featured():
    featured = connector.getFeaturedDetails()
    return render_template('booking/featured.html', featured=featured)
