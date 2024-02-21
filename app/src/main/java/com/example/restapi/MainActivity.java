package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //Load More: ListView and ReyclerView khi user kéo danh sách xuống sẽ tự động tải lại
    private  String url = "https://fptshop.com.vn/Uploads/Originals/2023/5/29/638209992395613788_frame-218.png";
    private String gif = "https://wallpapers-clan.com/wp-content/uploads/2022/08/meme-gif-pfp-17.gif";
    private ImageView imgNormal,imgGif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        imgGif = findViewById(R.id.imgGif);
        imgNormal = findViewById(R.id.imgNormal);
        //thư viện hiển thị ảnh từ url
        //Glide
        Glide.with(this).load(url).into(imgNormal);
        Glide.with(this).load(gif).into(imgGif);
        //Fresco
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(url);
        //Pocasso
        //Thư viện HTTP Request - Retrofit
        imgNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService rf = RetrofitInstance.getInstance().create(RetrofitService.class);
                Call<User> user = rf.getUserInfo(2);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(MainActivity.this, "Không có gì ", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}