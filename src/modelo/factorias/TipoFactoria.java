package modelo.factorias;

public enum TipoFactoria {

	peque�a(100), mediana(400), grande(1000);

	private int numTrabajadores;

	private TipoFactoria(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	public int getNumTrabajadores() {
		return numTrabajadores;
	}

}
