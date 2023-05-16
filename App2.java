import java.io.IOException;
import java.text.ParseException;
import java.util.*;

class Main {
    public static void main(String[] args) throws ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Client> fileData = new ArrayList<Client>();
        ArrayList<Plan> uniquePlans = new ArrayList<Plan>();
        ArrayList<Sede> uniqueSedes = new ArrayList<Sede>();
        Utils.leerArchivo("bigmuscle2.csv", fileData, uniquePlans, uniqueSedes);
        Utils.menu(fileData, uniquePlans, uniqueSedes, scanner);
        // Utils.guardarArchivo("bigmuscle2.csv", "bigmuscle2.bak", fileData);
        scanner.close();
    }
}
