package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import modelo.factorias.Factoria;
import modelo.factorias.TipoFactoria;
import modelo.seres.Trabajador;

class FactoriaTest {

	private Factoria factoriaPeque�a = new Factoria(TipoFactoria.peque�a, true);
	private Factoria factoriaMediana = new Factoria(TipoFactoria.mediana, true);
	private Factoria factoriaGrande = new Factoria(TipoFactoria.grande, true);

	public FactoriaTest(Factoria factoriaPeque�a, Factoria factoriaMediana, Factoria factoriaGrande) {
		super();
		for (int i = 0; i < 500; i++) {
			factoriaGrande.getTrabajadores().add(new Trabajador(String.valueOf(i), i));
		}
		for (int i = 0; i < 300; i++) {
			factoriaMediana.getTrabajadores().add(new Trabajador(String.valueOf(i), i));
		}
		for (int i = 0; i < 75; i++) {
			factoriaPeque�a.getTrabajadores().add(new Trabajador(String.valueOf(i), i));
		}
	}

	@Test
	void testContratarTrabajador() {
		Factoria fm = new Factoria(TipoFactoria.mediana, true);
		Factoria fg = new Factoria(TipoFactoria.grande, true);

		for (int i = 0; i < 1000; i++) {
			fg.getTrabajadores().add(new Trabajador(String.valueOf(i), i));
		}
		for (int i = 0; i < 200; i++) {
			fm.getTrabajadores().add(new Trabajador(String.valueOf(i), i));
		}
		assertEquals(201, fm.getTrabajadores().size());
		assertEquals(1000, fg.getTrabajadores().size());
	}

	@Test
	void testDespedirTrabajador() {
		Stack<Trabajador> listaDesempleados = new Stack<Trabajador>();
		for (int i = 0; i < 200; i++) {
			listaDesempleados.add(new Trabajador(String.valueOf(i), i));
		}
		factoriaPeque�a.despedirTrabajador(listaDesempleados, 30);
		assertEquals(45, factoriaPeque�a.getTrabajadores().size());
		assertEquals(230, listaDesempleados.size());

	}

	@Test
	void testCerraFactoria() {
		Stack<Trabajador> listaDesempleados = new Stack<Trabajador>();
		listaDesempleados.add(new Trabajador("pepito", 1));
		listaDesempleados.add(new Trabajador("pepito", 1));
		listaDesempleados.add(new Trabajador("pepito", 1));
		listaDesempleados.add(new Trabajador("pepito", 1));
		factoriaPeque�a.cerraFactoria(null);
		assertFalse(factoriaPeque�a.isOpen());
		assertTrue(factoriaPeque�a.getTrabajadores().isEmpty());
	}

	@Test
	void testGetProduccionAnual() {
		assertEquals(450000, factoriaGrande.getProduccionAnual());
		assertEquals(260000, factoriaMediana.getProduccionAnual());
		assertEquals(60000, factoriaPeque�a.getProduccionAnual());
	}

	@Test
	void testGetPorcentajeCapacidad() {
		assertEquals(50, factoriaGrande.getPorcentajeCapacidad());
		assertEquals(75, factoriaMediana.getPorcentajeCapacidad());
		assertEquals(75, factoriaPeque�a.getPorcentajeCapacidad());
	}

}
