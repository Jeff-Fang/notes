import requests

url = "http://192.168.1.101/api/v2.0.0/mission_queue"

payload = "{\n\t\"mission_id\": \"22b20848-4dee-11e8-9537-f44d306ef93d\"\n}"
headers = {
    'content-type': "application/json",
    'authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA==",
    'cache-control': "no-cache",
    'postman-token': "9e89fe88-13a5-45f2-fb19-6226ba8b5353"
    }

response = requests.request("POST", url, data=payload, headers=headers)

print(response.text)
