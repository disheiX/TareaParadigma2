import java.lang.Math;
import java.util.Date;


public class Verificadores 
{
    public static boolean verificarRut(String rut) {
        String[] partes = rut.split("-");
        String digitos = partes[0]; 
        String verificador = partes[1]; 
        if (digitos.length() != 8 || verificador.length() != 1 || partes.length != 2) {
            System.out.println("El rut debe contener 8 digitos y un \"-\" seguido de un código" +
            "verificador. Por favor, inténtelo de nuevo.");
            return false;
        }

        int sum = 0;
        String codigo;
        for (int i = digitos.length() - 1; i >= 0; i--) {
            if (Character.isDigit(digitos.charAt(i)) == false) {
                System.out.println("Los dígitos del rut ingresado no son todos numéricos." +
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

        if (Integer.parseInt(codigo) != Integer.parseInt(verificador)) {
            System.out.println("El rut ingresado no es válido. Por favor, inténtelo de nuevo.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean verificarFecha(Date desde, Date hasta) {
        if (desde.after(hasta)) {
            System.out.println("Las fecha final es menor a la fecha inicial." + 
            "Por favor ingresar fechas válidas.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean verificarPlan() {
        // ...
        return false;
    }
}
