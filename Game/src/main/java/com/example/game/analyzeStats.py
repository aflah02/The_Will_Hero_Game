import pandas as pd
from mpl_toolkits import mplot3d
import numpy as np
import matplotlib.pyplot as plt
import os
with open("src\main\java\com\example\game\previousheroLocations.txt", "r") as f:
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
lsdata = list(laststr.strip().split(' '))
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

fig = plt.figure(figsize = (10, 7))
plt.title("Location of Player Over Time")
plt.xlabel("X-Coordinate")
plt.ylabel("Y-Coordinate")
plt.plot(lsx, lsy)
textstr = '\n'.join((
    r'Orcs Killed=%d' % (int(lsdata[0]), ),
    r'TNTs Burst=%d' % (int(lsdata[1]), ),
    r'Score Achieved=%d' % (int(lsdata[2]), ),
    r'Total Time Taken=%d' % (int(lsdata[3]), ),
    r'Number of Times Orcs were Encountered=%d' % (int(lsdata[4]), ),
    r'Number of Swords Collected=%d' % (int(lsdata[5]), ),
    r'Number of Spears Collected=%d' % (int(lsdata[6]), ),
    r'Number of Coin Chests Opened=%d' % (int(lsdata[7]), ),
    ))
xmin, xmax = plt.xlim()
ymin, ymax = plt.ylim()
plt.text(xmin+10, ymax-85, textstr, fontsize = 7)
plt.savefig(os.path.join('src\main\java\com\example\game', 'point.png'))

###########################################

from PIL import Image, ImageDraw

image_path_output = 'src\main\java\com\example\game\/'
image_name_output = 'stats.jpg'

mode = 'RGB'
size = (640, 480)
color = (73, 109, 137)

im = Image.new(mode, size, color)

im.save(image_path_output + image_name_output )

im = Image.open(image_path_output + image_name_output)
position = (50, 50)
message = textstr

draw = ImageDraw.Draw(im)
draw.text(position, message)
image_name_output = 'stats.jpg'
im.save(image_path_output + image_name_output)