# coding: utf-8
import functools

from flask import (
    Blueprint, flash, g, redirect, render_template, request, session, url_for
)
from werkzeug.exceptions import abort
from validate_email import validate_email

import re

from . import connector

bp = Blueprint('auth', __name__)

@bp.before_app_request
def load_logged_in_user():
    g.user = session.get('user')

@bp.route('/register', methods=('GET', 'POST'))
def register():
    if request.method == 'POST':
        firstname = request.form['firstname']
        lastname = request.form['lastname']
        email = request.form['email']
        password = request.form['password']
        error = None
        isRegistered = None

        # Validate input
        if not firstname:
            error = 'First name is required.'
        elif not lastname:
            error = 'Last name is required.'
        elif not email:
            error = 'Email is required.'
        elif not validate_email(email):
            error = 'Email is not valid.'
        elif not password:
            error = 'Password is required.'
        elif not validate_name(firstname):
            error = 'First name is not valid, can only contain alphabetic characters.'
        elif not validate_name(lastname):
            error = 'Last name is not valid, can only contain alphabetic characters.'
        elif not validate_password(password):
            error = 'Password is not valid, can only contain []'
        else:
            # Attempt to register user
            isRegistered = connector.registerUser(firstname, lastname, email, password)
            if not isRegistered['status']:
                error = isRegistered['error'] 

        if error is None:
            session.clear()
            session['user'] = connector.getUser(isRegistered['id'])
            return redirect(url_for('user.index'))
        
        flash(error)
    return render_template('auth/register.html')

def validate_password(password):
    return re.match(r"[a-zA-Z0-9:;|=,+*?<> .&@!?£#$%^*()']+", password)

def validate_name(name):
    return re.match(r"[a-zA-Z- ']+", name)

@bp.route('/login', methods=('GET', 'POST'))
def login():
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']
        error = None
        user = None

        # Validate inputs
        if not email:
            error = 'Email is required.'
        elif not validate_email(email):
            error = 'Email is not valid.'
        elif not password:
            error = 'Password is required.'
        else:
            # Attempt login and get details
            user = connector.attemptLogin(email, password)
            if isinstance(user, str):
                error = user

        if error is None:
            session.clear()
            session['user'] = connector.getUser(user['id'])
            return redirect(url_for('user.index'))

        flash(error)
    return render_template('auth/login.html')

@bp.route('/logout')
def logout():
    session.clear()
    g.user = None
    return redirect(url_for('auth.login'))

def login_required(view):
    @functools.wraps(view)
    def wrapped_view(**kwargs):
        if g.user is None:
            return redirect(url_for('auth.login'))
        
        return view(**kwargs)
    
    return wrapped_view
