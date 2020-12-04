package com.after;

import java.util.TimerTask;

public class RequestTimerTask extends TimerTask {
  private Process p;
  public RequestTimerTask(Process p) {
    this.p = p;
  }
  @Override
  public void run() throws RuntimeException {
    p.destroy();
    throw new RuntimeException("程序还未结束");
  }
}
