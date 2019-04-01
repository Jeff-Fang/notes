
# export catkin_ws=$HOME/Udacity/robond_p2/catkin_ws
# export catkin_ws=$HOME/Udacity/robond_p3/catkin_ws
# export catkin_ws=$HOME/Udacity/robond_p4/catkin_ws
# export catkin_ws=$HOME/Udacity/robond_p3/exer_stick_ws
# export catkin_ws=$HOME/catkin_ws3
# export catkin_ws=$HOME/catkin_ws4
# export catkin_ws=$HOME/Workspace/Udacity/ros/catkin101
# export catkin_ws=$HOME/Workspace/Udacity/proj2/catkin_ws
export catkin_ws=$HOME/Workspace/Udacity/proj4/ekf_ws

if [ -n "$BASH_VERSION" ]; then
  source /opt/ros/melodic/setup.bash
  source $catkin_ws/devel/setup.bash

elif [ -n "$ZSH_VERSION" ]; then
  source /opt/ros/melodic/setup.zsh
  source $catkin_ws/devel/setup.zsh
fi

echo "ROS sourced!"
