import cv2
import numpy as np
import pyautogui
import pygetwindow as gw
import sys
import os

def convert_avi_to_mp4(avi_file_path, output_name):
    os.popen("ffmpeg -i '{input}' -ac 2 -b:v 2000k -c:a aac -c:v libx264 -b:a 160k -vprofile high -bf 0 -strict experimental -f mp4 '{output}.mp4'".format(input = avi_file_path, output = output_name))
    return True

# the window name, e.g "notepad", "Chrome", etc.
window_name = "Will Hero"

# define the codec
fourcc = cv2.VideoWriter_fourcc(*"XVID")
# frames per second
fps = 20.0
# the time you want to record in seconds
record_seconds = int(sys.argv[1])
# search for the window, getting the first matched window with the title
w = gw.getWindowsWithTitle(window_name)[0]
# activate the window
w.activate()
# create the video write object
path = "src/main/java/com/example/game/output.avi"
out = cv2.VideoWriter("src/main/java/com/example/game/output.mp4", fourcc, fps, tuple(w.size))

for i in range(int(record_seconds * fps)):
    # make a screenshot
    img = pyautogui.screenshot(region=(w.left, w.top, w.width, w.height))
    # convert these pixels to a proper numpy array to work with OpenCV
    frame = np.array(img)
    # convert colors from BGR to RGB
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    # write the frame
    out.write(frame)
    # show the frame
    # cv2.imshow("screenshot", frame)
    # if the user clicks q, it exits
    if cv2.waitKey(1) == ord("q"):
        break

# make sure everything is closed when exited
cv2.destroyAllWindows()
out.release()
# convert_avi_to_mp4(path, "superfunrecording")