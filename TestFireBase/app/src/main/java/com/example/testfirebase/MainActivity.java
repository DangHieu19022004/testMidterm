package com.example.testfirebase;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    Button btnget, btnpush, btndelete, btnupdate;
    TextView data;
    EditText editTextText;
    RecyclerView rclv;
    UserAdapter userAdapter;
    List<User> userList;

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



        btnget = findViewById(R.id.btnget);
        btnpush = findViewById(R.id.btnpush);
        data = findViewById(R.id.data);
        editTextText = findViewById(R.id.editTextText);
        btndelete = findViewById(R.id.btndelete);
        btnupdate = findViewById(R.id.btnupdate);
        rclv = findViewById(R.id.rclv);

        //xu ly render du lieu len recycleview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rclv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rclv.addItemDecoration(dividerItemDecoration);

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, new UserAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(User user) {
                openDialogUpdateItem(user);
            }
        });

        rclv.setAdapter(userAdapter);
        //

        //getuser
        getListUserRealtimeDTB();
        //

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateObj(); // c3 là updateMap
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteDataMap();
//                onClickDeleteDataObj();
//                onClickDeleteData();
            }
        });

        btnpush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPushDataList();
//                onClickPushDataMap();
//                onClickPushDataObj();
//                onClickPushData();
            }
        });

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGetDataMap();
//                onClickGetDataObj();
//                onClickGetData();
            }
        });
    }

    private void openDialogUpdateItem(@NonNull User user){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        EditText edtUpdatename = dialog.findViewById(R.id.edtUpdateName);
        Button btncancel = dialog.findViewById(R.id.btncancel);
        Button btnupdate = dialog.findViewById(R.id.btnbtnupdate);

        edtUpdatename.setText(user.getName());

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user_list");

                user.setName(edtUpdatename.getText().toString().trim());

                myRef.child(String.valueOf(user.getId() - 1)).updateChildren(user.toMap());

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void getListUserRealtimeDTB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_list");

//        c1:
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(userList != null){
//                    userList.clear();
//                }
//
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    User user = dataSnapshot.getValue(User.class);
//                    userList.add(user);
//                }
//
//                userAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        c2:
        Query query = myRef.orderByChild("rate");
        // sap xep theo rate (myRef sẽ thay bằng query) có thể dùng orderByValue
        //query cx dùng để lọc dữ liệu:
            //myRef.limitToFirst(2) - lấy 2 thk đầu danh sách
            //......limitToLast(2) - lấy 2 thk cuối danh sách
            //......startAt() - Trả về các mục lớn hơn HOẶC BẰNG khóa hoặc giá trị được chỉ định tùy thuộc vào phương pháp theo thứ tự đã chọn.
                //vd lọc rate > 2: .orderByChild("rate).startAt(2)
            //......startAfter() - ngược lại startAt() CHỈ LỚN HƠN
            //......endAt() - trả về nhỏ hơn HOẶC BẰNG
            //......endBefore() - CHỈ nhỏ hơn
            //......equalTo() - lọc BẰNG
        myRef.addChildEventListener(new ChildEventListener() { //thay query ở đây (mặc định từ bé đến lớn)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    //nếu muốn lọc theo logic tự tạo thì xử lý bang if
                    //vd: if(user.getName().contains(keyword))

                    userList.add(user); // muốn đổi thành từ lớn đến bé thì dùng .add(0, user)

                    //thong bao dl trong list thay đổi để recycleview reset
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if(user == null || userList == null || userList.isEmpty()){return;}
                for(int i =0; i<userList.size(); i++){
                    if(user.getId() == userList.get(i).getId()){
                        userList.set(i, user);
                        break;
                    }
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user == null || userList == null || userList.isEmpty()){return;}
                for(int i =0; i<userList.size(); i++){
                    if(user.getId() == userList.get(i).getId()){
                        userList.remove(userList.get(i));
                        break;
                    }
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void onClickPushDataList(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_list");

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Hieu"));
        users.add(new User(2, "Mai"));
        users.add(new User(3, "Dao"));

        myRef.setValue(users);
    }
    private void onClickDeleteDataMap(){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Chắc chắn xóa?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("my_map");

                        myRef.removeValue(); // delete my_map
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void onClickGetDataMap(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_map");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Boolean> map = new HashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    Boolean value = dataSnapshot.getValue(Boolean.class);

                    map.put(key, value);
                }

                data.setText(map.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void onClickPushDataMap(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_map");

        Map<String, Boolean> map = new HashMap<>();
        map.put("1", true);
        map.put("2", false);

        myRef.setValue(map);
    }
    private void onClickDeleteDataObj(){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Chắc chắn xóa?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("user_info/address");

                        myRef.removeValue(); // delete address

                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void onClickUpdateObj(){
        //c1: setvalue giống pushData
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("user_info");
//        .....setValue....

        //c2: thêm /name
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("user_info/name");
//        myRef.setValue("NYM");

        //c3: sd Map(tối ưu nhất)
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_info");
        Map<String, Object> map = new HashMap<>();
        map.put("address", "Tay Mo");
        map.put("job/name", "Giao vien");
        myRef.updateChildren(map);
    }
    private  void onClickGetDataObj(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_info");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                data.setText(user.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void  onClickPushDataObj(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_info");

        User user = new User( 1, "Hieu");

        myRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void onClickDeleteData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void onClickPushData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(editTextText.getText().toString().trim(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference myReftest = database.getReference("test");
        myReftest.setValue("test2");

        DatabaseReference myReftest1 = database.getReference("prj/test1");
        myReftest1.setValue("test prj1");
    }

    private void onClickGetData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                data.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}