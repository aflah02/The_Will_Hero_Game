import cv2 as cv
import numpy as np
import os
from time import time
from windowcapture import WindowCapture
from win32api import GetSystemMetrics
# Change the working directory to the folder this script is in.
# Doing this because I'll be putting the files from each video in their own folder on GitHub
os.chdir(os.path.dirname(os.path.abspath(__file__)))
fourcc = cv.VideoWriter_fourcc(*"MP4V")

w = GetSystemMetrics(0)
h =  GetSystemMetrics(1)
# initialize the WindowCapture class
wincap = WindowCapture('Will Hero')
out = cv.VideoWriter("C:/Users/ASUS/Desktop/The_Will_Hero_Game/Game/src/main/java/com/example/game/recording2.mp4", fourcc, 20.0, (wincap.h, wincap.w))
while(True):
    # get an updated image of the game
    screenshot = wincap.get_screenshot()
    out.write(screenshot)
    cv.imshow('Computer Vision', screenshot)

    # press 'q' with the output window focused to exit.
    # waits 1 ms every loop to process key presses
    if cv.waitKey(1) == ord('q'):
        out.release()
        cv.destroyAllWindows()
        break
# out.release()
print('Done.')