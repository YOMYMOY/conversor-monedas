public enum Moneda {
    USDREAL(3, "cotizaciones/brl", "compra", "USD", "REAL"),
    REALUSD(4, "cotizaciones/brl", "venta", "REAL", "USD"),
    USDCHIL(5, "cotizaciones/clp", "compra", "USD", "CLP"),
    CHILUSD(6, "cotizaciones/clp", "venta", "CLP", "USD"),
    USDOFARS(8, "dolares/oficial", "compra", "USD OFICIAL", "ARS"),
    USDBLUEARS(9, "dolares/blue", "compra", "USD BLUE", "ARS"),
    USDBOLARS(10, "dolares/bolsa", "compra", "USD BOLSA", "ARS"),
    USDTARARS(11, "dolares/tarjeta", "compra", "USD TARJETA", "ARS"),
    ARSUSDOF(12, "dolares/oficial", "venta", "ARS", "USD OFICIAL"),
    ARSUSDBLUE(13, "dolares/blue", "venta", "ARS", "USD BLUE"),
    ARSUSDBOL(14, "dolares/bolsa", "venta", "ARS", "USD BOLSA"),
    ARSUSDTAR(15, "dolares/tarjeta", "venta", "ARS", "USD TARJETA"),
    INVALIDO(16, "", "", "", "");

    private final int opcion;
    private final String urlMoneda;
    private final String tipoCambio;
    private final String monedaOrigen;
    private final String monedaDestino;

    Moneda(int opcion, String urlMoneda, String tipoCambio, String monedaOrigen, String monedaDestino){
        this.opcion = opcion;
        this.urlMoneda = urlMoneda;
        this.tipoCambio = tipoCambio;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
    }

    public int getOpcion() {
        return opcion;
    }

    public String getUrlMoneda() {
        return urlMoneda;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public static Moneda elegirOpcion(int opcionElegida){
        for (Moneda moneda : Moneda.values()){
            if (moneda.opcion == opcionElegida){
                return moneda;
            }
        }
        return INVALIDO;
    }
}
