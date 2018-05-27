```bash
# List all processes
ps -ef
ps -ef | grep "sslocal"
ps aux | less

# to view every process
ps -A or ps -e

# All processes running by a user:
ps -u username

# to kill a process
kill -9 processname
kill pid


# Other ref
man ps
man kill
man 2 kill
man killall
man nice
man pkill
man renice
man 7 signal
man skill

```

simply killing a process that you think is useless may be a mistake. The system might restart the process, or something you depend on might depend on the process you killed. Learn what the processes do, and look at `/etc/init/` and `/etc/init.d`, `/etc/rc?.d`, `man service` to see how processes are started by the system.
