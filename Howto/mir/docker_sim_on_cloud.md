I figured out that there are a couple of things you need to do to make the docker container work on a cloud server.

1. For the last line in DockerSim, you need to remove the flags -x -p so we don't pass the X server and we don't prevent the port forwarding to the host system.
```
_sh "$_simDirectory/MiR_Simulator-$simVersion.mir"
```

2. In entrypoint.sh, you need to set the correct permission to mysql before `service mysql start`.
```
# Start mysql
echo "Starting mysql..."
chown -R mysql:mysql /var/lib/mysql /var/run/mysqld
service mysql start
```

3. Then if you need to load a relativly large map, you need to decrease the resolution of the planner.
- Inside the docker container, goto /usr/local/mir/software/robot/ros/install/share/mir_launch_sim/stage
- Use `nano world.world` to edit the file
- find `resolution 0.001` and change it to `resolution 0.05`
- Then run `service mir_service stop` and `service mir_service start`
