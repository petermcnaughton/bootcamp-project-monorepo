# SkyJourneys-web Installation Guide

In order to get this working you need to have the following installed:

 - Python 3.7.0 (Should come with OSX)
 - pip 10.0.1 (Should come with Python install, if not then run `sudo easy_install pip` or use HomeBrew)
 - virtualenv 16.0.0 (`pip install virtualenv`)

`git clone http://skyjourneysgitlab/avacados/SkyJourneys-web.git`

`cd SkyJourneys-web`

`mkdir venv`

`virtualenv venv`

`. venv/bin/activate`

`pip install -e .`

`export FLASK_APP=skyjourneys`

To run the application call: `flask run`
To run the unit tests call: `pytest` (Do `pytest -v` if you want test info)
To see test coverage use: `coverage run -m pytest`

If you get a message like 'No such command X' just run `pip install X`, make sure you're inside the virtual environment also.

On subsequent occasions you need to make sure to enter the virtualenv and export FLASK_APP again

`. venv/bin/activate`

`export FLASK_APP=skyjourneys`

If you don't want to restart flask every time you make changes, set the following:

`export FLASK_DEBUG=1`