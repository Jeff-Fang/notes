#! /usr/bin/python3.5
import requests
import time
import json

base_url = "http://192.168.1.103/api/v2.0.0"
reg_url = base_url + '/registers'
r7_url = reg_url + '/7'
r8_url = reg_url + '/8'
r13_url = reg_url + '/13'
r14_url = reg_url + '/14'

headers = {
    'Content-Type': "application/json",
    'Authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA=="
    }

int0 = "{\n\t\"value\": 0\n}"
int1 = "{\n\t\"value\": 1\n}"

def get_lower_cmd():
    response = requests.get(r13_url, headers=headers)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) != 0:
        print("Received lower cmd")
        return True
    elif int(value) == 0:
        print("No lower cmd")
        return False
    else:
        error()
    pass

def get_raising_cmd():
    response = requests.get(r14_url, headers=headers)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) != 0:
        print("Received raising cmd")
        return True
    elif int(value) == 0:
        print("No raising cmd")
        return False
    else:
        error()
    pass

def lower_lift():
    response = requests.post(r8_url, headers=headers, data=int0)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) == 1:
        print("Start lowering")

    time.sleep(5)
    response = requests.post(r7_url, headers=headers, data=int1)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) == 1:
        print("Shelf lowered")
    pass

def raising_lift():
    response = requests.post(r7_url, headers=headers, data=int0)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) == 1:
        print("Start raising")

    time.sleep(5)
    response = requests.post(r8_url, headers=headers, data=int1)
    json_data = json.loads(response.text)
    value = json_data['value']
    if int(value) == 1:
        print("Shelf raised")
    pass

count = 0
while True:
    count += 1
    print(count)
    lower_cmd = get_lower_cmd()
    raising_cmd = get_raising_cmd()

    if lower_cmd and not raising_cmd:
        lower_lift()
    elif raising_cmd and not lower_cmd:
        raising_lift()
    elif raising_cmd and lower_cmd:
        print("ERROR! Cannot raising and lowering at the same time!")
    else:
        print("No cmd yet")

    print()
    time.sleep(1)
