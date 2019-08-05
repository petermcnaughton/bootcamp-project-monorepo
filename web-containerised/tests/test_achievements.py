from skyjourneys import create_app, connector

from flask import g
from werkzeug import ImmutableMultiDict

def test_achievement_page_load(client, auth):
    auth.login()
    response = client.get('/achievements')
    assert response.status_code == 200
    assert b'Rebel pilot' in response.data