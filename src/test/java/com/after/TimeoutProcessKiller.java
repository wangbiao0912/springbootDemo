package com.after;

import java.util.TimerTask;

public class TimeoutProcessKiller  extends TimerTask {
  private Process p;
  public TimeoutProcessKiller(Process p) {
    this.p = p;
  }

  public void run() {

    p.destroy();
    throw new RuntimeException("程序还未结束");
  }
}
