package thread;

import model.Eod;

import java.util.List;

public class Thread3 extends Thread {
  private List<Eod> eodList;
  private int start;
  private int end;

  public Thread3(List<Eod> eodList, int start, int end) {
    this.eodList = eodList;
    this.start = start;
    this.end = end;
  }

  @Override
  public void run() {
    for (int i = start; i <= end; i++) {
      eodList.get(i).setBalanced(eodList.get(i).getBalanced() + 10);
      eodList.get(i).setThread3(this.getId());
    }
  }

  public List<Eod> getEodList() {
    return eodList;
  }
}
