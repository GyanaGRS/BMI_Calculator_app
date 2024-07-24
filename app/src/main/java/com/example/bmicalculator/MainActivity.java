package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assuming that EdgeToEdge is correctly implemented elsewhere
        // EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llmain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editWeight, editHeightFt, editHeightIn;
        TextView textResult;
        AppCompatButton btnCalculator;
        LinearLayout llmain;

        llmain = findViewById(R.id.llmain);
        editWeight = findViewById(R.id.editWeight);
        editHeightFt = findViewById(R.id.editHeightFt);
        editHeightIn = findViewById(R.id.editHeightInch);
        textResult = findViewById(R.id.textResult);
        btnCalculator = findViewById(R.id.btnCalculator);

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wt = Integer.parseInt(editWeight.getText().toString());
                int htF = Integer.parseInt(editHeightFt.getText().toString());
                int htI = Integer.parseInt(editHeightIn.getText().toString());

                int totalInch = htF * 12 + htI;
                double totalCM = totalInch * 2.54;

                double totalMeter = totalCM / 100;

                double bmi = wt / (totalMeter * totalMeter);

                if (bmi > 25) {
                    textResult.setText("You are Overweight");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorOW, getTheme())); // Updated line
                } else if (bmi < 18) {
                    textResult.setText("You are Underweight");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorUW, getTheme()));
                } else {
                    textResult.setText("You are Healthy!!");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorHW, getTheme()));
                }
            }
        });
    }
}
