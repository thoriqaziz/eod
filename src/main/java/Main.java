import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import model.Eod;
import thread.Thread1;
import thread.Thread2a;
import thread.Thread2b;
import thread.Thread3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Main {
  public static void main(String[] args) throws IOException {
    try {
      FileReader filereader = new FileReader("src/main/resources/Before Eod.csv");

      CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

      CSVReader csvReader = new CSVReaderBuilder(filereader)
        .withCSVParser(parser)
        .withSkipLines(1)
        .build();
      List<String[]> allData = csvReader.readAll();
      List<Eod> eodList = new ArrayList<>();
      Eod eod;
      for (String[] row : allData) {
        eod = Eod.builder()
          .id(row[0])
          .name(row[1])
          .age(row[2])
          .balanced(Float.valueOf(row[3]))
          .thread2b(0)
          .thread3(0)
          .prevBalanced(Float.valueOf(row[4]))
          .avgBalanced(Float.valueOf(row[5]))
          .thread1(0)
          .freeTransfer(Float.valueOf(row[6]))
          .thread2a(0)
          .build();
        eodList.add(eod);
      }

      // Question 1
      Thread1 thread1 = new Thread1(eodList);
      thread1.start();
      eodList.forEach(c -> c.setThread1(thread1.getId()));

      // Question 2a
      Thread2a thread2a = new Thread2a(eodList);
      thread2a.start();
      eodList.forEach(c -> c.setThread2a(thread2a.getId()));

      // Question 2b
      Thread2b thread2b = new Thread2b(eodList);
      thread2b.start();
      eodList.forEach(c -> c.setThread2b(thread2b.getId()));

      // Question 3
      int totalThread3 = 8;
      int totalPerson = 100;
      int personPerThread = totalPerson / totalThread3;

      for (int i = 1; i <= totalThread3; i++) {
        if (i == totalThread3) {
          Thread3 t3 = new Thread3(eodList, (i - 1) * personPerThread, totalPerson - 1);
          t3.start();
        }
        else {
          Thread3 t3 = new Thread3(eodList, (i - 1) * personPerThread, (i * personPerThread) - 1);
          t3.start();
        }
      }

      generateCsv(eodList);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

//  private static Eod calculate(String[] row) throws IOException {
//    float balanced = Float.valueOf(row[3]);
//    float prevBalanced = Float.valueOf(row[4]);
//    float freeTransfer = Float.valueOf(row[6]);
//
//    thread.Thread1 t1 = new thread.Thread1(balanced, prevBalanced);
//    thread.Thread2a t2a = new thread.Thread2a(balanced, freeTransfer);
//    thread.Thread2b t2b = new thread.Thread2b(balanced);
//    t1.start();
//    t2a.start();
//    t2b.start();
//
//    return Eod.builder()
//      .id(row[0])
//      .name(row[1])
//      .age(row[2])
//      .balanced(t2b.getBalanced())
//      .thread2b(t2b.getId())
//      .thread3(0l)
//      .prevBalanced(prevBalanced)
//      .avgBalanced(t1.getAvgBalanced())
//      .thread1(t1.getId())
//      .freeTransfer(t2a.getFreeTransfer())
//      .thread2a(t2a.getId())
//      .build();
//
//  }

  private static void generateCsv(List<Eod> eodList) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter("After Eod.csv"), ';', CSVWriter.NO_QUOTE_CHARACTER);
    try {
      List<String[]> data = toStringArray(eodList);

      writer.writeAll(data);
      writer.flush();
      System.out.println("Csv was successfully created");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<String[]> toStringArray(List<Eod> eodList) {
    List<String[]> records = new ArrayList<String[]>();
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat df = new DecimalFormat("#.#", otherSymbols);

    // adding header record
    records.add(new String[] {
      "id", "Nama", "Age", "Balanced", "No 2b Thread-No", "No 3 Thread-No",
      "Previous Balanced", "Average Balanced", "No 1 Thread-No", "Free Transfer", "No 2a Thread-No" });

    Iterator<Eod> eodIterator = eodList.iterator();
    while (eodIterator.hasNext()) {
      Eod eod = eodIterator.next();
      records.add(new String[] {
        eod.getId(), eod.getName(), eod.getAge(), df.format(eod.getBalanced()), String.valueOf(eod.getThread2b()), String.valueOf(eod.getThread3()),
        df.format(eod.getPrevBalanced()), df.format(eod.getAvgBalanced()), String.valueOf(eod.getThread1()), df.format(eod.getFreeTransfer()), String.valueOf(eod.getThread2a())
      });
    }
    return records;
  }
}