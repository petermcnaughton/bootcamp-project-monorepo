from flask import (
    Blueprint, flash, g, redirect, render_template, request, session, url_for
)

from . import connector

bp = Blueprint('information', __name__)

@bp.route('/privacy')
def profile():
    return render_template('information/privacy.html')
