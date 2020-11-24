
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
		System.out.println("1 - Comprar vuelos");
		System.out.println("2 - Mostrar mis vuelos");
		System.out.println("3 - Eliminar mis vuelos");
		System.out.println("4 - Modificar mis vuelos");
		System.out.println("0 - Finalizar programa");
		System.out.print("Elige una opcion: ");
		opcion = sc.nextInt();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				System.out.println("_______COMPRANDO BILLETES_____");
				miAcceso.comprarBilletes();
				System.out.println("_______BILLETES COMPRADOS CORRECTAMENTE_____");
				break;
			case 2:
				System.out.println("_______MOSTRANDO MIS BILLETES_____");
				miAcceso.obtenerVuelos();
				System.out.println("_______MOSTRANDO MIS BILLETES CORRECTAMENTE_____");
				break;
			case 3:
				System.out.println("_______ELIMINAR MIS BILLETES_____");
				miAcceso.borrarBilletes();
				System.out.println("_______BILLETES ELIMINADOS CORRECTAMENTE_____");
				break;
			case 4:
				System.out.println("_______MODIFICAR MIS BILLETES_____");
				miAcceso.modificarBilletes();
				System.out.println("_______MIS BILLETES SE HAN MODIFICADO CORRECTAMENTE_____");
				break;

			}

			System.out.println("______________Menu principal_____________");
			System.out.println("                           ");
			System.out.println("Seleccione la accion a realizar");
			System.out.println("1 - Comprar vuelos");
			System.out.println("2 - Mostrar mis vuelos");
			System.out.println("3 - Eliminar mis vuelos");
			System.out.println("4 - Modificar mis vuelos");
			System.out.println("0 - Finalizar el programa");
			System.out.print("Elige una opcion: ");
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
