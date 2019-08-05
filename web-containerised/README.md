# SkyJourneys-web (containerised) Installation Guide

This is a version of the SkyJourneys web app that uses static data (instead of Amadeus Flight API) and a number of features disabled (SMS messaging with AWS SNS, Cookie Control GDPR approver, etc) so it can be fully run inside of a docker container without worrying about API keys or credentials

Build/Run docker container using the following:

`docker build -t skyjourneys . && docker run -ti -p 8090:80 skyjourneys`

Replace `8090` with whichever port you want to map it to

Also, these modifications were made without updating the tests so they will likely fail, if you need them to work for whatever reason let me know
