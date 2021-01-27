
# declare -a ipaddr=("119.23.230.129")
declare -a ipaddr=( "47.102.221.62" "47.103.157.226" "47.103.153.117" "47.103.25.12" "47.103.155.229" "47.102.86.21" "47.103.8.167" "47.103.113.123" "47.103.156.4" "47.103.137.18" "47.103.93.24" "47.103.153.51" "47.103.158.11" "47.103.153.77" "47.103.119.8" "47.103.33.98" "47.102.192.244" "47.103.143.226" "47.103.15.159" "47.103.122.59")


for i in "${ipaddr[@]}"
do
  echo "Connecting to $i"
  # ssh -i AliMiRCN.pem -t root@$i 'sudo docker stop $(sudo docker ps -a -q); sudo docker container rm mir263; sudo docker run -p 80:80 -p 502:502 -p 8080:8080 -p 9090:9090 --rm -itd --name="mir263" mirrobots/simulator:2.6.3; exit;'
  # ssh -i AliMiRCN.pem -t root@$i 'sudo docker restart mir263; exit;'
  # ssh -i MiRCN-Sim.pem -t root@$i 'sudo docker restart mir263; exit;'
  # ssh -i AliMiRCN.pem -t root@$i 'sudo reboot;'
  ssh -i AliMiRCN.pem -t root@$i 'docker restart fleet &; exit;'
done
