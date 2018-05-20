hdiutil convert -format UDRW -o ~/path/to/target.img ~/path/to/ubuntu.iso

diskutil list
diskutil unmountDisk /dev/diskN

sudo dd if=/path/to/img.dmg of=/dev/rdiskN bs=1m

diskutil eject /dev/diskN
