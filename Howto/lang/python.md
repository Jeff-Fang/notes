# Script head
```bash
#!/usr/bin/env python3
```

# Run python in IDLE
## py2:
```py
execfile("./filename")
```

## py3:
```py
exec(open("./filename").read())
```

# Pipenv
```bash
# Install pip
python3 -m pip install --user --upgrade pip
python3 -m pip --version

#Install pipenv
pip install --user pipenv
cd myproject
pipenv install requests
pipenv run python main.py

# Installing virtualenv
python3 -m pip install --user virtualenv
# Creating virtual env
python3 -m virtualenv $venv/py3
# Activating a virtualenv
source $venv/py3/bin/activate
which python
# Leaving virtualenv
deactivate
```
