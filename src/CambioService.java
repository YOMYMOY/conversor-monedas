public class CambioService {
    public static double obtenerMonto(Moneda moneda, Cambio cambio, double monto){
        if (moneda.getTipoCambio().equals("compra")){
            return cambio.compra().doubleValue() * monto;
        }
        return monto / cambio.venta().doubleValue();
    }
}
