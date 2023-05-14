import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Verificadores 
{
    public static boolean verificarRut(String rut) {
        String[] partes = rut.split("-");
        if (partes.length != 2) {
            System.out.println("El rut debe contener y un \"-\" seguido de un código" +
            " verificador. Por favor, inténtelo de nuevo.");
            return false;
        }
        String digitos = partes[0];
        String verificador = partes[1];  
        int sum = 0;
        String codigo;
        for (int i = digitos.length() - 1; i >= 0; i--) {
            if (Character.isDigit(digitos.charAt(i)) == false) {
                System.out.println("Los dígitos del rut ingresado no son todos numéricos. " +
                   "Por favor, inténtelo de nuevo.");
                return false;
            }
            sum += (((digitos.length() - i - 1) % 6) + 2) * Character.getNumericValue(digitos.charAt(i));
        }

        int result = 11 - Math.abs(sum - ((int) sum / 11) * 11);
        if (result == 10) {
            codigo = "K";
        } else if (result == 11) {
            codigo = "0";
        } else {
            codigo = Integer.toString(result);
        }

        if (codigo.equals(verificador.toUpperCase()) == false) {
            System.out.println("El rut ingresado no es válido. Por favor, inténtelo de nuevo.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean verificarSede(String codigo, ArrayList<Sede> uniqueSedes) {
        for (Sede sede : uniqueSedes) {
            if (sede.codSede.equals(codigo.toLowerCase())) {
                return true;
            }
        };
        System.out.println("No existe la sede " + codigo + ". Las sedes que existen son:");
        for (Sede sede : uniqueSedes) {
            System.out.println(sede.codSede);
        }
        return false;
    }

    public static boolean verificarPlan(String codigo, ArrayList<Plan> uniquePlans) {
        for (Plan plan : uniquePlans) {
            if (plan.codPlan.equals(codigo.toUpperCase())) {
                return true;
            }
        };
        System.out.println("No existe el plan " + codigo + ". Los planes que existen son:");
        for (Plan plan : uniquePlans) {
            System.out.println(plan.codPlan);
        }
        return false;
    }

    public static boolean esNumerico(String numero) { 
        try {  
            Double.parseDouble(numero);  
            return true;
        } catch(NumberFormatException e){
            System.out.println("El valor ingresado no es numérico. Intenta de nuevo.");
            return false;  
        }  
    }

    public static boolean verificarFecha(String desde, String hasta) { 
        try {  
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            if (parser.parse(desde).before(parser.parse(hasta)) == true) {
                return true;
            } else {
                System.out.println("Fecha inválida. Inténtalo de nuevo.");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Fecha inválida. Inténtalo de nuevo.");
            return false;
        }  
    }
}
