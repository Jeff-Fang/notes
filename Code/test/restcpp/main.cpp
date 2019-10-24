#include <iostream>
#include <curl/curl.h>

static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp)
{
    ((std::string*)userp)->append((char*)contents, size * nmemb);
    return size * nmemb;
}

int main() 
{
    CURL* hnd = curl_easy_init();
    std::string readBuffer;

    curl_easy_setopt(hnd, CURLOPT_CUSTOMREQUEST, "GET");
    curl_easy_setopt(hnd, CURLOPT_URL, "http://172.17.0.2/api/v2.0.0/registers/1");

    struct curl_slist* headers = NULL;
    headers = curl_slist_append(headers, "Host: 172.17.0.2");
    headers = curl_slist_append(headers, "Content-Type: application/json");
    headers =
        curl_slist_append(headers,
                          "Authorization: Basic "
                          "ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU"
                          "3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA==");
    curl_easy_setopt(hnd, CURLOPT_HTTPHEADER, headers);
    curl_easy_setopt(hnd, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(hnd, CURLOPT_WRITEDATA, &readBuffer);

    CURLcode ret = curl_easy_perform(hnd);
    curl_easy_cleanup(hnd);


    std::cout << readBuffer << std::endl;
    return 0;
}

