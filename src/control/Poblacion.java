package control;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.CapitalEstado;
import modelo.Ser;
import modelo.TipoSeres;
import utilesglobal.Utilies;

public class Poblacion {

	private ArrayList<Ser> seres = new ArrayList<Ser>();

	private int id = 0;
	private int fallecidos;
	private int newJubilados;
	private int newTrabajadores;
	private int newMenores;

	public void resetDatos() {
		this.fallecidos = 0;
		this.newJubilados = 0;
		this.newTrabajadores = 0;
		this.newMenores = 0;
	}

	public Poblacion(int menores, int desempleados, int jubilados) {
		super();
		for (int i = 0; i < menores; i++) {
			seres.add(new Ser(this.id, TipoSeres.menor, Utilies.obtenerAleatorio(0, 17)));
			this.id++;
		}
		for (int i = 0; i < desempleados; i++) {
			seres.add(new Ser(this.id, TipoSeres.desempleado, Utilies.obtenerAleatorio(18, 64)));
			this.id++;
		}
		for (int i = 0; i < jubilados; i++) {
			seres.add(new Ser(this.id, TipoSeres.jubilado, 65));
			this.id++;
		}
	}

	public ArrayList<Ser> getListTipoSer(TipoSeres tipoSer) {
		ArrayList<Ser> lista = new ArrayList<Ser>();
		for (Iterator iterator = this.seres.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			if (ser.getTipoSer() == tipoSer) {
				lista.add(ser);
			}
		}
		return lista;
	}

	public ArrayList<Ser> getDesempleados() {
		ArrayList<Ser> lista = new ArrayList<Ser>();
		for (Iterator iterator = this.seres.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			if (ser.getTipoSer() == TipoSeres.desempleado) {
				ser.setTrabajador();
				lista.add(ser);
			}
		}
		return lista;
	}

	public void envejecerPoblacion() {
		for (Ser ser : this.seres) {
			ser.setEdad(ser.getEdad() + 1);
		}
	}

	public ArrayList<Integer> actualizarSer() {
		ArrayList<Integer> listId = new ArrayList<Integer>();
		for (Ser ser : this.seres) {
			if (ser.getEdad() == 18 && ser.getTipoSer() == TipoSeres.menor) {
				ser.setDesempleado();
				this.newTrabajadores++;
			}
			if (ser.getEdad() == 65 && ser.getTipoSer() == TipoSeres.desempleado) {
				ser.setJubilar();
				this.newJubilados++;
			}
			if (ser.getEdad() == 65 && ser.getTipoSer() == TipoSeres.trabajador) {
				ser.setJubilar();
				listId.add(ser.getId());
				this.newJubilados++;
			}
		}
		for (int i = 0; i < this.newJubilados; i++) {
			this.seres.add(new Ser(this.id, TipoSeres.menor, 0));
			this.newMenores++;
			this.id++;
		}
		return listId;
	}

	public ArrayList<Integer> eliminarMuertos(CapitalEstado capitalEstado) {
		ArrayList<Integer> listId = new ArrayList<Integer>();
		for (Iterator iterator = seres.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			if (ser.isMuerto()) {
				capitalEstado.sumarDineroEstado(ser.getAhorros());
				if (ser.getTipoSer() == TipoSeres.trabajador)
					listId.add(ser.getId());
				this.fallecidos++;
				iterator.remove();
			}
		}
		return listId;
	}

	public void pagarMenores(CapitalEstado capitalEstado) {
		for (Ser ser : seres) {
			if (ser.getTipoSer() == TipoSeres.menor)
				ser.subsidioMenor(capitalEstado.getSubsidioMenor(getNumTipoSer(TipoSeres.menor)));
		}
	}

	public void pagarDesempleados(CapitalEstado capitalEstado) {
		for (Ser ser : seres) {
			if (ser.getTipoSer() == TipoSeres.desempleado)
				ser.subsidioDesempleado(capitalEstado.obtenerSueldo(getListTipoSer(TipoSeres.desempleado).size(),
						ser.getTipoSer().getSueldo()));
		}
	}

	public void pagarTrabajadores(CapitalEstado capitalEstado) {
		double sueldo = capitalEstado.obtenerSueldo(getListTipoSer(TipoSeres.trabajador).size(),
				TipoSeres.trabajador.getSueldo());
		for (Ser ser : seres) {
			if (ser.getTipoSer() == TipoSeres.trabajador) {
				ser.pagarTrabajador(capitalEstado, sueldo);
			}
		}
	}

	public void pagarJubilados(CapitalEstado capitalEstado) {
		ArrayList<Ser> pensionistas = new ArrayList<>();
		double subsidioTotal = getSubsidioTotalJubilados(pensionistas);
		double porcentaje = capitalEstado.obtenerSubsidioJubilado(subsidioTotal);
		for (Ser ser : seres) {
			if (pensionistas.contains(ser)) {
				ser.subsidioJubilado(porcentaje * ser.getNVRestanteJubilado());
			}
		}
	}

	private double getSubsidioTotalJubilados(ArrayList<Ser> pensionistas) {
		double retorno = 0;
		for (Ser ser : seres) {
			if (ser.getTipoSer() == TipoSeres.jubilado) {
				double dinero = ser.getNVRestanteJubilado();
				if (dinero != 0) {
					pensionistas.add(ser);
					retorno += dinero;
				}
			}
		}
		return retorno;
	}

	public int getNumTipoSer(TipoSeres tipoSeres) {
		int contador = 0;
		for (Ser ser : seres) {
			if (ser.getTipoSer() == tipoSeres) {
				contador++;
			}
		}
		return contador;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getFallecidos() {
		return fallecidos;
	}

	public int getNewJubilados() {
		return newJubilados;
	}

	public int getNewTrabajadores() {
		return newTrabajadores;
	}

	public int getNewMenores() {
		return newMenores;
	}

	public ArrayList<Ser> getSeres() {
		return seres;
	}

}
