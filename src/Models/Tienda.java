package Models;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {

    //*********** Atributos de la clase ***********//
    private String nombre;
    private int maxStock;
    private double saldo;
    private HashMap <String, Producto> stock = new HashMap<>();


    //*********** Constructor vacio *********** //

    public Tienda() {
    }

    //*********** Constructor de la clase ***********//

    public Tienda(String nombre, int maxStock, double saldo) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldo = saldo;
    }


    //*********** Metodos propios ***********//
    public int consultarStock(){
        int stockActual = 0;
        for (Producto p : stock.values()) {
            stockActual += p.getCantidadStock();
        }
        return stockActual;
    }

    //*********** Comprar ***********//
    public double comprarProducto(double precioUnidad, int cantidad) {
        if (this.saldo < precioUnidad * cantidad) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja, " +
                    "el saldo es: "+ this.saldo + " y el precio total del producto por la cantidad es: " +precioUnidad * cantidad);
            return this.saldo;
        }
        this.saldo -= precioUnidad * cantidad;
        System.out.println("Producto agregado, el saldo restante de la caja es: " + this.saldo);
        return this.saldo;
    }


    //*********** Agregar productos ***********//

    public boolean agregarProducto(Producto producto, int cantidad) {
        int stockActual = 0;
        for (Producto p : stock.values()) {
            stockActual += p.getCantidadStock();
        }

        if (stockActual + cantidad > maxStock) {
            System.out.println("No se puede agregar " + producto.getDescripcion() + " a la tienda ya que se alcanzó el máximo de stock, " +
                    "stock maximo: "+ maxStock + " cantidad que se intenta ingresar : " + cantidad);
            return false;
        }

        if (comprarProducto(producto.getPrecioUnidad(), cantidad) < 0) {
            return false;
        }

        producto.setDisponibleVenta(true);
        producto.setCantidadStock(cantidad);
        stock.put(producto.getId(), producto);
        return true;
    }

    //*********** Ventas ***********//

    public boolean realizarVenta(List<Producto> productosVenta, List<Integer> cantidades) {
        if (productosVenta.size() > 3) {
            System.out.println("Error: No se pueden vender más de 3 productos en una sola venta.");
            return false;
        }

        double totalVenta = 0;
        double totalDescuento = 0;

        for (int i = 0; i < productosVenta.size(); i++) {
            Producto producto = productosVenta.get(i);
            int cantidad = cantidades.get(i);

            if (!validarProductoDisponible(producto)) {
                continue;
            }

            Producto productoEnStock = stock.get(producto.getId());
            cantidad = ajustarCantidadDisponible(productoEnStock, cantidad);

            totalVenta += calcularSubtotal(productoEnStock, cantidad);
            totalDescuento += calcularDescuentoProducto(productoEnStock, cantidad);
        }


        saldo += totalVenta;

        imprimirDetalleYMensajes(totalVenta, totalDescuento);

        return true;
    }

    private boolean validarProductoDisponible(Producto producto) {
        if (!stock.containsKey(producto.getId())) {
            System.out.println("El producto " + producto.getId() + " " + producto.getDescripcion() + " no se encuentra disponible.");
            return false;
        }

        Producto productoEnStock = stock.get(producto.getId());

        if (!productoEnStock.isDisponibleVenta()) {
            System.out.println("El producto " + productoEnStock.getId() + " " + productoEnStock.getDescripcion() + " no se encuentra disponible.");
            return false;
        }

        return true;
    }

    private int ajustarCantidadDisponible(Producto productoEnStock, int cantidadSolicitada) {
        if (cantidadSolicitada > 12) {
            System.out.println("No se puede vender más de 12 unidades de un mismo producto.");
            cantidadSolicitada = 12;
        }

        if (productoEnStock.getCantidadStock() < cantidadSolicitada) {
            System.out.println("Hay productos con stock disponible menor al solicitado: " + productoEnStock.getDescripcion());
            cantidadSolicitada = productoEnStock.getCantidadStock();
            productoEnStock.setCantidadStock(0);
            productoEnStock.setDisponibleVenta(false);
        } else {
            productoEnStock.setCantidadStock(productoEnStock.getCantidadStock() - cantidadSolicitada);
        }

        return cantidadSolicitada;
    }

    private double calcularSubtotal(Producto productoEnStock, int cantidad) {
        double precioUnidad = productoEnStock.getPrecioVenta();
        double subtotal = cantidad * precioUnidad;

        return subtotal;
    }

    private double calcularDescuentoProducto(Producto productoEnStock, int cantidad) {
        double descuento = productoEnStock.calcularDescuento();
        double subtotal = calcularSubtotal(productoEnStock, cantidad);
        double precioFinal;

        if (descuento > 0) {
            precioFinal = productoEnStock.getPrecioVenta() - descuento;
            System.out.printf("%s %s %d x %.2f$ (Precio original: %.2f$, Descuento: %.2f%%, Precio final: %.2f$)%n", productoEnStock.getId(),
                    productoEnStock.getDescripcion(), cantidad, precioFinal,
                    productoEnStock.getPrecioVenta(), (descuento / productoEnStock.getPrecioVenta() * 100), precioFinal);
        } else {
            precioFinal = productoEnStock.getPrecioVenta();
            System.out.printf("%s %s %d x %.2f$ (El producto no tiene ningún descuento)%n", productoEnStock.getId(),
                    productoEnStock.getDescripcion(), cantidad, precioFinal);
        }

        return descuento * cantidad;
    }

    private void imprimirDetalleYMensajes(double totalVenta, double totalDescuento) {
        System.out.printf("TOTAL VENTA: %.2f$%n", totalVenta);
        if (totalDescuento > 0) {
            System.out.printf("Total descuento: %.2f$%n", totalDescuento);
        }
    }


    //*********** Requerimientos adicionales ***********//

    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return stock.values().stream()
                .filter(producto -> producto instanceof Bebidas || producto instanceof Envasados)
                .filter(producto -> !producto.isEsImportado())
                .filter(producto -> producto.getPorcDescuento() < porcentajeDescuento)
                .sorted((p1, p2) -> Double.compare(p1.getPrecioVenta(), p2.getPrecioVenta()))
                .map(producto -> producto.getDescripcion().toUpperCase())
                .collect(Collectors.toList());
    }

}
