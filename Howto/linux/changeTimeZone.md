```shell
mirex@fleet:~$ sudo mv /etc/localtime /etc/localtime.bak
[sudo] password for mirex: 
mirex@fleet:~$ sudo ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
mirex@fleet:~$ date
Wed Sep 11 16:16:32 CST 2019
mirex@fleet:~$ ls
mirex@fleet:~$ sudo reboot
Connection to 192.168.50.43 closed by remote host.
Connection to 192.168.50.43 closed.
```
