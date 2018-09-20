from locust import HttpLocust, TaskSet
import random

PATHS = [
    "login",
    "start",
    "register",
]

def index(l):
    l.client.get("/")

def query(l):
    l.client.get("/" + random.choice(PATHS))

class UserBehavior(TaskSet):
    tasks = {index: 1, query: 4}

    def on_start(self):
        pass

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 2000
    max_wait = 9000