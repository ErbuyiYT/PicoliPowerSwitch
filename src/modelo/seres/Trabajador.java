package modelo.seres;

import modelo.Ser;

public class Trabajador extends Ser {

	public Trabajador(String nombre, int id) {
		super(nombre, id);
		setEdad(18);
		setSueldoMinimoVivir(365);
	}
	
//  Si el trabajador tiene ahorros puede complementar la ayuda estatal para completar su NV.
//	Durante la vida laboral el trabajador cobrar� el doble de su NV, la parte que no consume se 
//	reparte de la siguiente forma, el 50% es ahorro para el propio trabajador y el otro 50% es para el estado.
//	Cuando un trabajador cobra menos que su necesidad vital su esperanza de vida decrece en una cantidad proporcional
//	a su d�ficit de ingresos para ese a�o y nunca en una cantidad menor a 0,5 a�os  
	@Override
	public void cobrar(int sueldo) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean morir() {
		// TODO Auto-generated method stub
		return false;
	}

}
