package control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import modelo.Factoria;
import modelo.Ser;
import modelo.seres.Trabajador;

public class Industria {

	private ArrayList<Factoria> factorias;

	public Industria() {
		super();
		this.factorias = new ArrayList<>();
		this.factorias.add(new Factoria());
	}

	public double getProduccionTotal() {
		double retorno = 0;
		for (Factoria factoria : factorias) {
			retorno += factoria.getProduccionAnual();
		}
		return retorno;
	}

	public void cerrarFactorias(Stack<Trabajador> listaDesempleados) {
		for (Factoria factoria : factorias) {
			if (factoria.getTrabajadores().isEmpty()) {
				factoria.cerraFactoria(listaDesempleados);
				factorias.remove(factoria);
			}
		}
	}

	public void eliminarTrabajador(int id) {
		for (Factoria factoria : factorias) {
			factoria.eliminarTrabajador(id);
		}
	}

	public void crearNuevaFactoria() {
		this.factorias.add(new Factoria());
	}

	public int getNumTrabajdores() {
		int numTrabajadores = 0;
		for (Factoria factoria : factorias) {
			numTrabajadores += factoria.getTrabajadores().size();
		}
		return numTrabajadores;
	}

	public ArrayList<Factoria> getFactorias() {
		return factorias;
	}

	public void contratar(Stack<Trabajador> desempleados, ArrayList<Ser> seres) {

	}

	public void despedir(HashSet<Trabajador> listaDesempleados, int numTrabajadores) {

	}

}