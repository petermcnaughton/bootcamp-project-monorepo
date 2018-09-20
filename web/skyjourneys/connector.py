# coding: utf-8
import json
import requests
import time
import datetime
import random

from flask import g

url = 'http://34.255.168.28:8080/api/search/flights'

# achievements API
url2 = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/achievements.json?alt=media&token=98db2fd9-95de-410d-ab31-99b3efcaebce"

users = [{
    'id': 0,
    'firstname': 'Joe',
    'lastname': 'Bloggs',
    'email': 'joebloggs@example.com',
    'password': 'password'
}]

def getFlights(parameters):
    count = 1
    print(searchFlights(parameters))
    flightsRaw = searchFlights(parameters)['searchResults']
    flights = []
    for f in flightsRaw:
        outbound = f['outbound']['segments'][0]
        returnbound = f['returnbound']['segments'][0]
        flights.append({
            'flightid': count,
            'price':  ("%.2f" % f['price']),
            'outbound': {
                'departureTime': datetime.datetime.strptime(
                    outbound['departureDateTime'],
                    '%Y-%m-%dT%H:%M'
                ).strftime('%H:%M'),
                'departureAirportIATA': outbound['departureAirportIATA'],
                'duration': datetime.datetime.strptime(
                    outbound['flightLength'],
                    '%H:%M'
                ).strftime('%-Hh %-M'),
                'arrivalTime': datetime.datetime.strptime(
                    outbound['arrivalDateTime'],
                    '%Y-%m-%dT%H:%M'
                ).strftime('%H:%M'),
                'arrivalAirportIATA': outbound['arrivalAirportIATA']
            },
            'return': {
                'departureTime': datetime.datetime.strptime(
                    returnbound['departureDateTime'],
                    '%Y-%m-%dT%H:%M'
                ).strftime('%H:%M'),
                'departureAirportIATA': returnbound['departureAirportIATA'],
                'duration': datetime.datetime.strptime(
                    returnbound['flightLength'],
                    '%H:%M'
                ).strftime('%-Hh %-M'),
                'arrivalTime': datetime.datetime.strptime(
                    returnbound['arrivalDateTime'],
                    '%Y-%m-%dT%H:%M'
                ).strftime('%H:%M'),
                'arrivalAirportIATA': returnbound['arrivalAirportIATA']
            }
        })
        count += 1
    return flights

def searchFlights(parameters):
    searchUrl = url + \
        '?origin=' + parameters['from'] + \
        '&destination=' + parameters['to'] + \
        '&departure_date=' + parameters['depart'] + \
        '&adults=' + parameters['adults'] + \
        '&children=' + parameters['children']
    
    if parameters['returnReturn'] == 'checked':
        searchUrl += '&return_date=' + parameters['returnbound']
    
    return json.loads(requests.get(searchUrl).content.decode('utf-8'))



# Accomodation Mock JSON
accomData = '''{
    "accomId": "12345",
    "accom": {
        "bookingInfo": [
            {
                "accomName": "Hotel Dubrovnik",
                "accomAddress": "Šetalište kralja Zvonimira 16, 20000, Dubrovnik, Croatia",
                "checkInDateTime": "2018-09-16T15:00:00",
                "checkOutDateTime": "2018-09-23T12:00:00",
                "rooms": "1 room 1 Double Bed"
            }
        ]
    },
    "price": 245,
    "currency": "GBP"
}'''

def getAccoms():
    acount = 10
    accomRaw = []
    accoms = []
    for x in range(acount):
        accomRaw.append(getAccomById(0))
    for a in accomRaw:
        # print(a)
        accomBooking = a['accom']['bookingInfo'][0]
        accoms.append({
            'accomId': a['accomId'],
            'price':  ("%.2f" % a['price']),
            'accom': {
                'accomName': accomBooking['accomName'],
                'accomAddress': accomBooking['accomAddress'],
                'accomRooms': accomBooking['rooms'],
                'checkInTime': datetime.datetime.strptime(
                    accomBooking['checkInDateTime'],
                    '%Y-%m-%dT%H:%M:%S'
                ).strftime('%H:%M'),
                'checkOutTime': datetime.datetime.strptime(
                    accomBooking['checkOutDateTime'],
                    '%Y-%m-%dT%H:%M:%S'
                ).strftime('%H:%M')
            }
        })
    return accoms

def getAccomById(id):
    # return json.loads(requests.get(url).content.decode('utf-8'))
    return json.loads(accomData)







# Activities

activityData = '''{
    "activityId": "12334",
    "activity": {
        "activityInfo": [
            {
                "activityName": "Kayak Gentle Adriatic Waves",
                "activityAddress": "Pile Bay, Dubrovnik",
                "activityLocation": "Lovrijenac",
                "activityTime": "2018-08-05T13:00:00",
                "activityPeople": "2"
            }
        ]
    },
    "price": 45,
    "currency": "GBP"
}'''


def getActivity():
    atcount = 10
    activityRaw = []
    activities = []
    for x in range(atcount):
        activityRaw.append(getActivityById(0))
    for t in activityRaw:
        activityBooking = t['activity']['activityInfo'][0]
        activities.append({
            'activityId': t['activityId'],
            'price':  ("%.2f" % t['price']),
            'activity': {
                'activityName': activityBooking['activityName'],
                'activityLocation': activityBooking['activityLocation'],
                'activityAddress': activityBooking['activityAddress'],
                'activityPeople': activityBooking['activityPeople'],
                'activityTime': datetime.datetime.strptime(
                    activityBooking['activityTime'],
                    '%Y-%m-%dT%H:%M:%S'
                ).strftime('%H:%M')
            }
        })
    return activities


def getActivityById(id):
    # return json.loads(requests.get(url).content.decode('utf-8'))
    return json.loads(activityData)







# CURRENTLY A LOT OF MOCK DATA, ADJUST WHEN API ENDPOINTS SET UP

def getFeatured():
    return {
        'location': 'Dubrovnik',
        'country': 'Croatia',
        'show': 'Star Wars: The Last Jedi'
    }

def registerUser(firstname, lastname, email, password):
    isRegistered = {'status': True, 'error': ''}
    for user in users:
        if user['email'].lower() == email.lower():
            isRegistered['status'] = False
            isRegistered['error'] = 'User already exists.'
            break
    if isRegistered['status']:
        users.append({'id': users[-1]['id'] + 1,'firstname': firstname, 'lastname': lastname, 'email': email, 'password': password})
        isRegistered['id'] = users[-1]['id']
    return isRegistered

def attemptLogin(email, password):
    for user in users:
        if user['email'].lower() == email.lower() and user['password'] == password:
            return user
        elif user['email'].lower() == email.lower():
            return 'Password Incorrect.'
    return 'User not found.'

def getUser(id):
    for user in users:
        if user['id'] is id:
            return user
        
    return None

def getWishlist():
    wishlist = [
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'},
        {'city': 'City Name', 'country': 'Country'}
    ]
    return wishlist



def getAchievements():
    r = json.loads(requests.get(url2).content)
    count = len(r['achievements'])
    achievements = []

    for x in range(count):
        a = getAchievementsById(x)
        achievements.append({
            'description': a['description'],
            'name': a['name'],
            'unlocked': a['unlocked'],
            'image': a['image'],
            'id': a['id'],
            'prize_image': a['prize_image'],
            'prize': a['prize']
        })
    return achievements

# get the achievements in the URL from firebase
def getAchievementsById(id):
    r = json.loads(requests.get(url2).content)
    return r['achievements'][id];



def getConfirmationDetails():
    details = {
        'bookingID': ''.join(random.choice('0123456789ABCDEF') for i in range(6)),
        'phoneNo': '+447847922377'
    }
    return details

def getFeaturedDetails():
    details = {
        'location': 'Dubrovnik',
        'country': 'Croatia',
        'description': 'Regardless of whether you are visiting Dubrovnik for ' \
                       'the first time or the hundredth, the sense of awe never ' \
                       'fails to descend when you set eyes on the beauty of ' \
                       'the old town. Indeed it’s hard to imagine anyone ' \
                       'becoming jaded by the city’s limestone streets, ' \
                       'baroque buildings and the endless shimmer of the ' \
                       'Adriatic, or failing to be inspired by a walk along the ' \
                       'ancient city walls that protected the capital of a ' \
                       'sophisticated republic for centuries.',
        'in': [
            ('Game of Thrones', '2011-', 'got.jpg'),
            ('Casino Royale', '2006', 'casinoroyale.jpg'),
            ('Star Wars: The Last Jedi', '2017', 'swtlj.jpg'),
            ('Robin Hood: The Rebellion', '2018', 'robinhood.jpg'),
            ('Emerald City', '2016-2017', 'emeraldcity.jpg')
        ]

    }
    return details
