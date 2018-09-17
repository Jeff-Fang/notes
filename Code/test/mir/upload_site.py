# import requests

# url = "http://192.168.1.101/api/v2.0.0/sessions/5c6934c4-2b2a-11e8-a20f-f44d306dcd92/export"

# headers = {
#     'cache-control': "no-cache",
#     'postman-token': "d573eb48-086e-7aeb-248a-ab20742cda04",
#     'Authorization': "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA=="
#     }

# response = requests.request("GET", url, headers=headers)

# with open('/home/jeff/FWSite.site', 'wb') as f:
#     f.write(response.content)


import requests

URL_BASE = 'http://192.168.1.101:8080'
JSON_HEADER = {
    'Authorization' : 'Basic YWRtaW46OGM2OTc2ZTViNTQxMDQxNWJkZTkwOGJkNGRlZTE1ZGZiMTY3YTljODczZmM0YmI4YTgxZjZmMmFiNDQ4YTkxOA==',
    'Content-Type' : 'application/json'
    }
AUTH_HEADER = {
    'Authorization' : 'Basic YWRtaW46OGM2OTc2ZTViNTQxMDQxNWJkZTkwOGJkNGRlZTE1ZGZiMTY3YTljODczZmM0YmI4YTgxZjZmMmFiNDQ4YTkxOA=='
    }

# response = requests.get(URL_BASE + '/v2.0.0/sessions/09f1d77a-a446-11e7-85e6-f44d306a41f0/export', headers=JSON_HEADER)
# print response.status_code
# if response.status_code == 200:
#     site_file = open("mysite.site", "w")
#     site_file.write(response.content)
#     site_file.close()


response = requests.post(URL_BASE + '/v2.0.0/sessions/import', headers=AUTH_HEADER, files={'file': open("FWSite.site", 'rb')})
print(response.status_code)
print(response.text)
