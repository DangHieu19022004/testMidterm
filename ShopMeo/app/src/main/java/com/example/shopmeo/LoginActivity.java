package com.example.shopmeo;

import android.content.Intent;
import android.database.Cursor;
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

public class LoginActivity extends AppCompatActivity {

    EditText phoneNum, passW;
    Button btnSignIn, btnLogIn;
    CuaHangHelper cuaHangHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Mapping();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(it);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLogin()){
                    Toast.makeText(LoginActivity.this, "Login sucess", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(it);

                }else{
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean checkLogin() {
                SQLiteDatabase db = cuaHangHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM User WHERE phoneNum = ? AND passW = ?", new String[] {phoneNum.getText().toString(), passW.getText().toString()} );
                if(cursor.getCount() > 0){
                    return true;
                }else{
                    return false;
                }
            }
        });
    }

    private void Mapping() {
        cuaHangHelper = new CuaHangHelper(this);

        phoneNum = findViewById(R.id.phoneNum);
        passW = findViewById(R.id.passW);
        btnSignIn = findViewById(R.id.btnsignIn);
        btnLogIn = findViewById(R.id.btnlogIn);
    }
}