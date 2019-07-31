#!/bin/bash

WIFILIST="wifilist.txt"

if [ -s "$WIFILIST" ]; then
    echo "Reading List..."
else
    echo "Searching WiFi..."
    nmcli device wifi list | awk '{print $1}' > $WIFILIST
fi

while read line
do
    sed -i '1d' $WIFILIST
    if [ "$line" = "*" ]; then
        echo "Asterisk detected"
    elif [[ "$line" =~ JF* ]]; then
        nmcli device wifi connect "$line" password 12345678
        nmcli device
        exit
    elif [[ "$line" =~ M100m* ]]; then
        nmcli device wifi connect "$line" password ap121002
        nmcli device
        exit
    else
        echo "Skipping '$line'"
        continue
    fi
done < $WIFILIST

echo "File empty. Not doing anything."
