package negocio;

import java.util.ArrayList;

public class Venta{
	ArrayList <Producto> productos = new ArrayList<Producto>();	 
	public Vendedor vendedor;
	public int cantidad=productos.size();
	int preciototal=0;
	
	
	
	public void calcularpreciototal(){		//Suma el precio de todos los productos de la venta
		
		 for (int i=0; i<productos.size();i++) {
			 preciototal= preciototal+(productos.get(i).precio);
			 ;
		 }
	 }
		
}		
