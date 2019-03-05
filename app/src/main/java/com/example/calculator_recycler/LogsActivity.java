package com.example.calculator_recycler;

import android.os.Bundle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LogsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        TextView tvLogs = findViewById(R.id.tvLogs);

        ArrayList<String> logs = getIntent().getStringArrayListExtra("LogsResult");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <logs.size(); i++){
            stringBuilder.append(logs.get(i));
            stringBuilder.append("\n");

        }
        RecyclerView recyclerView =findViewById(R.id.rv_results);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ResultsAdapter resultsAdapter = new ResultsAdapter(logs);
        recyclerView.setAdapter(resultsAdapter);
    }


    private String readDataFromFile(){
        File file = new File(getFilesDir(), "Logs.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            fileInputStream.read(contents);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new String(contents);

    }

}
