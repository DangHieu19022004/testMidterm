package tlu.cse.ht63.coffeeshop.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;

import tlu.cse.ht63.coffeeshop.DbCoffeeHelper;
import tlu.cse.ht63.coffeeshop.MainActivity;
import tlu.cse.ht63.coffeeshop.R;

public class SigninActivity extends AppCompatActivity {
    TextView userName, passW, fullname, role, state;
    Button signin, login;

    DbCoffeeHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mapping();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SigninActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    long result = insertUser();
                    if(result == -1){
                        Toast.makeText(SigninActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SigninActivity.this, "Signup success", Toast.LENGTH_SHORT).show();


                        // login after 2s
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent it = new Intent(SigninActivity.this, MainActivity.class);
                                startActivity(it);
                            }
                        }, 2000);
                        //

                    }
                }
            }

            private boolean checkInput() {
                String name = fullname.getText().toString().trim();
                String user = userName.getText().toString().trim();
                String pass = passW.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please enter complete information", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (pass.length() < 6) {
                    Toast.makeText(SigninActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return false;
                }

                return true;
            }

            private long insertUser() {
                //get date now
                LocalDateTime currentTime = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    currentTime = LocalDateTime.now();
                }
                //

                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("hovaten" ,fullname.getText().toString());
                contentValues.put("userName", userName.getText().toString());
                contentValues.put("passW", passW.getText().toString());
                contentValues.put("datecreate", currentTime + "");
                contentValues.put("role", role.getText().toString());
                contentValues.put("state", state.getText().toString());
                return db.insert("User", null, contentValues);

            }
        });
    }




    private void mapping() {
        dbhelper = new DbCoffeeHelper(this);

        signin = findViewById(R.id.toSignupButton);
        login = findViewById(R.id.loginButton);
        userName = findViewById(R.id.username);
        passW = findViewById(R.id.password);
        fullname = findViewById(R.id.fullname);
        role = findViewById(R.id.role);
        state = findViewById(R.id.state);
    }
}