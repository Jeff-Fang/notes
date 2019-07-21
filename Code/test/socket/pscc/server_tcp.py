import socket

HOST = 'localhost'
PORT = 10102

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen(1)
    conn, addr = s.accept()
    with conn:
        print('Connected by', addr)
        conn.sendall(b'Hello from server.')
        conn.sendall(b'Hello from server.')
        conn.sendall(b'Hello from server.')
        while True:
            data = conn.recv(30)
            print("Server: Data received!")
            if not data:
                print("Error occured while receiving data!")
                break
            print(data)
            if chr(data[-1]) == 'x':
                print('Completed!')
                break
