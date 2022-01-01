import pandas as pd
from mpl_toolkits import mplot3d
import numpy as np
import matplotlib.pyplot as plt
# fig, ax = plt.subplots()
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
lsdata = [1,2,3,4,5,6,7,8]
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
textstr = '\n'.join((
    r'Orcs Killed=%d' % (lsdata[0], ),
    r'TNTs Burst=%d' % (lsdata[1], ),
    r'Score Achieved=%d' % (lsdata[2], ),
    r'Total Time Taken=%d' % (lsdata[3], ),
    r'Number of Times Orcs were Encountered=%d' % (lsdata[4], ),
    r'Number of Swords Collected=%d' % (lsdata[5], ),
    r'Number of Spears Collected=%d' % (lsdata[6], ),
    r'Number of Coin Chests Opened=%d' % (lsdata[7], ),
    ))
# ax.text(0.05, 0.95, textstr, transform=ax.transAxes, fontsize=14,
#         verticalalignment='top', bbox=props)
plt.text(0, 0, textstr, fontsize = 10)
plt.show()
plt.savefig('location.png')


#  Creating figure
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
textstr = '\n'.join((
    r'Orcs Killed=%d' % (lsdata[0], ),
    r'TNTs Burst=%d' % (lsdata[1], ),
    r'Score Achieved=%d' % (lsdata[2], ),
    r'Total Time Taken=%d' % (lsdata[3], ),
    r'Number of Times Orcs were Encountered=%d' % (lsdata[4], ),
    r'Number of Swords Collected=%d' % (lsdata[5], ),
    r'Number of Spears Collected=%d' % (lsdata[6], ),
    r'Number of Coin Chests Opened=%d' % (lsdata[7], ),
    ))
# ax.text(0.05, 0.95, textstr, transform=ax.transAxes, fontsize=14,
#         verticalalignment='top', bbox=props)
plt.text(70, 360, textstr, fontsize = 7)
plt.show()
plt.savefig('point.png')

