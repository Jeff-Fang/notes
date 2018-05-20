#!/bin/bash

printf "Get All Users Home\n"

for userHome in `ls /Users | grep -v -e "Shared"`; do
    echo "User: [$userHome]"
    echo "  home folder: /Users/$userHome"
    echo "  size of home:"`du -xghd0 /Users/$userHome | awk '{print $1}'`
done

exit 0
