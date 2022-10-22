package thread;

import model.Eod;

import java.util.List;

public class Thread2b extends Thread {
  private List<Eod> eodList;

  public Thread2b(List<Eod> eodList) {
    this.eodList = eodList;
  }

  @Override
  public void run() {
    for (Eod eod : eodList) {
      if (eod.getBalanced() > 150) {
        eod.setBalanced(eod.getBalanced() + 25);
        eod.setThread2b(this.getId());
      }
    }
  }
}
