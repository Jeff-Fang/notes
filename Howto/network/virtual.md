```bash
sudo modprobe mac80211_hwsim
sudo modprobe -r mac80211_hwsim

sudo ip link set dev eth42 down
sudo ip link delete eth42
