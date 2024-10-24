import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TasaDeCambioCliente {

    private final HttpClient clienteHttp;
    private final Gson gson;
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/c5e6949ec957e4937aeab7b0/";

    public TasaDeCambioCliente() {
        clienteHttp = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public RespuestaTasaDeCambio obtenerTasaDeCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create((URL_API + "pair/" + monedaOrigen + "/" + monedaDestino))).GET().build();
        HttpResponse<String> respuesta = this.clienteHttp.send(peticion, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("Error al tratar de cosumir el API ExchangeRate [" + respuesta.statusCode() + "]");
        }

        JsonElement respuestaJson = JsonParser.parseString(respuesta.body());

        if (respuestaJson.isJsonObject()) {
            return gson.fromJson(respuesta.body(), RespuestaTasaDeCambio.class);
        }
        else {
            throw new RuntimeException("Error en la respuesta del cliente. No es un objeto JSON");
        }
    }

    public RespuestaMonedasSoportadas obtenerMonedasSoportadas() throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create((URL_API + "/codes"))).GET().build();
        HttpResponse<String> respuesta = this.clienteHttp.send(peticion, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("Error al tratar de cosumir el API ExchangeRate [" + respuesta.statusCode() + "]");
        }

        JsonObject respuestaJson = gson.fromJson(respuesta.body(), JsonObject.class);

        if (respuestaJson.isJsonObject()) {
            RespuestaMonedasSoportadas respuestaMonedasSoportadas = new RespuestaMonedasSoportadas();
            respuestaMonedasSoportadas.setResultado(respuestaJson.get("result").getAsString());
            respuestaMonedasSoportadas.setDocumentacion(respuestaJson.get("documentation").getAsString());
            respuestaMonedasSoportadas.setTerminosDeUso(respuestaJson.get("terms_of_use").getAsString());

            List<Monedas> listadoMonedasSoportadas = new ArrayList<>();
            JsonArray codigosSoportadosArreglo = respuestaJson.getAsJsonArray("supported_codes");

            int id = 1;
            for (JsonElement elemento: codigosSoportadosArreglo){
                JsonArray codigoArreglo = elemento.getAsJsonArray();
                String nombre = codigoArreglo.get(0).getAsString();
                String codigo = codigoArreglo.get(1).getAsString();
                listadoMonedasSoportadas.add(new Monedas(id++,nombre, codigo));
            }
            respuestaMonedasSoportadas.setMonedasSoportadas(listadoMonedasSoportadas);
            return respuestaMonedasSoportadas;
        }
        else {
            throw new RuntimeException("Error en la respuesta del cliente. No es un objeto JSON");
        }
    }

    public RespuestaTasaDeCambio convertirMoneda(String monedaOrigen, String monedaDestino, Double monto) throws IOException, InterruptedException {

        DecimalFormat decimalFormat = new DecimalFormat("0.##");

        HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create((URL_API + "pair/" + monedaOrigen + "/" + monedaDestino + "/" + decimalFormat.format(monto)))).GET().build();
        HttpResponse<String> respuesta = this.clienteHttp.send(peticion, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("Error al tratar de cosumir el API ExchangeRate [" + respuesta.statusCode() + "]");
        }

        JsonElement respuestaJson = JsonParser.parseString(respuesta.body());

        if (respuestaJson.isJsonObject()) {
            return gson.fromJson(respuesta.body(), RespuestaTasaDeCambio.class);
        }
        else {
            throw new RuntimeException("Error en la respuesta del cliente. No es un objeto JSON");
        }
    }
}
