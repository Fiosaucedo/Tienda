package negocio;

import java.util.ArrayList;

public class Vendedor {
	String codigo, nombre, sueldo;
	public ArrayList<Venta>ventashechas= new ArrayList<Venta>();

	int comisiontotal=0;
	public void calcularcomitotal() {					//Dependiendo de la cantidad de producto en cada venta, saca un porcentaje del total de la venta y lo agrega a su comision
		for (int i=0; i<ventashechas.size();i++) {
			if (ventashechas.get(i).cantidad<3){
			comisiontotal= comisiontotal+((ventashechas.get(i).preciototal/100)*5);
		}
			comisiontotal= comisiontotal+((ventashechas.get(i).preciototal/100)*10);
}}}
