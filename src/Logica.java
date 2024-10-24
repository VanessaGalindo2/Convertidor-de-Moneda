import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Logica {

    private TasaDeCambioCliente cliente;
    private HistorialDeConversion historialDeConversion;
    private RespuestaMonedasSoportadas monedasSoportadas;

    public Logica() {
        cliente = new TasaDeCambioCliente();
        historialDeConversion = new HistorialDeConversion();
    }

    public void lanzarAplicacion() throws IOException, InterruptedException {
        String opcion;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===============================================================" +
                    "\n=========== *************************************** ===========" +
                    "\n=========== **** Conversor de tasas de cambio  **** ===========" +
                    "\n=========== *************************************** ===========" +
                    "\n===============================================================");
            do {
                System.out.println("\n===============================================================");
                System.out.println("====================== Menú de opciones: ======================");
                System.out.println("===============================================================");
                System.out.println("[1] Listar monedas base soportadas");
                System.out.println("[2] Listar todas las monedas soportadas por la API ExchangeRate");
                System.out.println("[3] Ver historial de conversiones realizadas");
                System.out.println("[4] Realizar conversión entre monedas");
                System.out.println("[0] Salir de la aplicación");
                System.out.println("===============================================================");

                monedasSoportadas = this.obtenerMonedasSoportadas();

                opcion = scanner.nextLine();
                if (Utilitarios.validarSiEsNumerico(String.valueOf(opcion))) {
                    switch (opcion) {
                        case "1":
                            monedasSoportadas = this.obtenerMonedasSoportadas();
                            this.imprimirListadoMonedasSoportadas(monedasSoportadas);
                            break;
                        case "2":
                            monedasSoportadas = this.cliente.obtenerMonedasSoportadas();
                            this.imprimirListadoMonedasSoportadas(monedasSoportadas);
                            break;
                        case "3":
                            this.imprimirHistorialDeConversiones();
                            break;
                        case "4":
                            this.convertirMoneda(false);
                            break;
                        case "0":
                            System.out.println("\nCerrando aplicación...\n¡¡¡Vuelva pronto!!!");
                    }
                } else {
                    System.err.println("\n¡¡¡La opción ingresada debe ser númerica!!!");
                }
            } while (!Objects.equals(opcion, "0"));

            scanner.close();
        } catch (Exception e) {
            System.err.println("\n" + e.getMessage());
        }
    }

    private RespuestaMonedasSoportadas obtenerMonedasSoportadas() {
        RespuestaMonedasSoportadas respuestaMonedasSoportadas = new RespuestaMonedasSoportadas();
        List<Monedas> monedasSoportadas = new ArrayList<>();

        monedasSoportadas.add(new Monedas(1, "ARS", "Argentine Peso"));
        monedasSoportadas.add(new Monedas(2, "BOB", "Bolivian Boliviano"));
        monedasSoportadas.add(new Monedas(3, "BRL", "Brazilian Real"));
        monedasSoportadas.add(new Monedas(4, "COP", "Colombian Peso"));
        monedasSoportadas.add(new Monedas(5, "CLP", "Chilean Peso"));
        monedasSoportadas.add(new Monedas(6, "USD", "United States Dollar"));
        respuestaMonedasSoportadas.setMonedasSoportadas(monedasSoportadas);

        return respuestaMonedasSoportadas;
    }

    private void convertirMoneda(Boolean usarApi) throws IOException, InterruptedException {
        System.out.println("\n=================== Conversión de moneda ======================");

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el ID de la moneda de origen:");
        String monedaOrigen = scanner.nextLine();

        System.out.println("\nIngrese el ID de la moneda de destino:\n");
        String monedaDestino = scanner.nextLine();

        if (Utilitarios.validarSiEsNumerico(monedaOrigen) && Utilitarios.validarSiEsNumerico(monedaDestino)) {
            String codigoMonedaOrigen = this.monedasSoportadas.obtenerCodigoMonedaPorId(Integer.parseInt(monedaOrigen));
            String codigoMonedaDestino = this.monedasSoportadas.obtenerCodigoMonedaPorId(Integer.parseInt(monedaDestino));

            if (codigoMonedaOrigen != null && codigoMonedaDestino != null) {
                RespuestaTasaDeCambio respuestaTasaDeCambio = cliente.obtenerTasaDeCambio(this.monedasSoportadas.obtenerCodigoMonedaPorId(Integer.parseInt(monedaOrigen)),
                        this.monedasSoportadas.obtenerCodigoMonedaPorId(Integer.parseInt(monedaDestino)));

                System.out.println("\n====================== Tasa de cambio =========================");
                System.out.println("Moneda origen: " + codigoMonedaOrigen);
                System.out.println("Moneda destino: " + codigoMonedaDestino);
                System.out.println("Tasa de cambio: " + Utilitarios.obtenerValorFormateado(respuestaTasaDeCambio.getTasaDeConversion()));
                System.out.println("===============================================================");

                System.out.println("\nIngrese monto a convertir:\nEl formato debe tener el formato #######.##");

                String montoAConvertir = scanner.nextLine();

                if (Utilitarios.validarSiEsNumerico(String.valueOf(montoAConvertir))) {
                    respuestaTasaDeCambio.setMontoAConvertir(Double.parseDouble(montoAConvertir));

                    if (usarApi) {
                        respuestaTasaDeCambio = cliente.convertirMoneda(respuestaTasaDeCambio.getMonedaBase(), respuestaTasaDeCambio.getMonedaDestino(), respuestaTasaDeCambio.getMontoAConvertir());
                        respuestaTasaDeCambio.setMontoConvertido(respuestaTasaDeCambio.getMontoConvertido());
                    } else {
                        respuestaTasaDeCambio.setMontoConvertido(Utilitarios.convertirMoneda(respuestaTasaDeCambio.getTasaDeConversion(), respuestaTasaDeCambio.getMontoAConvertir()));
                    }

                    this.agregarHistorialDeConversion(respuestaTasaDeCambio);

                    System.out.println("\n================= Resumen de la conversión ====================");
                    System.out.println("Moneda origen: " + codigoMonedaOrigen);
                    System.out.println("Moneda destino: " + codigoMonedaDestino);
                    System.out.println("Tasa de cambio: " + Utilitarios.obtenerValorFormateado(respuestaTasaDeCambio.getTasaDeConversion()));
                    System.out.println("Monto a convertir: " + Utilitarios.obtenerValorFormateado(Double.parseDouble(montoAConvertir)));
                    System.out.println("Monto convertido: " + Utilitarios.obtenerValorFormateado(respuestaTasaDeCambio.getMontoConvertido()));
                    System.out.println("===============================================================");
                } else {
                    System.err.println("\n¡¡¡El monto a convertir debe ser númerico!!!");
                }
            } else {
                System.err.println("\n¡¡¡El ID de la moneda origen [" + monedaOrigen + "] o moneda destino [" +
                        monedaDestino + "] no esta en el listado!!!");
            }
        } else {
            System.err.println("\n¡¡¡El ID ingresado de la moneda de origen y destino debe ser númerico!!!");
        }
    }

    private void imprimirListadoMonedasSoportadas(RespuestaMonedasSoportadas monedasSoportadas) {
        System.out.println("\n====================================================");
        System.out.println("==== Listado de monedas soportadas disponibles: ====");
        System.out.println("====================================================");
        for (Monedas elemento : monedasSoportadas.getMonedasSoportadas()) {
            System.out.println("[" + elemento.getId() + "] " + elemento.getCodigo() + " - " + elemento.getNombre());
        }
    }

    private void agregarHistorialDeConversion(RespuestaTasaDeCambio respuestaTasaDeCambio) {
        historialDeConversion.agregarHistorialDeConversion(new RespuestaHistorialConversion(
                Utilitarios.obtenerFechaActualFormateada(),
                respuestaTasaDeCambio.getMonedaBase(),
                respuestaTasaDeCambio.getMonedaDestino(),
                respuestaTasaDeCambio.getTasaDeConversion(),
                respuestaTasaDeCambio.getMontoAConvertir(),
                respuestaTasaDeCambio.getMontoConvertido()));
    }

    private void imprimirHistorialDeConversiones() {
        System.out.println("\n====================================================");
        System.out.println("============ Historial de conversiones: ============");
        System.out.println("====================================================");
        for (RespuestaHistorialConversion elemento : historialDeConversion.getHistorialDeConversiones()) {
            System.out.println("ID de la conversión: " + elemento.getId());
            System.out.println("Fecha/Hora de la conversión: " + elemento.getHoraConversion());
            System.out.println("Moneda origen: " + elemento.getMonedaOrigen());
            System.out.println("Moneda destino: " + elemento.getMonedaDestino());
            System.out.println("Tasa de cambio: " + Utilitarios.obtenerValorFormateado(elemento.getTasaDeCambio()));
            System.out.println("Monto a convertir: " + Utilitarios.obtenerValorFormateado(elemento.getMontoAConvertir()));
            System.out.println("Monto convertido: " + Utilitarios.obtenerValorFormateado(elemento.getMontoConvertido()));
            System.out.println("====================================================");
        }
    }
}
