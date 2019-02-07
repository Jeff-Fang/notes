# Packages & Catkin Workspaces

### Create empty workspace

```bash
mkdir -p ~/catkin_ws/src && cd ~/catkin_ws/src
catkin_init_workspace
ls -l
```

Notice that a symbolic link (`CMakeLists.txt`) has been created to `/opt/ros/kinetic/share/catkin/cmake/toplevel.cmake`

```bash
cd ~/catkin_ws
catkin_make
ls

```

![](https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59114124_02c/02c.jpeg)

Before you begin to work with and develop your own ROS package, you should take a moment to get acquainted with catkin workspace conventional directory structure as described in the ROS Enhancement Proposal (REP) 128: http://www.ros.org/reps/rep-0128.html

### Add a package

```bash
cd ~/catkin_ws/src
git clone https://github.com/udacity/simple_arm_01.git simple_arm
cd ~/catkin_ws
catkin_make
```

Some times the building would fail and you need to install additional package by
```bash
sudo apt-get install ros-kinetic-controller-manager
```

Once done the building, you can launch by
```bash
source devel/setup.bash  # or setup.zsh
roslaunch simple_arm robot_spawn.launch
```

To install missing runtime dependency, you can use the `rosdep` tool.
```bash
rosdep check simple_arm
rosdep install -i simple_arm
```

Issues with this command may arise when using a VM. If this is the case, please try:
```bash
sudo apt-get install ros-kinetic-gazebo-ros-control
```

### More about the package

All ROS packages should reside under the `src` directory. After sourcing the ROS env and catkin workspace, you can create a package by syntax `catkin_create_pkg <your_package_name> [dependency1 dependency2 …]`

```bash
cd ~/catkin_ws/src
catkin_create_pkg first_package

```
Voilà. You just created your first catkin package! Navigating inside our newly created package reveals that it contains just two files, CMakeLists.txt and package.xml. This is a minimum working catkin package. It is not very interesting because it doesn't do anything, but it meets all the requirements for a catkin package. One of the main functions of these two files is to describe dependencies and how catkin should interact with them.

### The conventional directory structure
Typical:

- scripts (python executables)
- src (C++ source files)
- msg (for custom message definitions)
- srv (for service message definitions)
- include -> headers/libraries that are needed as dependencies
- config -> configuration files
- launch -> provide a more automated way of starting nodes

Other folders may include

- urdf (Universal Robot Description Files)
- meshes (CAD files in .dae (Collada) or .stl (STereoLithography) format)
- worlds (XML like files that are used for Gazebo simulation environments)

![](https://d17h27t6h515a5.cloudfront.net/topher/2017/May/591144ee_rosl2/rosl2.png)

There are many packages that you can install. To see a list of available packages for the Kinetic distribution, take some time to explore [here](http://www.ros.org/browse/list.php).
