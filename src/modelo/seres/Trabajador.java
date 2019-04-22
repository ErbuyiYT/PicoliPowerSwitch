package modelo.seres;

import modelo.CapitalEstado;
import modelo.Ser;

public class Trabajador extends Ser {

	private boolean trabajando;

	public Trabajador(String nombre, int id) {
		super(nombre, id);
		this.trabajando = false;
		setEdad(18);
		setNecesidadVital(365);
	}

//  Si el trabajador tiene ahorros puede complementar la ayuda estatal para completar su NV.
//	Durante la vida laboral el trabajador cobrar� el doble de su NV, la parte que no consume se 
//	reparte de la siguiente forma, el 50% es ahorro para el propio trabajador y el otro 50% es para el estado.
//	Cuando un trabajador cobra menos que su necesidad vital su esperanza de vida decrece en una cantidad proporcional
//	a su d�ficit de ingresos para ese a�o y nunca en una cantidad menor a 0,5 a�os
	// importante ya esta creado el metodo que le reduce la vida, ha este le entra
	// lo que le falta para tener el max NV
	@Override
	public void cobrar(double sueldo, CapitalEstado capitalEstado) {
		// TODO Auto-generated method stub

	}

// true si ha muerto
	@Override
	public boolean morir() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTrabajando() {
		return trabajando;
	}

	public void setTrabajando(boolean trabajando) {
		this.trabajando = trabajando;
	}

}
