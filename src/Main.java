import Models.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear una tienda con un saldo inicial

         Tienda tiendaAriel = new Tienda("TiendaAriel", 1000, 5000);

        // Crear productos de cada tipo

            System.out.println("********************* Creando productos Bebidas *********************");
            Bebidas cepita = new Bebidas("AC003", "Jugo", 30, 40, 12, true, 10, true, "2025-01-01", 80, 0, true, true);
            Bebidas jhonny = new Bebidas("AC004", "Whisky", 15, 150, 18, true, 12, false, "2026-12-31", 300, 20, false, true);
            Bebidas ron = new Bebidas("AC005", "Ron", 10, 120, 22, true, 18, true, "2026-06-30", 250, 15, true, false);
            Bebidas tequila = new Bebidas("AC006", "Tequila", 5, 22200, 25, true, 10, true, "2025-08-20", 350, 30, false, false);

            System.out.println("********************* Creando productos Envasados *********************");
            Envasados azucar = new Envasados("AB003", "Azúcar", 30, 25, 15, true, 6, false, "2026-03-15", 180, "Bolsa", true, true);
            Envasados harina = new Envasados("AB004", "Harina", 25, 35, 18, true, 10, false, "2025-11-22", 300, "Bolsa", true, true);
            Envasados sal = new Envasados("AB005", "Sal", 15, 20, 20, true, 12, false, "2025-05-18", 250, "Bolsa", true, true);
            Envasados fideos = new Envasados("AB006", "Fideos", 35, 30, 12, true, 7, false, "2025-04-27", 280, "Caja", true, true);

            System.out.println("********************* Creando productos de Limpieza *********************");
            Limpieza lavandina = new Limpieza("AZ003", "Lavandina", 30, 35, 18, true, 8, false, Limpieza.TipoAplicacion.MULTIUSO);
            Limpieza jabon = new Limpieza("AZ004", "Jabón Líquido", 15, 25, 22, true, 10, false, Limpieza.TipoAplicacion.COCINA);
            Limpieza lipiavidrios = new Limpieza("AZ005", "Limpiavidrios", 20, 28, 25, true, 15, false, Limpieza.TipoAplicacion.MULTIUSO);
            Limpieza limpiador = new Limpieza("AZ006", "Limpiador de pisos", 25, 30, 19, true, 12, false, Limpieza.TipoAplicacion.MULTIUSO);



            System.out.println("********************* Agregando productos a la tienda *********************");
            tiendaAriel.agregarProducto(cepita, 8);
            tiendaAriel.agregarProducto(jhonny, 3);
            tiendaAriel.agregarProducto(azucar, 15);
            tiendaAriel.agregarProducto(harina, 8);
            tiendaAriel.agregarProducto(lavandina, 10);
            tiendaAriel.agregarProducto(jabon, 25);

            System.out.println("********************* Consultar stock *********************");

            System.out.println("El stock es de: "+tiendaAriel.consultarStock() + " productos totales");

            System.out.println("********************* Maximo Stock *********************");
            tiendaAriel.agregarProducto(jabon, 5000);


            System.out.println("********************* Saldo Insuficiente *********************");
            tiendaAriel.agregarProducto(tequila,3);
            System.out.println("********************* Venta de producto e impresion del detalle de compra *********************");
            List<Producto> productosVenta = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();

            productosVenta.add(cepita);
            productosVenta.add(azucar);
            cantidades.add(8);
            cantidades.add(3);


            tiendaAriel.realizarVenta(productosVenta,cantidades);
            System.out.println("El stock actual es de: "+ tiendaAriel.consultarStock());

            System.out.println("********************* Error mas de 3 productos *********************");
            List<Producto> productosVenta2 = new ArrayList<>();
            List<Integer> cantidades2 = new ArrayList<>();
            productosVenta2.add(harina);
            productosVenta2.add(azucar);
            productosVenta2.add(jabon);
            productosVenta2.add(azucar);
            cantidades2.add(8);
            cantidades2.add(5);
            cantidades2.add(8);
            cantidades2.add(6);
            System.out.println("El stock actual es de: "+ tiendaAriel.consultarStock());

            System.out.println("********************* Error mas de 12 unidades/solo vende 12 unidades como maximo. *********************");
            List<Producto> productosVenta3 = new ArrayList<>();
            List<Integer> cantidades3 = new ArrayList<>();
            productosVenta3.add(azucar);
            cantidades3.add(13);
            tiendaAriel.realizarVenta(productosVenta3,cantidades3);
            System.out.println("El stock actual es de: "+ tiendaAriel.consultarStock());

            System.out.println("********************* Producto no se encuentra disponible *********************");
            List<Producto> productosVenta4 = new ArrayList<>();
            List<Integer> cantidades4 = new ArrayList<>();
            productosVenta4.add(ron);
            cantidades4.add(11);
            tiendaAriel.realizarVenta(productosVenta4,cantidades4);

            System.out.println("********************* Mayor numero solicita que en stock *********************");
            List<Producto> productosVenta5 = new ArrayList<>();
            List<Integer> cantidades5 = new ArrayList<>();
            System.out.println("Cantidad de lavandina en stock: " + lavandina.getCantidadStock());
            productosVenta5.add(lavandina);
            cantidades5.add(11);
            tiendaAriel.realizarVenta(productosVenta5,cantidades5);

            System.out.println("********************* Requerimiento adicional *********************");
            System.out.println(tiendaAriel.obtenerComestiblesConMenorDescuento(20));

            //********************* Validaciones *********************

            /* Descomentar las lineas para probar las validaciones, las mismas arrojaran un error.
            se comentan para evitar la ruptura de flujo del programa, se comentara que error arrojara cada linea
             */


        //      *********************** Envasados *********************************

        //Arrojara un error al intentar instanciar la clase Envasado debido a que el Id no es correcto
        // Envasados sal = new Envasados("A005", "Sal", 15, 20, 20, true, 12, false, "2025-05-18", 250, "Bolsa", true, true);

        //Arrojara un error al intentar instanciar la clase Envasado debido a que el porcentaje de descuento es mayor a 15
        // Envasados sal = new Envasados("AB005", "Sal", 15, 20, 20, true, 16, false, "2025-05-18", 250, "Bolsa", true, true);

        //Arrojara un error al intentar instanciar la clase Envasado debido a que comestible es true y su % de ganancia es mayor a 20
        // Envasados sal = new Envasados("AB005", "Sal", 15, 20, 21, true, 12, false, "2025-05-18", 250, "Bolsa", true, true);

        //Arrojara un error al intentar instanciar la clase Envasado debido a que el la fecha de vencimiento es menor a hoy
        // Envasados sal = new Envasados("AB005", "Sal", 15, 20, 20, true, 12, false, "2023-05-18", 250, "Bolsa", true, true);


        //      *********************** Bebidas *********************************

        //Arrojara un error al intentar instanciar la clase Bebida debido a que el Id no es correcto
        //Bebidas cepita = new Bebidas("AC03", "Jugo", 30, 40, 12, true, 10, true, "2025-01-01", 80, 0, true, true);

        //Arrojara un error al intentar instanciar la clase Bebida debido a que comestible es true y su % de ganancia es mayor a 20
        //Bebidas cepita = new Bebidas("AC013", "Jugo", 30, 40, 21, true, 10, true, "2025-01-01", 80, 0, true, true);

        //Arrojara un error al intentar instanciar la clase Bebida debido a que el porcentaje de descuento es mayor a 10
        //Bebidas cepita = new Bebidas("AC013", "Jugo", 30, 40, 12, true, 11 true, "2025-01-01", 80, 0, true, true);

        //Arrojara un error al intentar instanciar la clase Bebida debido a que el la fecha de vencimiento es menor a hoy
        //Bebidas cepita = new Bebidas("AC013", "Jugo", 30, 40, 12, true, 10 true, "2023-01-01", 80, 0, true, true);


        //      *********************** Limpieza *********************************

        //Arrojara un error al intentar instanciar la clase Limpieza debido a que tipoAplicacion es Multiuso
        // y su % de ganancia es mayor a 25
        //Limpieza limpiador = new Limpieza("AZ", "Limpiador de pisos", 25, 30, 26, true, 21, false, Limpieza.TipoAplicacion.MULTIUSO);

        //Arrojara un error al intentar instanciar la clase Limpieza debido a que el porcentaje de descuento es mayor a 20
        //Limpieza limpiador = new Limpieza("AZ111", "Limpiador de pisos", 25, 30, 19, true, 21, false, Limpieza.TipoAplicacion.MULTIUSO);

        //Arrojara un error al intentar instanciar la clase Limpieza debido a que el Id no es correcto
        //Limpieza limpiador = new Limpieza("AZ111", "Limpiador de pisos", 25, 30, 19, true, 12, false, Limpieza.TipoAplicacion.MULTIUSO);

    }
}