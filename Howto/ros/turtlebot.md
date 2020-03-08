# Login
ssh pi@192.168.0.19
turtlebot

# [Bringup a Turtlebot3](http://emanual.robotis.com/docs/en/platform/turtlebot3/bringup/#ros-1-bringup)

**Remote PC**
roscore

**Turtlebot3**
roslaunch turtlebot3_bringup turtlebot3_robot.launch

**Remote PC**
export TURTLEBOT3_MODEL=${TB3_MODEL}
roslaunch turtlebot3_bringup turtlebot3_remote.launch

rosrun rviz rviz -d `rospack find turtlebot3_description`/rviz/model.rviz
