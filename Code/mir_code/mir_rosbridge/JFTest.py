# Run the script by Python 2

import rosbridge
import time
import json

robot = None
port = 9090
robot_url = "192.168.12.20"
data = {
        'pose' : [],
        'test' : []
        }

def initRobot():
    try:
        robot = rosbridge.RosbridgeSetup(robot_url, port)
    except ValueError:
        print("Cannot setup robot at \(robot_url) !")

    # Please fill in the topic you want you subscribe as test topic
    robot.subscribe(topic='/f_scan', callback=callback_test_topic)
    robot.subscribe(topic='/robot_pose', callback=callback_robot_pose)

    return robot

def callback_robot_pose(msg):
    data['pose'] = msg

def callback_test_topic(msg):
    data['test'] = msg

robot = initRobot()

while(len(data['pose'])) == 0:
    print("Waiting for pos - 0.5s")
    time.sleep(0.5)

print(json.dumps(data['test'], indent = 2))

# This will list out all the topics available
print(robot.callService("/rosapi/topics"))
