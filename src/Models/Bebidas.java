package Models;

public class Bebidas extends Producto {



    private String fechaVencimiento;
    private double calorias;
    private double graduacionAlcoholica;
    private boolean esComestible;


    public Bebidas() {
    }



    public Bebidas(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, double porcDescuento, boolean esImportado, String fechaVencimiento, double calorias, double graduacionAlcoholica, boolean esImportado1, boolean esComestible) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta, porcDescuento, esImportado);
        if (!id.matches("AC\\d{3}")){
            throw new IllegalArgumentException("El identificador debe tener el formato ACXXX.");
        }
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calcularCalorias(graduacionAlcoholica);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.esComestible = esComestible;
        validarPorcentajeGanancia(porcentajeGanancia);
        validarFechaVencimiento(fechaVencimiento);
    }



    public double calcularCalorias(double graduacionAlcoholica){
        double caloriasFinal;
        if (graduacionAlcoholica <= 2){
            return caloriasFinal = this.calorias;
        } else if (graduacionAlcoholica <=4.5) {
            return caloriasFinal = this.calorias * 1.25;
        }else{
            return caloriasFinal = this.calorias * 1.5;
        }
    }




    private void validarPorcentajeGanancia(double porcentajeGanancia) {
        if (porcentajeGanancia > 20 && this.esComestible) {
            throw new IllegalArgumentException("El porcentaje de ganancia para productos comestibles no puede superar el 20%.");
        }
    }

    public void validarPorcentajeDesc(double porcDescuento) {
        if (porcDescuento > 10) {
            throw new IllegalArgumentException("El porcentaje de descuento para bebidas no puede superar el 10%.");
        }
    }
}
