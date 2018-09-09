import rosbridge_2_0 as rb
from rosbridge_2_0 import json
from pynput.keyboard import Key, Listener

robot = rb.boot()

forward_linear_vel = [0.3, 0.0, 0.0]
backward_linear_vel = [-0.3, 0.0, 0.0]
right_angular_vel = [0.0, 0.0, -0.4]
left_angular_vel = [0.0, 0.0, 0.4]
empty_vel = [0.0, 0.0, 0.0]

l_vel = empty_vel
a_vel = empty_vel

def on_press(key, l_vel=l_vel, a_vel=a_vel):
    try:
        print('Key {0} pressed'.format(key))
        if key == Key.up:
            print('Start move forward')
            l_vel = forward_linear_vel
        elif key == Key.down:
            print('Start move backward')
            l_vel = backward_linear_vel
        elif key == Key.right:
            print('Start turn right')
            a_vel = right_angular_vel
        elif key == Key.left:
            print('Start turn left')
            a_vel = left_angular_vel

        robot.publish(
                "/cmd_vel",
                {"linear" : {
                    "x": l_vel[0],
                    "y": l_vel[1],
                    "z": l_vel[2],
                    },
                "angular" : {
                    "x": a_vel[0],
                    "y": a_vel[1],
                    "z": a_vel[2],
                    }
                })
    except AttributeError:
        print('Error!')

def on_release(key, l_vel=l_vel, a_vel=a_vel):
    print('{0} released'.format(key))
    try:
        print('Key {0} pressed'.format(key))
        if key == Key.up or key == Key.down:
            l_vel = empty_vel
        elif key == Key.left or key == Key.right:
            a_vel = empty_vel
        elif key == Key.esc:
            return False
        else:
            print('Invalid!')
            l_vel = empty_vel
            a_vel = empty_vel
        robot.publish(
                "/cmd_vel",
                {"linear" : {
                    "x": l_vel[0],
                    "y": l_vel[1],
                    "z": l_vel[2],
                    },
                "angular" : {
                    "x": a_vel[0],
                    "y": a_vel[1],
                    "z": a_vel[2],
                    }
                })
    except AttributeError:
        print('Error!')

with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
