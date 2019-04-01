package com.rn11.bmirechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight;
    private EditText etWeight;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         etHeight = findViewById(R.id.txtHeight);
         etWeight = findViewById(R.id.txtWeight);
         tvOutput = findViewById(R.id.lblOutput);

        final Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (inputIsValid()) {
                        calculateBMI();
                    }
                }
                catch (NumberFormatException nfe)
                {
                    tvOutput.setText(R.string.exceptionFormatMismatch);
                }
            }
        });
    }

    private boolean inputIsValid(){
        int height;
        double weight;

        weight = Double.parseDouble(etWeight.getText().toString());
        height = Integer.parseInt(etHeight.getText().toString());

        if (height < 100 || height > 250 || weight < 10 || weight > 400) {
            tvOutput.setText(R.string.exceptionInvalidInputRange);
            return false;
        }
        return true;
    }

    private void calculateBMI(){
        try {
            int height;
            double weight;
            double res;

            weight = Double.parseDouble(etWeight.getText().toString());
            height = Integer.parseInt(etHeight.getText().toString());

            res = weight / Math.pow(((double) height / 100),2);

            res = Math.round(res * 100) / 100.00;

            tvOutput.setText(getApplicationContext().getString(R.string.calculationFinished, String.valueOf(res)));
        }
        catch (NumberFormatException exInputFormat) {
            tvOutput.setText(R.string.exceptionFormatMismatch);
        }
    }
}
