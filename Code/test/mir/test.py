import requests
from time import sleep

url = "http://192.168.1.101/api/v2.0.0/status"
pl_continue = "{\n\t\"state_id\": 3 \n}"
pl_pause= "{\n\t\"state_id\": 4 \n}"

headers = {
    'content-type': "application/json",
    'authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA==",
    'cache-control': "no-cache",
    'postman-token': "00a40e04-cd97-f5c2-72c0-cf184bedafd8"
    }

def rb_continue():
    response = requests.request("PUT", url, data=pl_continue, headers=headers)
    print(response.text)
    pass

def rb_pause():
    response = requests.request("PUT", url, data=pl_pause, headers=headers)
    print(response.text)
    pass

rb_pause()
sleep(2)
rb_continue()
sleep(2)
rb_pause()

