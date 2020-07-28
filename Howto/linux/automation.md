```bash
for i in *.png; do convert "$i" -filter point -resize 50000% "${i%.*}_x50000.png"; done
```
