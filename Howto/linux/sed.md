```bash
sed '/connectionpool\.py\:207/d;/NO\ PLC\ DATA \PROVIDED/d' -i modbus.log.cp | less
```