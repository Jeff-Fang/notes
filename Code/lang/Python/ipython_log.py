# IPython log file

get_ipython().magic(u'logstart')
get_ipython().system(u'date')
thedate = get_ipython().getoutput(u'date')
type(thedate)
a = 2+2
a
get_ipython().magic(u'hist')
help arange
m = array([arange(2), arange(2)])
m
type(m)
m.shape
m.dtype
a = arange(5)
a
type(a)
a.shape
uno = array([23,33,33
])
uno
type(uno)
a = m
m
m = array([[1,2],[3,4]])
m
type(m)
m.shape
a = m
a
a[0,0]
a[1,0]
a[1,]
a[,1]
a[ ,1]
a[0,]
x = float64(42)
type(x)
x = bool(42)
x
type(x)
x.dtype
x = float64(42)
x
x.dtype
x.dtype.itemsize
arange(7, dtype='f')
arange(7, dtype='D')
a = arange(9)
a[3:7]
a
a[:7:2]
a[::-
2]
a[::-3]
quit()
