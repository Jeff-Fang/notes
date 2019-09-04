# Setup ROS environment
# If ROS environment is not been choosed, use Anaconda to setup python 3.
# Setting up Gazebo path as well

# Allow for ROS source choice
while true
do
  if [ -n "$BASH_VERSION" ]; then
    read -p "Do you want to source ROS in this workspace? (y/n): " input_choice
  elif [ -n "$ZSH_VERSION" ]; then
    read -q "input_choice?Do you want to source ROS in this workspace?"
    echo
  fi

  if [ "$input_choice" = "y" ]; then
    . ~/.rosenv/set_catkin_env.sh

    # export ROS_IP=`echo $(hostname -I)`
    # export ROS_MASTER_URI=http://192.168.1.107:11311
    # export ROS_HOSTNAME=192.168.1.107
 
    # Setting Gazebo Path
    if [ -f ~/.rosenv/set_gazebo_path.sh ]; then
        . ~/.rosenv/set_gazebo_path.sh
    fi

    # export TURTLEBOT3_MODEL=burger

    break

  elif [ "$input_choice" = "n" ]; then
    echo "ROS *NOT* sourced!"
    # Setup conda
    # export PATH="/home/jeff/Application/anaconda3/bin:$PATH"

    # >>> conda initialize >>>
    # !! Contents within this block are managed by 'conda init' !!
    __conda_setup="$('/home/jeff/Application/anaconda3/bin/conda' 'shell.bash' 'hook' 2> /dev/null)"
    if [ $? -eq 0 ]; then
        eval "$__conda_setup"
    else
        if [ -f "/home/jeff/Application/anaconda3/etc/profile.d/conda.sh" ]; then
            . "/home/jeff/Application/anaconda3/etc/profile.d/conda.sh"
        else
            export PATH="/home/jeff/Application/anaconda3/bin:$PATH"
        fi
    fi
    unset __conda_setup
    # <<< conda initialize <<<
    break
  else
    echo "Warning: Not an acceptable option. Choose (y/n).                                  "
  fi
done
