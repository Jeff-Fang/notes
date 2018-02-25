# Write ROS Nodes

### ROS Publishers

Publishers allow a node to send messages to a topic, so that data from the node can be used in other parts of the ROS system.

```python
pub1 = rospy.Publisher("/topic_name", message_type, queue_size=size)
```

ROS publishing can be either synchronous or asynchronous:

- Synchronous publishing means that a publisher will attempt to publish to a topic but may be blocked if that topic is being published to by a different publisher. In this situation, the second publisher is blocked until the first publisher has serialized all messages to a buffer and the buffer has written the messages to each of the topic's subscribers. This is the default behavior of a rospy.Publisher if the queue_size parameter is not used or set to None.
- Asynchronous publishing means that a publisher can store messages in a queue until the messages can be sent. If the number of messages published exceeds the size of the queue, the oldest messages are dropped. The queue size can be set using the queue_size parameter.

Once the publisher has been created as above, a message with the specified data type can be published as follows:

```python
pub1.publish(message)
```

### A HelloWorld node
Once the scripts directory has been created, executable scripts can be added to the package. However, in order for rosrun to find them, their permissions must be changed to allow execution. Let’s add a simple bash script that prints “Hello World” to the console.

```bash
# Adding the scripts directory
cd ~/catkin_ws/src/simple_arm/
mkdir scripts

# Creating a new script
cd scripts
echo '#!/bin/bash' >> hello
echo 'echo Hello World' >> hello

# Now change the permission and run the script
chmod u+x hello
cd ~/catkin_ws
catkin_make
source devel/setup.bash
rosrun simple_arm hello
```

![](https://d17h27t6h515a5.cloudfront.net/topher/2017/May/5911032c_02/02.png)

### Node example: /simple_mover

```bash
cd ~/catkin_ws/src/simple_arm
cd scripts
touch simple_mover
chmod u+x simple_mover
```

Now edit the simple_mover file as follows:
```python
#!/usr/bin/env python

import math
import rospy
from std_msgs.msg import Float64

def mover():
    pub_j1 = rospy.Publisher('/simple_arm/joint_1_position_controller/command',
                             Float64, queue_size=10)
    pub_j2 = rospy.Publisher('/simple_arm/joint_2_position_controller/command',
                             Float64, queue_size=10)
    rospy.init_node('arm_mover')
    rate = rospy.Rate(10)
    start_time = 0

    while not start_time:
        start_time = rospy.Time.now().to_sec()

    while not rospy.is_shutdown():
        elapsed = rospy.Time.now().to_sec() - start_time
        pub_j1.publish(math.sin(2*math.pi*0.1*elapsed)*(math.pi/2))
        pub_j2.publish(math.sin(2*math.pi*0.1*elapsed)*(math.pi/2))
        rate.sleep()

if __name__ == '__main__':
    try:
        mover()
    except rospy.ROSInterruptException:
        pass
```


Then you can run the node using `rosrun`

```bash
# tty1
cd ~/catkin_ws
roslaunch simple_arm robot_spawn.launch

# tty2
cd ~/catkin_ws
source devel/setup.bash
rosrun simple_arm simple_mover
```


### ROS Service
Next up, we'll see another node called arm_mover which implements the safe_move service to allow the arm to be controlled with service calls.

#### Defining services
In Python, a ROS service can be created using the following definition format:

```python
service = rospy.Service('service_name', serviceClassName, handler)
```

