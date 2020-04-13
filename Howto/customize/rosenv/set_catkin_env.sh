# export catkin_ws=$HOME/catkin_ws
export catkin_ws=$HOME/Workspace/catkin_ws

if [ -n "$BASH_VERSION" ]; then
  # source /opt/ros/kinetic/setup.bash
  source /opt/ros/melodic/setup.bash
  source $catkin_ws/devel/setup.bash

elif [ -n "$ZSH_VERSION" ]; then
  # source /opt/ros/kinetic/setup.zsh
  source /opt/ros/melodic/setup.zsh
  source $catkin_ws/devel/setup.zsh
fi
