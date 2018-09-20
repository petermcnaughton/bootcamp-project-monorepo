from flask import (
    Blueprint, flash, g, redirect, render_template, request, session, url_for
)
from werkzeug.exceptions import abort

from skyjourneys.auth import login_required
from . import connector

bp = Blueprint('user', __name__)

@bp.route('/')
def index():
    featured = connector.getFeatured()
    return render_template('user/index.html', featured=featured)

@bp.route('/profile')
@login_required
def profile():
    user = session.get('user')
    return render_template('user/profile.html', user=user)

@bp.route('/wishlist')
@login_required
def wishlist():
    wishlist = connector.getWishlist()
    return render_template('user/wishlist.html', wishlist=wishlist)

@bp.route('/achievements')
@login_required
def achievements():
    achievements = connector.getAchievements()
    return render_template('user/achievements.html', achievements=achievements)
