import pandas as pd
from mpl_toolkits import mplot3d
import numpy as np
import matplotlib.pyplot as plt

with open("heroLocations.txt", "r") as f:
    data = f.readlines()
ls = []
x = len(data)
print(x)
c = 0
laststr = ""
for i in data:
    if (c == x-1):
        laststr = i
    else:
        c += 1
        ls.append(i.strip().split(' '))
lsx = []
lsy = []
lsp = []
for i in ls:
    lsx.append(i[-3])
    lsy.append(i[-1])
    lsp.append(i[3])
import numpy as np
lsx = np.asarray(lsx, dtype=np.float32)
lsy = np.asarray(lsy, dtype=np.float32)
lsp = np.asarray(lsp, dtype=np.float32)
# Import libraries

 
 # Creating figure
fig = plt.figure(figsize = (10, 7))
# ax = plt.axes(projection ="3d")
# Creating plot
# ax.scatter3D(lsx, lsy, lsp, color = "green")
plt.title("Location of Player Over Time")
plt.xlabel("X-Coordinate")
plt.ylabel("Y-Coordinate")
plt.plot(lsx, lsy)
# plt.ylim([74.5, 75.5])
# show plot
plt.show()
plt.savefig('location.png')


 # Creating figure
fig = plt.figure(figsize = (10, 7))
# ax = plt.axes(projection ="3d")
# Creating plot
# ax.scatter3D(lsx, lsy, lsp, color = "green")
plt.title("Location of Player wrt Points")
plt.xlabel("X-Coordinate")
plt.ylabel("Points")
plt.plot(lsx, lsp)
# plt.ylim([74.5, 75.5])
# show plot
plt.show()
plt.savefig('point.png')

