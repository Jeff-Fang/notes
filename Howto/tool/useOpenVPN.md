# Use openvpn connecting to SSL-VPN
## Setup SSL-VPN on Aliyun
[Setup tutorial with windows client](https://www.alibabacloud.com/help/zh/doc-detail/64994.htm?spm=a2c63.p38356.b99.15.65be778au6GJcP)
Download cert files from Aliyun as cert_files.zip

## macOS Client
```bash
brew install openvpn
cp cert_files.zip /usr/local/etc/openvpn/ && cd /usr/local/etc/openvpn
unzip cert_files.zip
sudo /usr/local/opt/openvpn/sbin/openvpn --config /usr/local/etc/openvpn/config.ovpn
```

## Ubuntu Client
[Official Documentation](https://help.ubuntu.com/16.04/serverguide/openvpn.html)
```bash
sudo apt install openvpn
cp cert_files.zip /etc/openvpn/ && cd /etc/openvpn
unzip cert_files.zip
cp config.ovpn client.conf
sudo systemctl start openvpn@client
sudo systemctl status openvpn@client
ifconfig tun0
netstat -rn
```