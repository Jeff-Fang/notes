
`/etc/wpa_supplicant/wpa_supplicant.conf`
```
ctrl_interface=/run/wpa_supplicant
update_config=1
```


```sh
systemctl stop NetworkManager
systemctl disable NetworkManager

iwconfig
iw dev

wpa_supplicant -i interface -c /etc/wpa_supplicant/wpa_supplicant.conf

wpa_cli
```
