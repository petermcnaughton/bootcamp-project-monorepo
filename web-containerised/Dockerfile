#########################################
# SKYJOURNEYS
# FLASK APP
########################################

FROM ubuntu:latest

MAINTAINER Dan Foad
RUN ls -la
RUN apt-get update && apt-get install -y \
  apache2 \
  libapache2-mod-wsgi-py3 \
  python-pip \
  build-essential \
  vim \
  python3-pip \
  && apt-get clean \
  && apt-get autoremove \
  && rm -rf /var/lib/apt/lists/*

ADD . /var/www/html/skyjourneys
WORKDIR /var/www/html/skyjourneys

RUN pip3 install virtualenv
RUN virtualenv venv
RUN /bin/bash -c "source /var/www/html/skyjourneys/venv/bin/activate"
RUN pip3 install flask pytest validate_email requests

RUN a2enmod wsgi

COPY ./conf/000-default.conf /etc/apache2/sites-enabled/000-default.conf

EXPOSE 80

CMD /usr/sbin/apache2ctl -D FOREGROUND
