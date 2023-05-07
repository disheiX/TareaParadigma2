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
    public static ArrayList<Client> leerArchivo(String path) throws ParseException {
        List<Client> data = new ArrayList<Client>();

        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String line = buffer.readLine();
            while ((line = buffer.readLine()) != null) {
                String[] temp = line.split(",");
                Utils.parseCliente(temp, data);
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Client>(data);
    }

    public static void parseCliente(String[] array, List<Client> data) throws ParseException {
        Client.Credenciales credenciales = new Client.Credenciales(array[1], Integer.parseInt(array[2]), array[0]);
        Client.Plan plan = new Client.Plan(array[3], array[4]);
        Client.Sede sede = new Client.Sede(array[7], array[8]);
        Client.Fechas fechas = new Client.Fechas(parseFecha(array[5]), parseFecha(array[6]));
        data.add(new Client(credenciales, plan, sede, fechas));
    }

    public static Date parseFecha(String string) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        return parser.parse(string);
    }

    public static String fechaToString(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fecha);
    }

    public static void agregarCliente(String[] array, List<Client> data) throws ParseException {
        if (Verificadores.verificarRut(array[0]) == true &&
                Verificadores.verificarFecha(parseFecha(array[5]), parseFecha(array[6]))) {
        }
    }
}
