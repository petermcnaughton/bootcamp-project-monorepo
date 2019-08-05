from skyjourneys import create_app

def test_privacy_page_load(client):
    response = client.get('/privacy')

    assert response.status_code == 200
    assert b'Privacy' in response.data