import java.util.ArrayList;

public class WierszTrojkataPascala {
    private ArrayList<Integer> ostatniWiersz = new ArrayList<>();

    public WierszTrojkataPascala(int n) {

    if (n < 0) {
      return;
    }

    ostatniWiersz.add(1);

    for (int i = 0; i < n; i++) {
      ArrayList<Integer> nowyWiersz = new ArrayList<>();
      nowyWiersz.add(1);
      for (int k = 0; k < ostatniWiersz.size() - 1; k++) {
        nowyWiersz.add(ostatniWiersz.get(k) + ostatniWiersz.get(k + 1));
      }
      nowyWiersz.add(1);
      ostatniWiersz = nowyWiersz;
    }
     
  }

  public ArrayList<Integer> getWiersz() {
      return ostatniWiersz;
  }
}
