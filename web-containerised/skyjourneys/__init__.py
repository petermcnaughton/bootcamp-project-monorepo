import os

from flask import Flask


def create_app(test_config=None):
    app = Flask(__name__, instance_relative_config=True)
    app.config.from_mapping(
        SECRET_KEY='dev',  # Change to random key for production
    )

    if (test_config is None):
        # Load instance config (if exists) when not testing
        app.config.from_pyfile('config.py', silent=True)
    else:
        # Load test config if passed in
        app.config.from_mapping(test_config)

    # Ensure the instance folder exists
    try:
        os.makedirs(app.instance_path)
    except OSError:
        pass

    # Attach user blueprint
    from . import user
    app.register_blueprint(user.bp)

    # Attach auth blueprint
    from . import auth
    app.register_blueprint(auth.bp)

    # Attach booking blueprint
    from . import booking
    app.register_blueprint(booking.bp)

    from . import information
    app.register_blueprint(information.bp)

    return app
