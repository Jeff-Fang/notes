Learn more in [here](http://www.binarytides.com/linux-find-command-examples/)

```bash
find path_A -name "*AAA*" -print0 | xargs -0 -I {} mv {} path_B
find . -name "*C_Primer*" -print0 | xargs -0 -I {} mv {} ../C\ Picked 
find ~/Downloads/ -name "*ckpt" -exec mv '{}' . \;
find . -type f ! -name *.py -delete
