package com.example.restapi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.restapi.Activity.Adapter.UserAdapter;
import com.example.restapi.MainActivity;
import com.example.restapi.R;
import com.example.restapi.RetrofitInstance;
import com.example.restapi.RetrofitService;
import com.example.restapi.User;
import com.example.restapi.loadmore.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList; // Danh sách người dùng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        userList = new ArrayList<>(); // Khởi tạo danh sách người dùng

        recyclerView = findViewById(R.id.rvList);
        userAdapter = new UserAdapter(this, userList); // Truyền danh sách người dùng vào Adapter
        LinearLayoutManager ln = new LinearLayoutManager(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(ln);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(ln) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Tăng giới hạn (limit)
                int newLimit = page * visibleThreshold;

                // Gọi API để lấy danh sách người dùng
                RetrofitService rf = RetrofitInstance.getInstance().create(RetrofitService.class);
                Call<List<User>> call = rf.getUsers(page, newLimit); // Truyền page và newLimit vào phương thức getUsers()

                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> newUsers = response.body();
                            userAdapter.addNewUsers(newUsers); // Thêm dữ liệu mới vào danh sách người dùng
                             // Cập nhật RecyclerView
                        } else {
                            Toast.makeText(ListActivity.this, "Failed to load more data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(ListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Gọi API lần đầu để tải dữ liệu ban đầu
        loadData(1, 10); // Ví dụ: Trang 1, giới hạn 10
    }

    // Phương thức để gọi API và tải dữ liệu ban đầu
    private void loadData(int page, int limit) {
        RetrofitService rf = RetrofitInstance.getInstance().create(RetrofitService.class);
        Call<List<User>> call = rf.getUsers(page, limit);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> initialUsers = response.body();
                    userList.addAll(initialUsers); // Thêm dữ liệu ban đầu vào danh sách người dùng
                    userAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                } else {
                    Toast.makeText(ListActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
