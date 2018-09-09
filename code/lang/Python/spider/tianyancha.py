# -*- coding: utf-8 -*-

# import sys
import os
# import re 
# import urllib, urllib2
import requests
# from lxml import html
# import datetime
# from bs4 import BeautifulSoup


def get_content(url):
    response = requests.get(url)
    content = response.content.decode('utf8')
    return content


def content_save(content, path, name):
    if not os.path.exists(path):
        os.makedirs(path)
    path = path+"/"+name+".txt"
    with open(path, 'w+') as fp:
        fp.write(content)


url = 'http://www.tianyancha.com/company/9519792'
content = get_content(url)
content_save(content, path="tianyan", name="tcall")
