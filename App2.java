import java.text.ParseException;
import java.util.*;

class Main {
  public static void main(String[] args) throws ParseException {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Client> fileData = new ArrayList<Client>();
    ArrayList<Plan> uniquePlans = new ArrayList<Plan>();
    ArrayList<Sede> uniqueSedes = new ArrayList<Sede>();
    Utils.leerArchivo("bigmuscle2.csv", fileData, uniquePlans, uniqueSedes);

    // int resultados = Utils.buscarRut("40-k", fileData); 
    // System.out.println(fileData.get(resultados).getNombre());
    // Utils.parseCliente(Utils.agregarCliente(fileData, uniquePlans, uniqueSedes, scanner), fileData);
    Utils.editCliente(0, uniquePlans, uniqueSedes, fileData, scanner);

    for (int i=0; i<fileData.size(); i++) {
        System.out.println(Arrays.toString(fileData.get(i).getData()));
    }
    // for (int i : resultados) {
    //     if(i == -1) {continue;}
    //     System.out.println(Arrays.toString(fileData.get(i).getData()));
    //     System.out.println(uniqueSedes.get(i).codSede);
    // }
    scanner.close();
	}
}
