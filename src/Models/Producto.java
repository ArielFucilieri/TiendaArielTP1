package Models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Producto {


    private String id;
    private String descripcion;
    private int cantidadStock;
    private double precioUnidad;
    private double porcentajeGanancia;
    private boolean disponibleVenta = true;
    private double porcDescuento;
    private boolean esImportado;
    private double precioVenta;


    public Producto() {
    }


    public Producto(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, double porcDescuento, boolean esImportado) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioUnidad = precioUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponibleVenta = disponibleVenta;
        this.porcDescuento = porcDescuento;
        this.esImportado = esImportado;
        this.precioVenta = calcularPrecioVenta();
    }


    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public boolean isDisponibleVenta() {
        return disponibleVenta;
    }

    public double getPorcDescuento() {
        return porcDescuento;
    }

    public boolean isEsImportado() {
        return esImportado;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }


    public void setDisponibleVenta(boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }


    public double calcularDescuento() {
        return precioUnidad * (porcDescuento / 100);
    }

    public double calcularPrecioVenta() {
        double precioBase = precioUnidad;

        precioBase +=   precioBase * (porcentajeGanancia / 100);

        if (esImportado) {
            precioBase +=  precioBase * 0.12;
        }


        return precioBase;
    }




    public void validarPorcentajeDesc(double porcDescuento){}

    public void validarFechaVencimiento(String fechaVencimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate fecha = LocalDate.parse(fechaVencimiento, formatter);
            if (fecha.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("El producto ya esta vencido, no se puede agregar al stock");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha de vencimiento " + fechaVencimiento + " no es válida.");
        }
    }
}


