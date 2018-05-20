# ROS linux commands

## Catkin Workspaces
```bash
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
cd ~/catkin_ws/src
# catkin_create_pkg <some_package_name> [dependency1 dependency2 ...]
catkin_create_pkg first_package
```

