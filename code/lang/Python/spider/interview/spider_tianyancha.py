#!/usr/bin/env python3

"""
A spider to get shareholder information on tianyancha.com
by Fang, Zheyu at 2017, May 11
"""

import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities


def save_content(content, file_location):
    with open(file_location, 'w+') as fp:
        fp.write(content)
    print('Content saved at ', file_location)
    pass


def new_driver():
    dcap = dict(DesiredCapabilities.PHANTOMJS)
    dcap["phantomjs.page.settings.userAgent"] = (
        """Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) \
        AppleWebKit/537.36 (KHTML, like Gecko) \
        Chrome/57.0.2987.133 Safari/537.36"""
    )
    driver = webdriver.PhantomJS(
        executable_path="/Users/JFrmbp/Support/" +
        "anaconda3/envs/py3/bin/phantomjs",
        desired_capabilities=dcap
    )
    return driver


def search_and_get_link(driver, search_str, url):
    driver.get(url)

    print('Loading initial page...')
    time.sleep(5)
    elem = driver.find_element_by_id('live-search')
    elem.send_keys(search_str)
    elem.send_keys(Keys.RETURN)

    print('Searching ', search_str, '...')
    time.sleep(7)
    # driver.save_screenshot('search.png')
    # save_content(driver.page_source, 'search_result.html')
    links = driver.find_element_by_xpath(u'//a[@event-name="搜索结果-企业区域"]')
    link = links.get_attribute('href')
    print("Get link: ", link)

    return link


def get_content(driver, url):
    driver.get(url)
    time.sleep(5)
    content = driver.page_source
    driver.close()

    save_content(content, 'output.html')

    soup = BeautifulSoup(content, 'lxml')
    return soup


def get_shareholder(soup):
    invest_detail = soup.find_all(
        attrs={'event-name': '企业详情-股东人'})

    # print(invest_detail)
    investors = ""
    for investor in invest_detail:
        investor_info_split = investor.text.replace('\n', '').split()
        investor_info = ' '.join(investor_info_split)
        print(investor_info)
        investors += investor_info
        investors += '\n'

    return investors


def main():
    tyc_url = 'http://www.tianyancha.com/search'
    search_str = '深圳市腾讯计算机系统有限公司'
    tencent_url = 'http://www.tianyancha.com/company/9519792'
    driver = new_driver()

    # try:
    corp_link = search_and_get_link(driver, search_str, tyc_url)
    # except Exception:
    #     driver.save_screenshot('error.png')
    # driver.close()

    corp_content = get_content(driver, corp_link)
    shareholder_info = get_shareholder(corp_content)
    save_content(shareholder_info, 'info.txt')


if __name__ == '__main__':
    main()
