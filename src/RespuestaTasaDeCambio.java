import com.google.gson.annotations.SerializedName;

public class RespuestaTasaDeCambio {
    @SerializedName("result")
    private String resultado;

    @SerializedName("documentation")
    private String documentacion;

    @SerializedName("terms_of_use")
    private String terminosDeUso;

    @SerializedName("time_last_update_unix")
    private String ultimaHoraActualizacionUnix;

    @SerializedName("time_last_update_utc")
    private String ultimaHoraActualizacionUtc;

    @SerializedName("time_next_update_unix")
    private String horaProximaActualizacionUnix;

    @SerializedName("time_next_update_utc")
    private String horaProximaActualizacionUtc;

    @SerializedName("base_code")
    private String monedaBase;

    @SerializedName("target_code")
    private String monedaDestino;

    @SerializedName("conversion_rate")
    private Double tasaDeConversion;

    @SerializedName("conversion_result")
    private Double montoConvertido;

    @SerializedName("amount_to_convert")
    private Double montoAConvertir;


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

    public String getUltimaHoraActualizacionUnix() {
        return ultimaHoraActualizacionUnix;
    }

    public void setUltimaHoraActualizacionUnix(String ultimaHoraActualizacionUnix) {
        this.ultimaHoraActualizacionUnix = ultimaHoraActualizacionUnix;
    }

    public String getUltimaHoraActualizacionUtc() {
        return ultimaHoraActualizacionUtc;
    }

    public void setUltimaHoraActualizacionUtc(String ultimaHoraActualizacionUtc) {
        this.ultimaHoraActualizacionUtc = ultimaHoraActualizacionUtc;
    }

    public String getHoraProximaActualizacionUnix() {
        return horaProximaActualizacionUnix;
    }

    public void setHoraProximaActualizacionUnix(String horaProximaActualizacionUnix) {
        this.horaProximaActualizacionUnix = horaProximaActualizacionUnix;
    }

    public String getHoraProximaActualizacionUtc() {
        return horaProximaActualizacionUtc;
    }

    public void setHoraProximaActualizacionUtc(String horaProximaActualizacionUtc) {
        this.horaProximaActualizacionUtc = horaProximaActualizacionUtc;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public Double getTasaDeConversion() {
        return tasaDeConversion;
    }

    public void setTasaDeConversion(Double tasaDeConversion) {
        this.tasaDeConversion = tasaDeConversion;
    }

    public Double getMontoConvertido() {
        return montoConvertido;
    }

    public void setMontoConvertido(Double montoConvertido) {
        this.montoConvertido = montoConvertido;
    }

    public Double getMontoAConvertir() {
        return montoAConvertir;
    }

    public void setMontoAConvertir(Double montoAConvertir) {
        this.montoAConvertir = montoAConvertir;
    }

    @Override
    public String toString() {
        return "RespuestaTasaDeCambio{" +
                "resultado='" + resultado + '\'' +
                ", documentacion='" + documentacion + '\'' +
                ", terminosDeUso='" + terminosDeUso + '\'' +
                ", ultimaHoraActualizacionUnix='" + ultimaHoraActualizacionUnix + '\'' +
                ", ultimaHoraActualizacionUtc='" + ultimaHoraActualizacionUtc + '\'' +
                ", horaProximaActualizacionUnix='" + horaProximaActualizacionUnix + '\'' +
                ", horaProximaActualizacionUtc='" + horaProximaActualizacionUtc + '\'' +
                ", monedaBase='" + monedaBase + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", tasaDeConversion=" + tasaDeConversion +
                ", montoConvertido=" + montoConvertido +
                ", montoAConvertir=" + montoAConvertir +
                '}';
    }
}
