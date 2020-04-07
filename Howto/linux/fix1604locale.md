https://stackoverflow.com/questions/14547631/python-locale-error-unsupported-locale-setting

Run following commands

i```sh
export LC_ALL="en_US.UTF-8"
export LC_CTYPE="en_US.UTF-8"
sudo dpkg-reconfigure locales
```
It will solve this.

Make sure to match the .UTF-8 part to the actual syntax found in the output of locale -a e.g. .utf8 on some systems.
