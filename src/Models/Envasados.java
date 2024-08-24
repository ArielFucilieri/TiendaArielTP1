package Models;

public class Envasados extends Producto {


    private String  fechaVencimiento;
    private int calorias;
    private String tipoEnvase;
    private boolean esComestible;



    public Envasados() {
    }



    public Envasados(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, double porcDescuento, boolean esImportado, String fechaVencimiento, int calorias, String tipoEnvase, boolean esImportado1, boolean esComestible) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta, porcDescuento, esImportado);
        if (!id.matches("AB\\d{3}")){
            throw new IllegalArgumentException("El identificador debe tener el formato ABXXX.");
        }
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        this.tipoEnvase = tipoEnvase;
        this.esComestible = esComestible;
        validarPorcentajeGanancia(porcentajeGanancia);
        validarFechaVencimiento(fechaVencimiento);
    }




    private void validarPorcentajeGanancia(double porcentajeGanancia) {
        if (porcentajeGanancia > 20 && this.esComestible) {
            throw new IllegalArgumentException("El porcentaje de ganancia para productos comestibles no puede superar el 20%.");
        }
    }


    @Override
    public void validarPorcentajeDesc(double porcDescuento) {
        if (porcDescuento > 15) {
            throw new IllegalArgumentException("El porcentaje de descuento para productos envasados no puede superar el 15%.");
        }
    }
}
