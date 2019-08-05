import os
import tempfile

import pytest

from skyjourneys import create_app

class AuthActions(object):
    def __init__(self, client):
        self._client = client
    
    def login(self, email='joebloggs@example.com', password='password'):
        return self._client.post(
            '/login',
            data={'email': email, 'password': password}
        )
    
    def logout(self):
        return self._client.get('/auth/logout')

@pytest.fixture
def app():

    app = create_app({
        'TESTING': True,
    })

    yield app

@pytest.fixture
def auth(client):
    return AuthActions(client)

@pytest.fixture
def client(app):
    return app.test_client()