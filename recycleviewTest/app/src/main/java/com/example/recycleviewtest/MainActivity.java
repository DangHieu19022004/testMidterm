package com.example.recycleviewtest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardList = new ArrayList<Card>();
        cardList.add(new Card(R.drawable.avatar, "meo"));
        cardList.add(new Card(R.drawable.avatar, "meo"));
        cardList.add(new Card(R.drawable.avatar, "meo"));
        cardList.add(new Card(R.drawable.avatar, "meo"));
        cardList.add(new Card(R.drawable.avatar, "meo"));
        cardList.add(new Card(R.drawable.avatar, "meo"));

        recyclerView =  findViewById(R.id.recycleview);

        Adapter myAdapter = new Adapter(cardList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}