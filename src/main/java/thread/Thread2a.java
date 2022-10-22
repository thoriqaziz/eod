package thread;

import model.Eod;

import java.util.List;

public class Thread2a extends Thread {
  private List<Eod> eodList;

  public Thread2a(List<Eod> eodList) {
    this.eodList = eodList;
  }

  @Override
  public void run() {
    for (Eod eod : eodList) {
      if (eod.getBalanced() >= 100 && eod.getBalanced() <= 150) {
        eod.setFreeTransfer(5);
        eod.setThread2a(Thread.currentThread().getId());
      }
    }
  }
}
