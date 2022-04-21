import cv2
import numpy as np
import pyautogui
import pygetwindow as gw
import sys
import os

def convert_avi_to_mp4(avi_file_path, output_name):
    os.popen("ffmpeg -i '{input}' -ac 2 -b:v 2000k -c:a aac -c:v libx264 -b:a 160k -vprofile high -bf 0 -strict experimental -f mp4 '{output}.mp4'".format(input = avi_file_path, output = output_name))
    return True
window_name = "Will Hero"
fourcc = cv2.VideoWriter_fourcc(*"XVID")
fps = 20.0
record_seconds = int(sys.argv[1])
w = gw.getWindowsWithTitle(window_name)[0]
w.activate()
path = "src/main/java/com/example/game/output.avi"
out = cv2.VideoWriter("src/main/java/com/example/game/output.mp4", fourcc, fps, tuple(w.size))

for i in range(int(record_seconds * fps)):
    img = pyautogui.screenshot(region=(w.left, w.top, w.width, w.height))
    frame = np.array(img)
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    out.write(frame)
    if cv2.waitKey(1) == ord("q"):
        break
cv2.destroyAllWindows()
out.release()