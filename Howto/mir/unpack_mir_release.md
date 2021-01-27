```bash
echo "MiRPassPhrase" | gpg --homedir /tmp --batch --no-tty --yes --passphrase-fd 0 -o "tmp.zip" MiR_Software-2.10.2.3.mir

echo "MiRSimulatorPassPhrase" | gpg --homedir /tmp --batch --no-tty --yes --passphrase-fd 0 -o "tmp.zip" MiR_Simulator-2.10.2.3.mir
```
