#! /usr/bin/python3.5
import requests

base_url = "http://192.168.1.101/api/v2.0.0"
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

response = requests.post(r7_url, headers=headers, data=int1)

print(response.text)
