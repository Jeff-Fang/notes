import requests

url = "http://192.168.1.101/api/v2.0.0/status"

headers = {
    'cache-control': "no-cache",
    'postman-token': "d573eb48-086e-7aeb-248a-ab20742cda04"
    }

response = requests.request("GET", url, headers=headers)

print(response.text)
