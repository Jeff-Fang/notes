df -h
du -sh /usr/local/mir/backups

sudo find /usr/local/mir/backups -type f -mtime +65 -exec rm -f {} \;
sudo find /usr/local/mir/upgrades -type f -mtime +65 -exec rm -f {} \;
