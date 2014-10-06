package com.example.indicedemasacorporal;

import android.util.Log;

public class IndiceMasaCorporal {

	/**
	 * 
	 */
	private Double peso;
	private Integer altura;

	private final static int MAX_PESO = 300;
	private final static int MIN_PESO = 10;
	private final static int MAX_ALTURA = 300; // cm
	private final static int MIN_ALTURA = 100; // cm

	/**
	 * Constructor
	 * 
	 * @param peso
	 * @param altura
	 */
	public IndiceMasaCorporal(Double peso, Integer altura)
			throws IndiceMasaCorporalException {
		// super();
		this.setPeso(peso);
		this.setAltura(altura);

	}

	/**
	 * @return the altura
	 */
	public Integer getAltura() {
		return altura;
	}

	/**
	 * @param altura
	 *            the altura to set
	 * @throws IndiceMasaCorporalException
	 */
	public void setAltura(Integer altura) throws IndiceMasaCorporalException {
		if (validarAltura(altura)) {
			this.altura = altura;
		} else {
			throw new IndiceMasaCorporalAlturaException();
		}
	}

	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            the peso to set
	 * @throws Exception
	 */
	public void setPeso(Double peso) throws IndiceMasaCorporalException {
		if (validarPeso(peso)) {
			this.peso = peso;
		} else {
			throw new IndiceMasaCorporalPesoException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Double valorIndiceMasaCorporal() {
		Double altura_mts = altura.doubleValue() / 100.0;
		return peso.doubleValue() / Math.pow(altura_mts, 2.0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndiceMasaCorporal [peso=" + peso + ", altura=" + altura + "]";
	}

	/**
	 * Verifica que un valor de peso no es nulo y estŽ en su rango ...
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean validarPeso(Double valor) {
		boolean resultado;
		if (valor == null) {
			resultado = false;
		} else {
			resultado = (valor.doubleValue() >= MIN_PESO && valor.doubleValue() <= MAX_PESO);
		}
		return resultado;
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean validarAltura(Integer valor) {
		boolean resultado;
		if (valor == null) {
			resultado = false;
		} else {
			resultado = (valor.intValue() >= MIN_ALTURA && valor.intValue() <= MAX_ALTURA);
		}
		return resultado;
	}

	public String clasificacionOMS() {

		String resultado;
		double valor = this.valorIndiceMasaCorporal();
		if (valor < 16.0) {
			resultado = "Infrapeso. Delgadez severa";
		} else if (valor < 16.99) {
			resultado = "Infrapeso. Delgadez moderada";
		} else if (valor < 18.49) {
			resultado = "Infrapeso. Delgadez aceptable";
		} else if (valor < 24.99) {
			resultado = "Peso Normal";
		} else if (valor < 29.99) {
			resultado = "Sobrepeso";
		} else if (valor < 34.99) {
			resultado = "Obeso. Tipo I";
		} else if (valor < 40.00) {
			resultado = "Obeso. Tipo II";
		} else {
			resultado = "Obeso. Tipo III";
		}
		return resultado;
	}
}
