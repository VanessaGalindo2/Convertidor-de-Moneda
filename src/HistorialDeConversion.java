import java.util.ArrayList;
import java.util.List;

public class HistorialDeConversion {

    private List<RespuestaHistorialConversion> historialDeConversiones;
    private int contadorHistorialConversion;

    public HistorialDeConversion() {
        this.historialDeConversiones = new ArrayList<>();
        this.contadorHistorialConversion = 1;
    }

    public List<RespuestaHistorialConversion> getHistorialDeConversiones() {
        for (int i = 0; i < this.historialDeConversiones.size() -1 ; i++) {
            for (int j = 0; j < historialDeConversiones.size() - i - 1; j++) {
                if (historialDeConversiones.get(j).getId() < historialDeConversiones.get(j + 1).getId()) {
                    RespuestaHistorialConversion historialTemporal = historialDeConversiones.get(j);
                    historialDeConversiones.set(j, historialDeConversiones.get(j + 1));
                    historialDeConversiones.set(j + 1, historialTemporal);
                }
            }
        }
        return this.historialDeConversiones;
    }

    public void agregarHistorialDeConversion(RespuestaHistorialConversion historialDeConversiones) {
        historialDeConversiones.setId(contadorHistorialConversion++);
        this.historialDeConversiones.add(historialDeConversiones);
    }

    @Override
    public String toString() {
        return "HistorialDeConversion{" +
                "historialDeConversiones=" + historialDeConversiones +
                '}';
    }
}

