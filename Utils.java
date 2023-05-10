import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Utils {
    public static void leerArchivo(String path,ArrayList<Client> data, ArrayList<Plan> uniquePlanes, ArrayList<Sede> uniqueSedes) throws ParseException {
        //List<Client> data = new ArrayList<Client>();
        //List<Plan> uniquePlanes = new ArrayList<Plan>();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String line = buffer.readLine();
            while ((line = buffer.readLine()) != null) {
                String[] temp = line.split(",");
                Utils.parseCliente(temp, data);
                Utils.guardarPlanes(temp, uniquePlanes);
                Utils.guardarSedes(temp,uniqueSedes);
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPlanes(String[] array, ArrayList<Plan> uniquePlanes) {
        for (Plan plan : uniquePlanes){
            if (plan.codPlan.equals(array[3])){
                return;
            }
        };
        Plan newPlan = new Plan(array[3].toUpperCase(), array[4]);
        uniquePlanes.add(newPlan);
    }

    public static void guardarSedes(String[] array, ArrayList<Sede> uniqueSedes) {
        for (Sede sede : uniqueSedes){
            if (sede.codSede.equals(array[7])){
                return;
            }
        };
        Sede newSede = new Sede(array[7].toLowerCase(), array[8]);
        uniqueSedes.add(newSede);

    }

    public static String getDesc(ArrayList<Plan> uniquePlans, String codigo) {
        String desc = new String();
        for (Plan plan : uniquePlans) {
            if (plan.codPlan.equals(codigo.toUpperCase())) {
                desc = plan.descPlan;
            }
        };
        return desc;
    }

    public static String getUbic(ArrayList<Sede> uniqueSede, String codigo) {
        String desc = new String();
        for (Sede sede : uniqueSede) {
            if (sede.codSede.equals(codigo.toLowerCase())) {
                desc = sede.ubicSede;
            }
        };
        return desc;
    }

    public static void parseCliente(String[] array, ArrayList<Client> data) throws ParseException {
        Plan plan = new Plan(array[3], array[4]);
        Sede sede = new Sede(array[7], array[8]);
        Client.Fechas fechas = new Client.Fechas(parseFecha(array[5]), parseFecha(array[6]));
        data.add(new Client(array[1], Integer.parseInt(array[2]), array[0], plan, sede, fechas));
    }

    public static Date parseFecha(String string) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        return parser.parse(string);
    }

    public static String fechaToString(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fecha);
    }

    public static String[] agregarCliente(ArrayList<Client> data, ArrayList<Plan> uniquePlans, ArrayList<Sede> uniqueSede, Scanner scanner, String... opcion) throws ParseException {
        // opcion = {opcion a editar, index del cliente}
        String[] array = new String[9];
        ArrayList<String> opciones;
        if (opcion.length == 0) {
            opciones = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        } else {
            array = data.get(Integer.parseInt(opcion[1])).getData();
            opciones = new ArrayList<String>(Arrays.asList(opcion[0]));
        }
        if (opciones.contains("1")) {
            do {
                System.out.print("Ingrese el rut: ");
                array[0] = scanner.nextLine();
            } while (Verificadores.verificarRut(array[0]) == false);
        } 
        if (opciones.contains("2")) {
            do {
                System.out.print("Ingrese el nombre: ");
                array[1] = scanner.nextLine();
            } while (array[1].length() == 0);
            
        }
        if (opciones.contains("3")) {
            do {
                System.out.print("Ingrese la edad: ");
                array[2] = scanner.nextLine();
            } while (Verificadores.esNumerico(array[2]) == false);        
        }
        if (opciones.contains("4")) {
            do {
                System.out.print("Ingrese la fecha de inicio: ");
                array[5] = scanner.nextLine();
                System.out.print("Ingrese la fecha final: ");
                array[6] = scanner.nextLine();
            } while (Verificadores.verificarFecha(array[5], array[6]) == false);                
        }
        if (opciones.contains("5")) {
            do {
                System.out.print("Ingrese la id plan: ");
                array[3] = scanner.nextLine();
            } while (Verificadores.verificarPlan(array[3], uniquePlans) == false);
            array[4] = Utils.getDesc(uniquePlans, array[3]);                
        }
        if (opciones.contains("6")) {
            do {
                System.out.print("Ingrese la id sede: ");
                array[7] = scanner.nextLine();
            } while (Verificadores.verificarSede(array[7], uniqueSede) == false);
            array[8] = Utils.getUbic(uniqueSede, array[7]);                
        }
        return array;
    }

    public static int buscarRut(String rut, ArrayList<Client> fileData) {
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).getRut().equals(rut.toUpperCase()) == true) {
                return i;
            }
        }
        return -1;
    }

    public static int[] buscarNombre(String nombre, ArrayList<Client> fileData) {
        int[] index = new int[fileData.size()];
        int pos = 0;
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).getNombre().toLowerCase().contains(nombre.toLowerCase()) == true) {
                index[pos] = i;
            } else {
                index[pos] = -1;
            }
            pos++;
        }
        return index;
    }

    public static void editCliente(int index, ArrayList<Plan> uniquePlans, ArrayList<Sede> uniqueSede, ArrayList<Client> fileData, Scanner scanner) throws ParseException {
        System.out.println("Ingrese registro a editar:");
        String scan;
        do {
            System.out.println("1) Rut\n2) Nombre\n3) Edad\n4) Fechas \n5) Plan\n6) sede\n7) Salir");
            scan = scanner.nextLine();
        } while (Verificadores.esNumerico(scan) == false);
        
        // opcion = {opcion a editar, index del cliente}
        String[] opcion = new String[] {scan, Integer.toString(index)};
        Utils.parseCliente(Utils.agregarCliente(fileData, uniquePlans, uniqueSede, scanner, opcion), fileData);
        fileData.remove(index);
    }
}
