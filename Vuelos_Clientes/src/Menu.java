
import java.util.Scanner;

public class Menu {
	public void ejecucion() {
		Funcionalidad miAcceso = new Funcionalidad();
		int opcion;
		int op = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("______________Menu principal_____________");
		System.out.println("                           ");
		System.out.println("Seleccione la accion a realizar");
		System.out.println("1 -Comprar Vuelos");
		System.out.println("2- Mostrar mis Vuelos");
		System.out.println("3- Eliminar mis Vuelos");
		System.out.println("4- Modificar mis Vuelos");
		System.out.println("0- Finalizar programa");
		System.out.print("opcion: ");
		opcion = sc.nextInt();
		while (opcion != 0) {
			switch (opcion) {

			case 1:
				System.out.println("_______COMPRANDO VUELOS_____");
				miAcceso.comprarVuelos();
				System.out.println("_______VUELOS COMPRADOS CORRECTAMENTE_____");

				break;
			case 2:
				System.out.println("_______MOSTRANDO MIS VUELOS_____");
				// miAcceso.anadirUno();
				System.out.println("_______MOSTRANDO MIS VUELOS CORRECTAMENTE_____");
				break;
			case 3:
				System.out.println("_______ELIMINAR MIS VUEOS_____");
				miAcceso.borrarVuelos();
				System.out.println("_______VUELOS ELIMINADOS CORRECTAMENTE_____");
				break;
			case 4:
				System.out.println("_______MODIFICAR MIS VUELOS_____");
				miAcceso.modificarVuelos();
				System.out.println("_______MIS VUELOS SE HAN MODIFICADO CORRECTAMENTE_____");
				break;

			}

			System.out.println("______________Menu principal_____________");
			System.out.println("                           ");
			System.out.println("Seleccione la accion a realizar");
			System.out.println("1 -Comprar Vuelos");
			System.out.println("2- Mostrar mis Vuelos");
			System.out.println("3- Eliminar mis Vuelos");
			System.out.println("4- Modificar mis Vuelos");
			System.out.println("0- Finalizar programa");
			System.out.print("opcion: ");
			opcion = sc.nextInt();

			if (opcion == 0)

			{
				System.out.println("______________");
				System.out.println("|             |");
				System.out.println("|Saliendo . . |");
				System.out.println("|_____________|");
			}
		}
	}
}
