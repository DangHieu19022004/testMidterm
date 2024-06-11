package com.example.testrepository;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testrepository.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> titles;
    private List<String> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        titles = new ArrayList<>();
        contents = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            titles.add("Title " + i);
            contents.add("Content " + i);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter(titles, contents);
        recyclerView.setAdapter(adapter);
    }
}
