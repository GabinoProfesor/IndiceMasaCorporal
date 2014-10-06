package com.example.indicedemasacorporal;

import java.text.DecimalFormat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private IndiceMasaCorporal imc;
	EditText editPeso;
	EditText editAltura;
	TextView textViewResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Establecer identificadores
		editPeso = (EditText) findViewById(R.id.editPeso);
		editAltura = (EditText) findViewById(R.id.editAltura);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);

	}

	/**
	 * onButtonClickCalcularIMC
	 */
	public void onButtonClickCalcularIMC(View v) {

		Double peso;
		Integer altura;
		String resultado;
		try {
			try {
				peso = Double.valueOf(editPeso.getText().toString());
			} catch (NumberFormatException e) {
				throw new IndiceMasaCorporalPesoException();
			}

			try {
				altura = Integer.valueOf(editAltura.getText().toString());
			} catch (NumberFormatException e) {
				throw new IndiceMasaCorporalAlturaException();
			}

			imc = new IndiceMasaCorporal(peso, altura);
			double imcValorRedondeado = (double) Math.round(imc.valorIndiceMasaCorporal() * 100) / 100;
			resultado = "Valor IMC = " +  imcValorRedondeado;;
			resultado = resultado.concat(imc.clasificacionOMS());
			textViewResultado.setText(resultado);
			Log.e("IMC_ERROR", imc.toString());
		} catch (IndiceMasaCorporalPesoException e) {
			editPeso.setBackgroundColor(Color.LTGRAY);
		} catch (IndiceMasaCorporalAlturaException e) {
			editAltura.setBackgroundColor(Color.LTGRAY);
		} catch (Exception e) {
			Log.e("IMC_ERROR", e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
