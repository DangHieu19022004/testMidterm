package tlu.cse.ht63.coffeeshop.user;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import tlu.cse.ht63.coffeeshop.DbCoffeeHelper;
import tlu.cse.ht63.coffeeshop.MainActivity;
import tlu.cse.ht63.coffeeshop.R;

public class LoginActivity extends AppCompatActivity {
    TextView userName, passW;
    Button signin, login;

    DbCoffeeHelper dbhelper;

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
        mapping();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(it);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    if(checkLogin()){
                        Toast.makeText(LoginActivity.this, "Login sucess", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                    }else{
                        Toast.makeText(LoginActivity.this, "Username or password are not correct", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            private boolean checkInput() {
                String user = userName.getText().toString().trim();
                String pass = passW.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter complete information", Toast.LENGTH_SHORT).show();
                    return false;
                }

                return true;
            }
        });
    }
    private boolean checkLogin() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE userName = ? AND passW = ?", new String[] {userName.getText().toString(), passW.getText().toString()} );
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    private void mapping() {
        dbhelper = new DbCoffeeHelper(this);

        signin = findViewById(R.id.toSignupButton);
        login = findViewById(R.id.loginButton);
        userName = findViewById(R.id.username);
        passW = findViewById(R.id.password);
    }
}