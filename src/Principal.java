import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Gson gson = new Gson();

        String menu = """
                *************************
                \nConversor de monedas:
                
                1) Dólar a Peso argentino
                2) Peso argentino a Dólar
                3) Dólar a Real brasileño
                4) Real brasileño a Dólar
                5) Dólar a Peso chileno
                6) Peso chileno a Dólar
                7) Salir
                *************************
                """;
        String menuDolar = """
                \n*************************
                1) Dólar Oficial a Peso argentino
                2) Dólar Blue a Peso argentino
                3) Dólar Bolsa a Peso argentino
                4) Dólar Tarjeta a Peso argentino
                5) Salir
                *************************
                """;
        String menuPeso = """
                \n*************************
                1) Peso argentino a Dólar Oficial
                2) Peso argentino a Dólar Blue
                3) Peso argentino a Dólar Bolsa
                4) Peso argentino a Dólar Tarjeta
                5) Salir
                *************************
                """;
        while(true){
            System.out.println(menu);
            var opcion = lectura.nextInt();
            if(opcion == 7){
                System.out.println("Muchas gracias por utilizar nuestro servicio.");
                break;
            }
            if (opcion >= 8){
                System.out.println("Opción inválida. Intenta nuevamente.\n");
                continue;
            }
            if(opcion == 1){
                System.out.println(menuDolar);
                opcion = lectura.nextInt();
                if (opcion == 5){
                    continue;
                }
                opcion += 7;
            }
            if (opcion == 2){
                System.out.println(menuPeso);
                opcion = lectura.nextInt();
                if (opcion == 5){
                    continue;
                }
                opcion += 11;
            }
            Moneda monedaElegida = Moneda.elegirOpcion(opcion);
            if (monedaElegida == Moneda.INVALIDO) {
                System.out.println("Opción inválida. Intenta nuevamente.");
                continue;
            }
            String direccion ="https://dolarapi.com/v1/" + monedaElegida.getUrlMoneda();
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                Cambio cambio = gson.fromJson(json, Cambio.class);

                System.out.println("Ingrese el monto a convertir: ");
                var monto = lectura.nextDouble();

                double montoConvertido = CambioService.obtenerMonto(monedaElegida, cambio, monto);

                System.out.println("El valor de " + String.format("%.2f", monto) +
                        " [" + monedaElegida.getMonedaOrigen() + "] es igual a " +
                        String.format("%.2f", montoConvertido) +
                        " [" + monedaElegida.getMonedaDestino() + "]");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
