import requests
import datetime

url = "http://172.17.0.2/api/v2.0.0/mission_queue/2/actions/search"

querystring = {"whitelist":"state,message,finished,action_type","sort_by":"id,desc"}

payload = "{\n\t\"filters\": [\n\t\t{\n\t\t\t\"fieldname\": \"state\",\n\t\t\t\"operator\": \"!=\",\n\t\t\t\"value\": \"Executing\"\n\t\t}\n\t]\n}"
headers = {
    'Content-Type': "application/json",
    'Authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA==",
    'cache-control': "no-cache",
    'Postman-Token': "29e603df-51e7-452c-a869-a210ca9e3bb3"
    }


time_log = []

for i in range(10):
    response = requests.request("POST", url, data=payload, headers=headers, params=querystring)
    time = response.elapsed / datetime.timedelta(seconds=0.001)
    print(time)
    time_log.append(time)

print("=====")
print(sum(time_log) / len(time_log))
