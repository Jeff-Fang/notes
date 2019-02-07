# Running Turtlesim nodes

### To start the `turtlesim_node`,
```bash
rosrun turtlesim turtlesim_node
```

### To start the teleop node,
```bash
rosrun turtlesim turtle_teleop_key
```

### To list all nodes,
```bash
rosnode list
```

So far you will see three nodes: `/rosout`, `/teleop_turtle`, and `/turtlesim`. The `/rosout` is launched by roscore. It subscribes to the standard /rosout topic, the topic to which all nodes send log messages.

### To list all topics,
```bash
rostopic list
```

Now you will see 5 topics listed
- `rosout_agg` Aggregated feed of messages published to /rosout.
- `/turtle1/cmd_vel` Topic upon which velocity commands are sent/received. Publishing a velocity message to this topic will command turtle1 to move.
- `/turtle1/color_sensor` Each turtle in turtlesim is equipped with a color sensor, and readings from the sensor are published to this topic.
- `/turtle1/pose` The position and orientation of turtle1 are published to this topic.

### To Get information about a specific topic
```bash
rostopic info /turtle1/cmd_vel
```

![](https://d17h27t6h515a5.cloudfront.net/topher/2017/May/591b7fbb_l1-20-03/l1-20-03.png)

As would be expected, there are two nodes registered on this topic. Our publisher, the `teleop_turtle` node, and our subscriber, the `turtlesim` node. Additionally, we can see that the type of message used on this topic is `geometry_msgs/Twist`.

### To show message information
```bash
rosmsg info geometry_msgs/Twist
```

![](https://d17h27t6h515a5.cloudfront.net/topher/2017/May/591b80fb_l1-20-04/l1-20-04.png)

We can see that a `Twist` message consists nothing more than two `Vector3` messages. One for linear velocity, and another for angular velocities, with each velocity component being represented by a float64.

**Note**: Sometimes, the message definition won’t provide an ample amount of detail about a message type. For example, in the example above, how can we be sure that linear and angular vectors above refer to velocities, and not positions? One way to get more detail would be to look at the comments in the message’s definition file. To do so, we can issue the following command: `rosed geometry_msgs Twist.msg`.

More information about rosed, including how to select which editor is used by default can be found [here](http://wiki.ros.org/ROS/Tutorials/UsingRosEd).

### To echo messages on a topic
```bash
rostopic echo /turtle1/cmd_vel
```

