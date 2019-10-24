```python
logging.info("Data object {}".format(data_object.data.__dict__))
```
```sh
rosservice call /MissionController/get loggers
rosservice call /MissionController/set logger level "logger: 'ros.mirMission'
level:'debug'"
tail -f /var/log/mir/ros/latest/rosout.log
```
```cpp
OS_DEBUG_STREAM_NAMED(
            "JEFF", "SetFleetRegisterActionHandler: Got Fleet IP address as "
                                    << fleet_ip_);
```
