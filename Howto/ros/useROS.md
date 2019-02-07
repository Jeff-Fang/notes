# ROS CLI Cheatsheet

## Basic ROS
```bash
roscore

rosrun turtlesim turtlesim_node
rosrun turtlesim turtle_teleop_key

rosnode list

rostopic list
rostopic info /turtle1/cmd_vel
rostopic echo /turtle1/cmd_vel

rosmsg info geometry_msgs/Twist
rosed geometry_msgs Twist.msg
```

## Catkin Workspaces
```bash
# Initialize workspace
mkdir -p ~/catkin_ws/src
cd ~/catkin_ws/src
catkin_init_workspace
cd ~/catkin_ws
catkin_make
cd ~/catkin_ws/src
git clone https://github.com/some/repository.git simple_arm
cd ~/catkin_ws
catkin_make
sudo apt-get install ros-kinetic-controller-manager
catkin_make
source devel/setup.bash
roslaunch simple_arm robot_spawn.launch
rosdep check simple_arm
rosdep install -i simple_arm
sudo apt-get install ros-kinetic-gazebo-ros-control

# Create catkin package
cd ~/catkin_ws/src
# catkin_create_pkg <some_package_name> [dependency1 dependency2 ...]
catkin_create_pkg first_package
```

