package com.example.restapi.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restapi.R;
import com.example.restapi.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }
    public void setData(List<User> userList) {
        userList.clear();
        userList.addAll(userList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        Glide.with(context).load(user.getAvatarUrl()).into(holder.imageViewAvatar);
        holder.tv_Name.setText(user.getName());
        holder.tv_Date.setText(String.valueOf(user.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void addNewUsers(List<User> newUsers) {
        int startPosition = userList.size(); // Vị trí bắt đầu thêm mới
        userList.addAll(newUsers); // Thêm dữ liệu mới vào danh sách
        notifyItemRangeInserted(startPosition, newUsers.size()); // Thông báo cho RecyclerView biết về việc thêm các mục mới
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewAvatar;
        private TextView tv_Date,tv_Name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_Date = itemView.findViewById(R.id.tv_Date);
        }
    }
}
