package negocio;

import java.util.ArrayList;
import javax.swing.*;

public class Menu {
   

	static ArrayList<Producto> productos = new ArrayList<Producto>();
    static ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
    static ArrayList<Venta> ventas = new ArrayList<Venta>();
    
      
    public static void main(String[] args) {			
    		boolean andando=true;					//En el main, mientras andando sea true, el programa va a funcionar.
    		while (andando==true){
    		int operacion= pediroperacion();		//Pido la operacionn que desea realizar el cliente
			if (operacion==1) 
				registrarventa(andando);
			if (operacion==2) 
				registrarvendedor(andando);
			if (operacion==3) 
				registrarproducto(andando);
			if (operacion==4) 
				buscarproducto(andando);
			if (operacion==5) 
				calcularcomision(andando);
			
    }}
    
    public static int pediroperacion() {			// Mientras la respuesta no este entre 1 y 5, seguira pidiendo respuesta hasta que se ingrese una valida
    	int opcion= Integer.valueOf (JOptionPane.showInputDialog("Bienvenido! Introduzca el numero de la operacion que desea realizar.\n 1- Registrar venta\n 2- Registrar nuevo vendedor\n 3- Registrar nuevo producto\n 4- Buscar producto\n 5- Calcular comision de vendedor"));
    	while (opcion<1 || opcion>5) {
    	opcion= Integer.valueOf(JOptionPane.showInputDialog("Opcion invalida! Intente de nuevo.\n 1- Registrar venta\n 2- Registrar nuevo vendedor\n 3- Registrar nuevo producto\n 4- Buscar producto\n 5- Calcular comision de vendedor"));
        }
    	return opcion;
    }
    
    static void registrarventa(boolean andando) {
    	Venta v= new Venta();						//La funcion crea un nuevo objeto del tipo venta, se le pide que ingrese el nombre de un vendedor el cual ya tiene que estar registrado
    	String nombreVendedor=  JOptionPane.showInputDialog("Ingrese el vendedor");
		int posicion= buscarvendedor(nombreVendedor,vendedores);
		if (posicion==-1) {							//En caso de que el vendeor ingresado no este registrado, no se procede con el registro de la venta
			int opcion=  JOptionPane.showConfirmDialog(null,"Parece que este vendedor no esta registrado. Desea volver a intentarlo? ");
			if (opcion==0) {
				registrarventa(andando);
			}
			if (opcion==2) {
			andando=false;
			}}
		
			v.vendedor= vendedores.get(posicion);	//En caso de que el vendedor si este, procedemos a asociar el objeto venta con dicho vendedor y viceversa
			vendedores.get(posicion).ventashechas.add(v);
			ventas.add(v);
			v.calcularpreciototal();
			
			String producto= "";					//Ahora le pedimos que ingrese un producto el cual tambien debe estar registrado en el sistema
			while (!(producto.equals("1"))) {	
				producto= JOptionPane.showInputDialog("Ingrese el nombre del producto o ingrese 1 si no hay mas productos que agregar");   //Se le va a pedir al suario que ingrese los productos que fueron comprados hasta que ingrese el 1 indicando que ya no hay mas para agregar
				if (!(producto.equals("1"))) {	

				posicion= (buscarenlista(producto,productos)) ;			//En caso de no encontrar el producto registrado, no se procede con el registro de la venta
				if (posicion== -1 && !producto.equals("1")) {
					int opcion=  JOptionPane.showConfirmDialog(null,"Parece que no tenemos registrado ese producto! Queres intentar con otro?");
						if (opcion==0) {
							registrarventa(andando);
						}
						if (opcion==2) {
							andando=false;}
							}
			
				v.productos.add(productos.get(posicion));			//En caso de que se verifique la existencia del producto, procedemos a asociar la venta con el
				v.vendedor.ventashechas.add(v);}}
				v.calcularpreciototal();
				int opcion= JOptionPane.showConfirmDialog(null,"Venta registrada correctamente.\nDesea cargar otra venta?");
				if (opcion==0) {
					registrarventa(andando);
				}
				if (opcion==2) {
					andando=false;
				}			
		}
    
			
    static void registrarvendedor(boolean andando) {			//La funcion crea un nuevo objeto Vendedor y le pide al usuario sus datos para ir rellenando las variables dentro de el
    	Vendedor n= new Vendedor();
    	n.nombre=  JOptionPane.showInputDialog("Ingrese el nombre del nuevo vendedor");
    	n.codigo=  JOptionPane.showInputDialog("Ahora ingrese el codigo de "+n.nombre);
    	n.sueldo=  JOptionPane.showInputDialog("Por ultimo, ingrese su sueldo");
    	vendedores.add(n);
    	int opcion= JOptionPane.showConfirmDialog(null,"Vendedor registrado exitosamente!\nDesea registrar otro vendedor?");
    	if (opcion==0) {
    	 registrarvendedor(andando);
    	 }	
    	if (opcion==2) {
    		andando=false;
    	}
    }
    
    static void registrarproducto(boolean andando) {			//La funcion crea un nuevo objeto Producto y le pide al usuario sus datos para ir rellenando las variables dentro de el
    	Producto p= new Producto();
    	p.nombre=  JOptionPane.showInputDialog("Ingrese el nombre del nuevo producto");
    	p.codigo=  JOptionPane.showInputDialog("Ahora ingrese su codigo");
    	p.categoria=  (JOptionPane.showInputDialog("Ingrese su categoria"));
    	p.precio=  Integer.valueOf((JOptionPane.showInputDialog("Por ultimo, ingrese su precio")));
    	productos.add(p);
    	int opcion= JOptionPane.showConfirmDialog(null,"Producto registrado exitosamente!\nDesea registrar otro roducto?");
    	if (opcion==0) {
    		registrarproducto(andando);
    	}
    }
    
    static void buscarproducto(boolean andando) {				//La funcion le pide al usuario que ingrese como desea encontar su producto
  
    	String opcionmultiple= JOptionPane.showInputDialog("Como queres buscar tu producto? Ingresa el numero de opcion que corresponda\n 1-Por nombre\n 2-Por codigo\n 3-Por categoria\n");
    	
    	if (opcionmultiple.equals("1")) {						//En caso de elegir buscarlo por nombre, busca en la lista "productos" el producto cuyo nombre coincida con el de la busqueda
        	String busqueda= JOptionPane.showInputDialog("Ingresa el nombre del producto");
        	int posicion= buscarenlista(busqueda,productos);
    			if (posicion!=-1){								//Si el producto se encuentra, se devuelve su precio, codigo y categoria
    				Producto resultado= productos.get(posicion);
    	        	int opcion= JOptionPane.showConfirmDialog(null,"Encontramos el producto "+ resultado.nombre +"!\nCodigo: "+resultado.codigo+"\nPrecio: "+resultado.precio+"\nCategoria: "+resultado.categoria+"\nDesea realizar otra busqueda?");
    	        	if (opcion==0){
    	        		buscarproducto(andando);}
    	        	if (opcion==2);
    	        		andando=false;
    				}
    			if(posicion==-1) {								//En caso de que no, le preguntamos al usuario si quiere realizar otra busqueda
    				int opcion= JOptionPane.showConfirmDialog(null,("No encontramos el producto "+ busqueda +"\nDesea realizar otra busqueda?"));
    				if (opcion==0){
    					buscarproducto(andando);}
    				if(opcion==2);
    					andando=false;
        	}}	
    	
    	if(opcionmultiple.equals("2")) {						//Al elegir buscarlo por codigo se usa un procedimiento similar al de nombre, solo que buscando coincidencias con el string codigo
    		String busqueda= JOptionPane.showInputDialog("Ingresa el codigo del producto");
        	int posicion= buscarenlista(busqueda,productos);
    			if (posicion!=-1){
    				Producto resultado= productos.get(posicion);
    	        	int opcion= JOptionPane.showConfirmDialog(null,"Encontramos el producto "+ resultado.codigo +"!\nNombre: "+resultado.nombre+"\nPrecio: "+resultado.precio+"\nCategoria: "+resultado.categoria+"\nDesea realizar otra busqueda?");
    	        	if (opcion==0){
    	        		buscarproducto(andando);
    	        	}
    	        	if (opcion==2);
    	        		andando=false;
    	        }
        	if(posicion==-1) {
        		int opcion= JOptionPane.showConfirmDialog(null,"No encontramos el producto "+ busqueda +"\nDesea realizar otra busqueda?");
        		if (opcion==0){
        			buscarproducto(andando);
        		}
        		if (opcion==2);
        			andando=false;
        		}
    		}	
    	
    	if(opcionmultiple.equals("3")) {						//Al elegir buscarlo por categoria, debemos encontar todos los productos con la categoria que inngrese el usuario
    		String resultado="";
    		String busqueda= JOptionPane.showInputDialog("Ingresa la categoria del producto");
    		ArrayList <Integer> posiciones= todaslasposiciones(busqueda,productos);			//Busco en la lista todos los objetos que coicidan y guardo sus pociciones
    		for (int i=0; i<posiciones.size();i++) {
    				resultado= resultado +productos.get(posiciones.get(i)).nombre+ "\n";}	//Y guardo el contenido de esas posiciones en un string para formar una listita
    	        	int opcion= JOptionPane.showConfirmDialog(null,"Encontramos el/los siguientes productos de la categoria "+ busqueda+ "\n"+resultado+"\nDesea realizar otra busqueda?");
    	        	if (opcion==0){
    	        		buscarproducto(andando);
    	        	}
    	        	if (opcion==2);
    	        		andando=false;
    					 	
    		if (posiciones.size()==0){								//en caso de no encontrar coincidencias, pregunta si se quiere realizar otra busqueda
    			 opcion= JOptionPane.showConfirmDialog(null,"No encontramos el producto "+ busqueda +"\nDesea realizar otra busqueda?");
    		if (opcion==0){
        		buscarproducto(andando);
        		}
        	if (opcion==2);
        		andando=false;
        	}
    	}
    	
    }
    
    static void calcularcomision(boolean andando) {
		String vendedor= JOptionPane.showInputDialog("Ingresa un vendedor para conocer su comision");		//Para conocer la comision el vendedor debe estar registrado
		int posicion=buscarvendedor(vendedor,vendedores);
		if (posicion!=-1){
			vendedores.get(posicion).calcularcomitotal();					//Una vez que se encuentra el vendedor en la lista, hace que se inicie la funcion ppara que calcula se comision
			int opcion= JOptionPane.showConfirmDialog(null,"La comision de este empleado es:$"+vendedores.get(posicion).comisiontotal+"\nDesea calcular la comision de otro vendedor?");
			if (opcion==0) {
				calcularcomision(andando);
			}
			if (opcion==2);
				andando=false;
		}
		if (posicion==-1){
			int opcion= JOptionPane.showConfirmDialog(null,"Parece que este vendeor no esta registrado! Queres consultar la comision de otro vendedor?");
			if (opcion==0) {
			calcularcomision(andando);
		}
		if (opcion==2);
			andando=false;
	}}
    
    static int buscarvendedor(String nombre, ArrayList<Vendedor>lista) { 		//Busca vendedor
    	for (int i=0; i<lista.size();i++) {
			if (nombre.equals((vendedores.get(i).nombre))) {
				return i;
			}
    	}
    	return -1;
    }
   
    static int buscarenlista(String nombre, ArrayList<Producto>lista) {			//Dado un string, busca un producto en la lista que cuyo alguno de sus datos coincida con el de la busqueda
    	for (int i=0; i<lista.size();i++) {
			if ((nombre.equals(productos.get(i).nombre))||(nombre.equals(productos.get(i).codigo))||(nombre.equals(productos.get(i).categoria))) {
				return i;
    		}
    	}	
    	return -1;
    }
    
    static ArrayList <Integer> todaslasposiciones (String categoria, ArrayList<Producto>lista) {		//Devuelve una lista con todas las posiciones donde haya productos con la categoria que el usuario busque
    	ArrayList <Integer >posiciones= new ArrayList<Integer>();
    	for (int i=0; i<lista.size();i++) {
    		if (categoria.equals(lista.get(i).categoria)) {
    			posiciones.add(i);
    		}
    	} return posiciones;
    	
    }
}