package com.example.shopmeo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopmeo.DataBase.CuaHangHelper;

public class SignInActivity extends AppCompatActivity {

    EditText hovaten, phoneNum, passW, rePassW;
    Button btnSignIn, btnLogIn;

    CuaHangHelper cuaHangHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mapping();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = insertUser();
                if(result == -1){
                    Toast.makeText(SignInActivity.this, "Signin failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignInActivity.this, "Signin success", Toast.LENGTH_SHORT).show();
                }
            }
            private long insertUser() {
                SQLiteDatabase db = cuaHangHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("hovaten" ,hovaten.getText().toString());
                contentValues.put("phoneNum", phoneNum.getText().toString());
                contentValues.put("passW", passW.getText().toString());
                contentValues.put("repassW", rePassW.getText().toString());
                return db.insert("User", null, contentValues);
            }

        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

    }

    private void Mapping() {
        cuaHangHelper = new CuaHangHelper(this);

        hovaten = findViewById(R.id.hovaten);
        phoneNum = findViewById(R.id.phoneNum);
        passW = findViewById(R.id.passW);
        rePassW = findViewById(R.id.rePassW);
        btnSignIn = findViewById(R.id.btnsignIn);
        btnLogIn = findViewById(R.id.btnlogIn);
    }
}