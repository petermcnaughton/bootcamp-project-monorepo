from skyjourneys import create_app, connector

def test_home_page_load(client):
    response = client.get('/')
    assert response.status_code == 200
    assert b'Start a Journey' in response.data

def test_featured(client):
    response = client.get('/')
    featured = connector.getFeatured()
    assert str.encode(featured['location']) in response.data
    assert str.encode(featured['country']) in response.data
    assert str.encode(featured['show']) in response.data

def test_user_profile(client, auth):
    auth.login()
    response = client.get('/profile')
    assert response.status_code == 200
    assert b'Joe Bloggs' in response.data