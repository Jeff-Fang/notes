import requests

url = "http://192.168.1.101/api/v2.0.0/sessions/5c6934c4-2b2a-11e8-a20f-f44d306dcd92/export"

headers = {
    'cache-control': "no-cache",
    'postman-token': "d573eb48-086e-7aeb-248a-ab20742cda04",
    'Authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA=="
    }

response = requests.request("GET", url, headers=headers)

with open('/home/jeff/FWSite.site', 'wb') as f:
    f.write(response.content)
