public class RespuestaHistorialConversion {

    private int id;
    private String horaConversion;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tasaDeCambio;
    private Double montoAConvertir;
    private Double montoConvertido;

    public RespuestaHistorialConversion(String horaConversion, String monedaOrigen, String monedaDestino, Double tasaDeCambio, Double montoAConvertir, Double montoConvertido) {
        this.horaConversion = horaConversion;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tasaDeCambio = tasaDeCambio;
        this.montoAConvertir = montoAConvertir;
        this.montoConvertido = montoConvertido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraConversion() {
        return horaConversion;
    }

    public void setHoraConversion(String horaConversion) {
        this.horaConversion = horaConversion;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public Double getTasaDeCambio() {
        return tasaDeCambio;
    }

    public void setTasaDeCambio(Double tasaDeCambio) {
        this.tasaDeCambio = tasaDeCambio;
    }

    public Double getMontoAConvertir() {
        return montoAConvertir;
    }

    public void setMontoAConvertir(Double montoAConvertir) {
        this.montoAConvertir = montoAConvertir;
    }

    public Double getMontoConvertido() {
        return montoConvertido;
    }

    public void setMontoConvertido(Double montoConvertido) {
        this.montoConvertido = montoConvertido;
    }

    @Override
    public String toString() {
        return "RespuestaHistorialConversion{" +
                "id=" + id +
                ", horaConversion='" + horaConversion + '\'' +
                ", monedaOrigen='" + monedaOrigen + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", tasaDeCambio=" + tasaDeCambio +
                ", montoAConvertir=" + montoAConvertir +
                ", montoConvertido=" + montoConvertido +
                '}';
    }
}
