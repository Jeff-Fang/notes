## Installation
Dependency:
```bash
sudo apt-get install libxml2-dev
sudo apt-get install libcurl4-openssl-dev
````
[libxml2](https://stackoverflow.com/questions/19904083/how-to-fix-error-with-xml2-config-not-found-when-installing-php-from-sources)
[libcurl](https://stackoverflow.com/questions/11471690/curl-h-no-such-file-or-directory/11471743)

## Test
### What is 'force_connect_cmd' and 'force_bss_selection'?
`test_ap_roam.py:149: test_ap_reconnect_auth_timeout()`
```python
    wpas = WpaSupplicant(global_iface='/tmp/wpas-wlan5')
    wpas.interface_add("wlan5",
                       drv_params="force_connect_cmd=1,force_bss_selection=1")
```
### What is "AP-STA-CONNECT"?
`hostapd.py:278: wait_sta()`
```python
    def wait_sta(self, addr=None, timeout=2):
        ev = self.wait_event(["AP-STA-CONNECT"], timeout=timeout)
        if ev is None:
            raise Exception("AP did not report STA connection")
        if addr and addr not in ev:
            raise Exception("Unexpected STA address in connection event: " + ev)
```

### Passive scan?

### signal override
`test_ap_roam.py:323: test_ap_roam_signal_level_override`

### set_rx_rssi(dev, val):
`hwsim_utils.py`

### relative rssi? relative_band_adjust?
`test_wpas_ctrl.py:2041: test_wpas_ctrl_set_sched_scan_relative_rssi`

### SIGNAL_MONITOR?
`test_wpas_ctrl.py:1952: test_wpas_ctrl_signal_monitor`

### autoscan?
```python
    dev[0].request('autoscan periodic:1')
```
### bgscan
[Roaming aggressiveness wpa_supplicant](http://lists.infradead.org/pipermail/hostap/2016-March/035423.html)
> wpa_supplicant_need_to_roam() decides whether to roam to a new AP based
> on the difference in signal between the current AP and the best new AP
> in the same ESS.  Changing the min_diff to lower values will cause the
> roaming decision to be more aggressive.

### add new dev
```python
    wpas = WpaSupplicant(global_iface='/tmp/wpas-wlan5')
    wpas.interface_add("wlan5", drv_params="force_connect_cmd=1")
    wpas.connect("sta-connect", psk="12345678", scan_freq="2412")
````
