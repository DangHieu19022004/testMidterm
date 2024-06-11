package com.example.testfirebase;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mlistuser;
    private IClickListener mInterfaceClickListener;
    public interface IClickListener{
        void onClickUpdateItem(User user);
    }

    public UserAdapter(List<User> mlistuser, IClickListener mInterfaceClickListener) {
        this.mlistuser = mlistuser;
        this.mInterfaceClickListener = mInterfaceClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mlistuser.get(position);
        if(user == null){return;}
        holder.tvid.setText("ID: "+ user.getId());
        holder.tvname.setText("Name: "+ user.getName());

        holder.btnUpDateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterfaceClickListener.onClickUpdateItem(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mlistuser != null){
            return mlistuser.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvid, tvname;
        private Button btnUpDateItem;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tvid);
            tvname = itemView.findViewById(R.id.tvname);
            btnUpDateItem = itemView.findViewById(R.id.btnUpdateItem);
        }
    }
}
