package thread;

import model.Eod;

import java.util.List;

public class Thread1 extends Thread {
  private List<Eod> eodList;

  public Thread1(List<Eod> eodList) {
    this.eodList = eodList;
  }


  @Override
  public void run() {
    for (Eod eod : eodList) {
      eod.setAvgBalanced((eod.getBalanced() + eod.getPrevBalanced()) / 2);
//      eod.setThread1(this.getId());
    }
  }

  public List<Eod> getEodList() {
    return eodList;
  }
}
