#! /usr/bin/python3.5
import requests
import json

url = "http://192.168.1.101/api/v2.0.0/registers/13"

headers = {
    'Content-Type': "application/json",
    'Authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA=="
    }

response = requests.request("GET", url, headers=headers)
json_data = json.loads(response.text)
value = json_data['value']

print(int(value)==0)
