from skyjourneys import create_app, connector

from flask import g
from werkzeug import ImmutableMultiDict

def test_start_page_load(client):
    response = client.get('/start')
    assert response.status_code == 200
    assert b'Start' in response.data

def test_booking_page_load(client):
    response = client.get('/choose')
    assert response.status_code == 200
    assert b'Flights' in response.data

    
    assert b'Accommodation' in response.data
    assert b'Activities' in response.data



def test_accom(client):
    response = client.get('/choose')
    accomTest = connector.getAccoms()

    data = response.data.decode('utf-8')

    assert accomTest[0]['accom']['accomName'] in data
    assert accomTest[0]['accom']['accomAddress'] in data
    assert accomTest[0]['accom']['accomRooms'] in data
    assert accomTest[0]['accom']['checkInTime'] in data
    assert accomTest[0]['accom']['checkOutTime'] in data
    assert accomTest[0]['price'] in data

def test_activity(client):
    response = client.get('/choose')
    activityTest = connector.getActivity()
    data = response.data.decode('utf-8')
    assert activityTest[0]['activity']['activityName'] in data
    assert activityTest[0]['activity']['activityLocation'] in data
    assert activityTest[0]['activity']['activityAddress'] in data
    assert activityTest[0]['activity']['activityPeople'] in data
    assert activityTest[0]['activity']['activityTime'] in data
    assert activityTest[0]['price'] in data	

def test_choose_shows_choice(client, app):
    response = client.post('/choose',
        data = {
            'from': 'London',
            'to': 'New York',
            'depart': '2018-08-02',
            'returnbound': '2018-08-05',
            'adults': '2',
            'children': '1',
            'return': 'Return'
        })
    assert b'London' in response.data
    assert b'New York' in response.data

def test_start_page_default_values(client):
    response = client.get('/start')
    
    assert b'<input type="number" id="adults" name="adults" min="1" max="5" value="1">' in response.data
    assert b'<input type="number" id="children" name="children" min="0" max="5" value="0">' in response.data
    assert b'<label for="return"><input type="radio" name="return" value="Return" id="return" checked>Return</label>' in response.data

def test_start_page_set_values(client):
    response = client.post('/start',
        data = {
            'from': 'London',
            'to': 'New York',
            'depart': '2018-08-02',
            'returnbound': '2018-08-05',
            'adults': '2',
            'children': '1',
            'return': 'Return'
        }
    )
    assert b'<input class="autocomplete-field" type="text" id="from" name="from" placeholder="e.g. London" value="London">' in response.data
    assert b'<input class="autocomplete-field" type="text" id="to" name="to" placeholder="e.g. New York" value="New York">' in response.data
    assert b'<input type="date" id="depart" name="depart" min="2018-08-04" value="2018-08-02">' in response.data
    assert b'<input type="date" id="returnbound" name="returnbound" min="2018-08-05" value="2018-08-05">' in response.data
    assert b'<input type="number" id="adults" name="adults" min="1" max="5" value="2">' in response.data