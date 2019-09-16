from skyjourneys import create_app

def test_home(client):
    response = client.get('/')
    assert response.status_code == 200
    assert b'Start a Journey' in response.data

def test_featured(client):
    response = client.get('/')
    assert b'Dubrovnik' in response.data
    assert b'Croatia' in response.data
    assert b'Star Wars' in response.data