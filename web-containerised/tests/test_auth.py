import pytest

from skyjourneys import create_app

def test_register_page(client):
    response = client.get('/register')
    assert response.status_code == 200
    assert b'Register' in response.data

@pytest.mark.parametrize(('firstname', 'lastname', 'email', 'password', 'message'), (
    ('', '', '', '', b'First name is required.'),
    ('a', '', '', '', b'Last name is required.'),
    ('a', 'a', '', '', b'Email is required.'),
    ('a', 'a', 'test@example.com', '', b'Password is required.'),
))
def test_register_validate_input(client, firstname, lastname, email, password, message):
    response = client.post(
        '/register',
        data={'firstname': firstname, 'lastname': lastname, 'email': email, 'password': password}
    )
    assert message in response.data

@pytest.mark.parametrize(('email', 'message'), (
    ('invalidemail', b'Email is not valid.'),
    ('invalidemail@', b'Email is not valid.'),
    ('validemail@test.com', b'Password is required.')
))
def test_register_validate_email(client, email, message):
    response = client.post(
        '/register',
        data={'firstname': 'a', 'lastname': 'a','email': email, 'password': ''}
    )
    assert message in response.data

def test_register(client, app):
    response = client.post(
        '/register',
        data={'firstname': 'a', 'lastname': 'a','email': 'a@a.com', 'password': 'a'}
    )
    assert 'http://localhost/' == response.headers['Location']

def test_register_user_exists(client, app):
    response = client.post(
        '/register',
        data={'firstname': 'Joe', 'lastname': 'Bloggs','email': 'joebloggs@example.com', 'password': 'password'}
    )
    assert b'User already exists.' in response.data

def test_login_page(client):
    response = client.get('/login')
    assert response.status_code == 200
    assert b'Sign In' in response.data

@pytest.mark.parametrize(('email', 'password', 'message'), (
    ('', '', b'Email is required.'),
    ('test@example.com', '', b'Password is required.'),
))
def test_login_validate_input(client, email, password, message):
    response = client.post(
        '/login',
        data={'email': email, 'password': password}
    )
    assert message in response.data

@pytest.mark.parametrize(('email', 'message'), (
    ('invalidemail', b'Email is not valid.'),
    ('invalidemail@', b'Email is not valid.'),
    ('validemail@test.com', b'Password is required.')
))
def test_login_validate_email(client, email, message):
    response = client.post(
        '/login',
        data={'email': email, 'password': ''}
    )
    assert message in response.data

def test_login(client, app):
    response = client.post(
        '/login',
        data={'email': 'joebloggs@example.com', 'password': 'password'}
    )
    assert 'http://localhost/' == response.headers['Location']

def test_login_incorrect_email(client, app):
    response = client.post(
        '/login',
        data={'email': 'nouser@example.com', 'password': 'password'}
    )
    assert b'User not found.' in response.data

def test_login_incorrect_password(client, app):
    response = client.post(
        '/login',
        data={'email': 'joebloggs@example.com', 'password': 'wrong'}
    )
    assert b'Password Incorrect.' in response.data

def test_logout(client, app):
    response = client.get(
        '/logout',
    )
    assert 'login' in response.headers['Location']