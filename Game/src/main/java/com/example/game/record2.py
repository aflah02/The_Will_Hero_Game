import signal
import time
import cv2
import numpy as np
import pyautogui
import pygetwindow as gw
import sys
import os
print()
print(os.getpid())
print()
def convert_avi_to_mp4(avi_file_path, output_name):
    os.popen("ffmpeg -i '{input}' -ac 2 -b:v 2000k -c:a aac -c:v libx264 -b:a 160k -vprofile high -bf 0 -strict experimental -f mp4 '{output}.mp4'".format(input = avi_file_path, output = output_name))
    return True

class GracefulKiller:
  kill_now = False
  def __init__(self):
    signal.signal(signal.SIGINT, self.exit_gracefully)
    signal.signal(signal.SIGTERM, self.exit_gracefully)

  def exit_gracefully(self, *args):
    self.kill_now = True

if __name__ == '__main__':
    killer = GracefulKiller()
    window_name = "Will Hero"
    fourcc = cv2.VideoWriter_fourcc(*"XVID")
    fps = 20.0
    record_seconds = int(sys.argv[1])
    w = gw.getWindowsWithTitle(window_name)[0]
    w.activate()
    path = "src/main/java/com/example/game/output.avi"
    out = cv2.VideoWriter("src/main/java/com/example/game/output.mp4", fourcc, fps, tuple(w.size))
    while not killer.kill_now:
            img = pyautogui.screenshot(region=(w.left, w.top, w.width, w.height))
            frame = np.array(img)
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            out.write(frame)
            if cv2.waitKey(1) == ord("q"):
                break   
    cv2.destroyAllWindows()
    out.release() 
    print("done")