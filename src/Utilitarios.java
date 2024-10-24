import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilitarios {

    public static double convertirMoneda(Double tasaDeConversion, Double montoAConvertir) {
        return tasaDeConversion * montoAConvertir;
    }

    public static LocalDateTime obtenerFechaACtual() {
        return LocalDateTime.now();
    }

    public static String obtenerFechaActualFormateada() {
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return obtenerFechaACtual().format(formatoFechaHora);
    }

    public static String obtenerValorFormateado(Double valor) {
        DecimalFormat formatoMoneda = new DecimalFormat("#,###.#####");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        formatoMoneda.setDecimalFormatSymbols(simbolos);
        return formatoMoneda.format(valor);
    }

    public static Boolean validarSiEsNumerico(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}