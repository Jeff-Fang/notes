# Setup ROS environment
# If ROS environment is not been choosed, use Anaconda to setup python 3.
# Setting up Gazebo path as well

# Setting Gazebo Path
if [ -f ~/.rosenv/set_gazebo_path.sh ]; then
    . ~/.rosenv/set_gazebo_path.sh
fi

# Allow for ROS source choice
if [ -n "$BASH_VERSION" ]; then
  while true
  do
    read -p "Do you want to source ROS in this workspace? (y/n): " input_choice

    if [ "$input_choice" = "y" ]; then
      . ~/.rosenv/set_catkin_env.sh
      break
    elif [ "$input_choice" = "n" ]; then
      echo "ROS *NOT* sourced!"
      # Setup conda
      export PATH="/home/jeff/Application/anaconda3/bin:$PATH"
      break
    else
      echo "Warning: Not an acceptable option. Choose (y/n).                                  "
    fi
  done


elif [ -n "$ZSH_VERSION" ]; then
  while true
  do
    read -q "input_choice?Do you want to source ROS in this workspace?"
    echo

    if [ "$input_choice" = "y" ]; then
      . ~/.rosenv/set_catkin_env.sh
      # Set up ROS ip
      export ROS_IP=`echo $(hostname -I)`
      break
    elif [ "$input_choice" = "n" ]; then
      echo "ROS *NOT* sourced!"

      # Setup conda
      export PATH="/home/jeff/Application/anaconda3/bin:$PATH"
      break
    else
      echo "Warning: Not an acceptable option. Choose (y/n).                                  "
    fi
  done
fi
