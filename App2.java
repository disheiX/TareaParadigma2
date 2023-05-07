import java.text.ParseException;
import java.util.*;

class Main {
  public static void main(String[] args) throws ParseException {
        ArrayList<Client> fileData = Utils.leerArchivo("bigmuscle2.csv");
        for (int i = 0; i < fileData.size(); i++) {
            System.out.println(Arrays.toString(fileData.get(i).getData()));
        }
	}
}
