import java.util.Scanner;

public class Menu {
	public void ejecucion() {
		PedirDatos miAcceso = new PedirDatos();
		int opcion;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("______________Menu principal_____________");
		System.out.println("                           ");
		System.out.println("Seleccione la accion a realizar");
		System.out.println("1 -Comprar Billetes");
		System.out.println("2- Eliminar mis Billetes");
		System.out.println("3- Modificar mis Billetes");
		System.out.println("0- Finalizar programa");
		System.out.print("opcion: ");
		opcion = sc.nextInt();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				System.out.println("_______COMPRANDO BILLETES_____");
				miAcceso.datoscomprarBillete();
				System.out.println("_______BILLETES COMPRADOS CORRECTAMENTE_____");
				break;
			case 2:
				System.out.println("_______ELIMINAR MIS BILLETES_____");
				miAcceso.datosMostrarMisVuelos();
				miAcceso.datosBorrarBillete();
				System.out.println("_______BILLETES ELIMINADOS CORRECTAMENTE_____");
				break;
			case 3:
				System.out.println("_______MODIFICAR MIS BILLETES_____");
				miAcceso.datosMostrarMisVuelos();
				miAcceso.datosModificarBillete();
				System.out.println("_______MIS BILLETES SE HAN MODIFICADO CORRECTAMENTE_____");
				break;

			}

			System.out.println("______________Menu principal_____________");
			System.out.println("                           ");
			System.out.println("Seleccione la accion a realizar");
			System.out.println("1 -Comprar Billetes");
			System.out.println("2- Eliminar mis Billetes");
			System.out.println("3- Modificar mis Billetes");
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
