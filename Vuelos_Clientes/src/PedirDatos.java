import java.util.Scanner;

public class PedirDatos {
	Cliente cliente = new Cliente();
	Cliente clienteNew = new Cliente();
	Funciones funcionalidad = new Funciones();
	String codVuelo = "";
	String codVenta = "";
	Scanner sc = new Scanner(System.in);

	public void datoscomprarBillete() {
		funcionalidad.obtenerVuelos();
		System.out.println("Introduzca el CodigoVuelo a comprar");
		codVuelo = sc.next();
		System.out.println("INTRODUZCA SU DNI");
		cliente.setDni(sc.next());
		System.out.println("INTRODUZCA SU APELLIDO");
		cliente.setApellido(sc.next());
		System.out.println("INTRODUZCA SU NOMBRE");
		cliente.setNombre(sc.next());
		System.out.println("INTRODUZCA EL DNI DEL PAGADOR");
		cliente.setDniPagador(sc.next());
		System.out.println("INTRODUZCA EL Nº DE TARJETA");
		cliente.setTarjeta(sc.next());
		System.out.println("INTRODUZCA EL CODIGO VENTA");
		funcionalidad.comprarBilletes(codVuelo, cliente.getDni(), cliente.getApellido(), cliente.getNombre(),
		cliente.getDniPagador(), cliente.getTarjeta());
		

	}

	public void datosBorrarBillete() {
		funcionalidad.obtenerVuelos();
		System.out.println("Introduzca el CodigoVuelo a BORRAR");
		codVuelo = sc.next();
		System.out.println("INTRODUZCA SU DNI");
		cliente.setDni(sc.next());
		System.out.println("INTRODUZCA EL CODIGO VENTA");
		codVenta = sc.next();
		funcionalidad.borrarBilletes(codVuelo, cliente.getDni(), codVenta);
		System.out.println("VUELO BORRADO CORRECTAMENTE.");
	}

	public void datosModificarBillete() {

		System.out.println("Introduzca el CodigoVuelo a MODIFICAR");
		codVuelo = sc.next();
		System.out.println("INTRODUZCA SU DNI DE NUEVO PARA VERIFICAR QUE ERES EL PROPETARIO");
		cliente.setDni(sc.next());
		System.out.println("INTRODUZCA EL CODIGO VENTA A MODIFICAR");
		codVenta = sc.next();
		System.out.println("INTRODUZCA SU DNI");
		clienteNew.setDni(sc.next());
		System.out.println("INTRODUZCA SU APELLIDO");
		clienteNew.setApellido(sc.next());
		System.out.println("INTRODUZCA SU NOMBRE");
		clienteNew.setNombre(sc.next());
		System.out.println("INTRODUZCA EL DNI DEL PAGADOR");
		clienteNew.setDniPagador(sc.next());
		System.out.println("INTRODUZCA EL Nº DE TARJETA");
		clienteNew.setTarjeta(sc.next());
		System.out.println("SE HA GENERADO UN NUEVO CODIGO VENTA");
		funcionalidad.modificarBilletes(codVuelo, cliente.getDni(), codVenta, clienteNew.getDni(),
				clienteNew.getApellido(), clienteNew.getNombre(), clienteNew.getDniPagador(), clienteNew.getTarjeta());
		System.out.println("VUELO MODIFICADO CORRECTAMENTE.");

	}
	
	public void datosMostrarMisVuelos() {
	
		System.out.println("INTRODUZCA SU DNI PARA MOSTRAR SUS VUELOS");
		cliente.setDni(sc.next());
		funcionalidad.obtenerMisVuelos(cliente.getDni());
		
		
	}
	

}
