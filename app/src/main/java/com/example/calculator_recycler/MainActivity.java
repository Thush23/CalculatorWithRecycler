package com.example.calculator_recycler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etNumberOne;
    EditText etNumberTwo;
    Button btnAdd;
    Button btnSubtract;
    Button btnDivide;
    Button btnMultiply;
    Button btnLogs;
    TextView tvResults;

    private List<String> log = new ArrayList<>();

    private boolean isPermissionRequestInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberOne = findViewById(R.id.etNumber1);
        etNumberTwo = findViewById(R.id.etNumber2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        tvResults = findViewById(R.id.tvResult);
        btnLogs = findViewById(R.id.btnLogs);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = add(etNumberOne.getText().toString(),
                        etNumberTwo.getText().toString());

                tvResults.setText(result);
                log.add("Result of Addition: " + result);
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = subtract(etNumberOne.getText().toString(),
                        etNumberTwo.getText().toString());

                tvResults.setText(result);
                log.add("Result of Subtraction: " + result);
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = multiply(etNumberOne.getText().toString(),
                        etNumberTwo.getText().toString());

                tvResults.setText(result);
                log.add("Result of multiplication " + result);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = divide(etNumberOne.getText().toString(),
                        etNumberTwo.getText().toString());

                tvResults.setText(result);
                log.add("Result of Division: " + result);
            }
        });

        btnLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (log.size()>0){
                    writeLogsToFile();
                    writeLogsToExternalStorage();

                }

                Intent intent = new Intent
                        (MainActivity.this, LogsActivity.class);
                intent.putStringArrayListExtra("LogsResult",
                        (ArrayList<String>) log);
                if(!isPermissionRequestInProgress){
                    startActivity(intent);
                }
            }});


    }

    private String add(String numberOne, String numberTwo) {
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,
                    "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberOne);
        int b = Integer.parseInt(numberTwo);
        int result = a + b;
        return String.valueOf(result);
    }

    private String subtract(String numberOne, String numberTwo) {
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,
                    "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int c = Integer.parseInt(numberOne);
        int d = Integer.parseInt(numberTwo);
        int result = c - d;
        return String.valueOf(result);

    }

    private String multiply(String numberOne, String numberTwo) {
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,
                    "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int e = Integer.parseInt(numberOne);
        int f = Integer.parseInt(numberTwo);
        int result = (e * f);
        return String.valueOf(result);
    }

    private String divide(String numberOne, String numberTwo) {
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,
                    "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int g = Integer.parseInt(numberOne);
        int h = Integer.parseInt(numberTwo);
        int result = (g / h);
        return String.valueOf(result);
    }

    private void writeLogsToFile() {
        try (FileOutputStream fileOutputStream = openFileOutput("Logs.txt", Context.MODE_PRIVATE)) {

            StringBuilder stringBuilder = new StringBuilder();
            for (String result : log) {
                stringBuilder.append(result);
                stringBuilder.append("\n");


            }
            fileOutputStream.write(stringBuilder.toString().getBytes());

        } catch (IOException iOxception) {
            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            iOxception.printStackTrace();

        }

    }
    private void writeLogsToExternalStorage(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission();
        }else{
            writeFile();

        }
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length >0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            writeFile();

        }
    }

    private void writeFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File parentFolder = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Calculator");
            parentFolder.mkdirs();

            File file = new File(parentFolder, "Logs.text");

            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(file)) {

                StringBuilder stringBuilder = new StringBuilder();
                for (String result : log) {
                    stringBuilder.append(result);
                    stringBuilder.append("\n");


                }
                fileOutputStream.write(stringBuilder.toString().getBytes());

            } catch (IOException iOxception) {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
                iOxception.printStackTrace();

            }
        }
    }
}
