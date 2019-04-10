package com.e.bodymassindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCalculate;
    private EditText etHeight, etWeight;
    private TextView etOutput;

    float height, weight, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalc);
        etHeight = findViewById(R.id.etHeight);
        etWeight= findViewById(R.id.etWeight);
        etOutput = findViewById(R.id.etOutput);

        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalc) {
            if (validation()) {
                height = Float.parseFloat(etHeight.getText().toString());
                weight = Float.parseFloat(etWeight.getText().toString());
                BMI obj = new BMI();
                obj.setHeight(height);
                obj.setWeight(weight);
                res = obj.calcBMI();
                etOutput.setText("Your BMI is " + Float.toString(res));

                if (res < 18.5) {
                    Toast.makeText(MainActivity.this, "Under Weight", Toast.LENGTH_LONG).show();
                } else if (res >= 18.5 && res <= 24.9) {
                    Toast.makeText(MainActivity.this, "Normal Weight", Toast.LENGTH_LONG).show();
                } else if (res >= 25 && res <= 29.9) {
                    Toast.makeText(MainActivity.this, "Over Weight", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Obesity", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public boolean validation(){
        boolean flag = true;

        if (TextUtils.isEmpty(etHeight.getText().toString())){
            etHeight.setError("Please enter Height");
            etHeight.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etWeight.getText().toString())){
            etWeight.setError("Please enter Height");
            etWeight.requestFocus();
            flag = false;
        }
        return flag;
    }
}
