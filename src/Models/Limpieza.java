package Models;

public class Limpieza extends Producto {

    public enum TipoAplicacion {
        COCINA, BANIO, ROPA, MULTIUSO
    }

    private TipoAplicacion tipoAplicacion;




    public Limpieza() {
    }



    public Limpieza(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, double porcDescuento, boolean esImportado, TipoAplicacion tipoAplicacion) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta, porcDescuento, esImportado);
        if(!id.matches("AZ\\d{3}")){
            throw new IllegalArgumentException("El identificador debe tener el formato AZXXX.");
        }
        this.tipoAplicacion = tipoAplicacion;
        validarPorcentajeGanancia(porcentajeGanancia);
    }



    private void validarPorcentajeGanancia(double porcentajeGanancia) {
        if (tipoAplicacion != TipoAplicacion.COCINA && tipoAplicacion != TipoAplicacion.MULTIUSO) {
            if (porcentajeGanancia < 10 || porcentajeGanancia > 25) {
                throw new IllegalArgumentException("El porcentaje de ganancia para productos de limpieza debe estar entre 10% y 25%, excepto para COCINA y MULTIUSO.");
            }
        } else if (porcentajeGanancia > 25) {
            throw new IllegalArgumentException("El porcentaje de ganancia para productos de limpieza no puede superar el 25%.");
        }
    }

    public void validarPorcentajeDesc(double porcDescuento) {
        if (porcDescuento > 20) {
            throw new IllegalArgumentException("El porcentaje de descuento para productos de limpieza no puede superar el 20%.");
        }
    }

}
