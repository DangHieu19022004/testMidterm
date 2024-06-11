package tlu.cse.ht63.coffeeshop.total;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tlu.cse.ht63.coffeeshop.DbCoffeeHelper;
import tlu.cse.ht63.coffeeshop.R;

public class TotalActivity extends AppCompatActivity {

    EditText iduser;
    Button btncheck;
    TextView sumcost;
    DbCoffeeHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_total);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainshoptotal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mapping();
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        sumcost.setText(value);




        btncheck.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(checkLogin()){
               double total = Double.parseDouble(value) - Double.parseDouble(value)*15/100;
               String cos = String.valueOf(total);
               sumcost.setText(cos);
           }else{
               sumcost.setText(value);
               Toast.makeText(TotalActivity.this, "ID không tồn tại", Toast.LENGTH_SHORT).show();
           }
               Toast.makeText(TotalActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
           }
       });
    }
    private boolean checkLogin() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE idUser = ? ", new String[]{iduser.getText().toString()});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    private void mapping() {
        btncheck = findViewById(R.id.btncheck);
        iduser = findViewById(R.id.iduser);
        sumcost = findViewById(R.id.sumcost);
    }
}