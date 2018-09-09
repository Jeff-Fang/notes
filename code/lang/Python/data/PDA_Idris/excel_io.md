```python2
>>> import numpy as np
>>> import pandas as pd
>>> from tempfile import NamedTemporaryFile
>>>
>>> np.random.seed(42)
>>> a = np.random.randn(365, 4)
>>>
>>> tmpf = NamedTemporaryFile(suffix='.xlsx')
>>> df = pd.DataFrame(a)
>>> print tmpf.name
/var/folders/_g/myz_3bx51yz6d5x68ds1sfdm0000gn/T/tmp9Ok58u.xlsx
>>>
>>> df.to_excel(tmpf.name, sheet_name='Random Data')
>>> print pd.read_excel(tmpf.name, 'Random Data').mean()
0    0.037860
1    0.024483
2    0.059836
3    0.058417
dtype: float64
```

> From Python Data Analysis by Ivan Idris. 
