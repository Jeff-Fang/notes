
## monitor network devices/interfaces
```bash
ip -br a
nmcli d
ps aux | grep wlan0
journalctl -xe
journalctl -u NetworkManager
````


## Virtual mac80211_hwsim
```bash
sudo modprobe mac80211_hwsim
sudo modprobe -r mac80211_hwsim

sudo ip link set dev eth42 down
sudo ip link delete eth42
```


## systemctl
```bash
sudo systemctl start netctl-auto@wlan0.service
sudo systemctl stop netctl-auto*
````


## disable interface
```bash
ip link set dev <interface> down
ip -br a
```


## wpa_supplicant
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


## Netplan
dependency:
- libsystemd-dev
- pandoc


