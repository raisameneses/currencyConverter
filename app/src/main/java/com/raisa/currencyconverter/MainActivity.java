package com.raisa.currencyconverter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String destCurr;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set spinner
        spinner = (Spinner)findViewById(R.id.converterOptons);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public double dollarToCoPeso(double dollar){
        double coPeso = 2876.35;
        return coPeso * dollar;
    }

    public double dollarToEuro(double dollar){
        double euro = 0.86;
        return euro * dollar;
    }
    public double dollarToMexPeso(double dollar){
        double mexPeso = 18.63;
        return mexPeso * dollar;
    }
    public double dollarToCADollar(double dollar){
        double caDollar = 1.31;
        return caDollar * dollar;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        destCurr = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void convert(View view){
        TextView amountInput = findViewById(R.id.amount);
        String amountToString = amountInput.getText().toString();
        double amount = Double.parseDouble(amountToString);
        Log.i("Dest", destCurr);
        double result;
        switch (destCurr){
            case "Pesos (CO)":
                result = dollarToCoPeso(amount);
                break;
            case "Euros":
                result = dollarToEuro(amount);
                break;
            case "Dollars (CA)":
                result = dollarToCADollar(amount);
                break;
            case "Pesos (MX)":
                result = dollarToMexPeso(amount);
                break;
            default:
                result = 0.0;
                destCurr = " ";
                break;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formatResult = df.format(result);
        Log.i("result", formatResult);
        TextView res = (TextView)findViewById(R.id.result);
        String displayResult = "$" + formatResult + " " + destCurr;
        res.setText(displayResult);

    }
}
