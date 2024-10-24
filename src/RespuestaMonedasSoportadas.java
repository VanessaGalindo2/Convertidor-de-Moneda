import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestaMonedasSoportadas {

    @SerializedName("result")
    private String resultado;

    @SerializedName("documentation")
    private String documentacion;

    @SerializedName("terms_of_use")
    private String terminosDeUso;

    @SerializedName("supported_codes")
    private List<Monedas> monedas;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getTerminosDeUso() {
        return terminosDeUso;
    }

    public void setTerminosDeUso(String terminosDeUso) {
        this.terminosDeUso = terminosDeUso;
    }

    public List<Monedas> getMonedasSoportadas() {
        return monedas;
    }

    public void setMonedasSoportadas(List<Monedas> monedas) {
        this.monedas = monedas;
    }

    public String obtenerCodigoMonedaPorId(int id) {
        for (Monedas moneda : monedas) {
            if (moneda.getId() == id) {
                return moneda.getCodigo();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "RespuestaCodigosSoportados{" +
                "resultado='" + resultado + '\'' +
                ", documentacion='" + documentacion + '\'' +
                ", terminosDeUso='" + terminosDeUso + '\'' +
                ", monedas=" + monedas +
                '}';
    }

}
