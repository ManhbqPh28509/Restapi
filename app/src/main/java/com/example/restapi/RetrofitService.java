package com.example.restapi;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/todos/{id}")
    public Call<User> getUserInfo(@Path("id") int id);
    // định nghĩa phương thức request vào địa chỉ /todos/ với biến id
    //trong địa chỉ chỉ nên dùng từ khóa là Path

    //https://jsonplaceholder.typicode.com/comments?postId=3
    // request http get với tham số gửi kèm dạng param : postId
    // tham số của Call là class dùng để bóc tách JSON , dựa trên thư viện GSON
    // sử dụng trang pojo json để tạo class bóc JSON từ mẫu json
    // tuỳ chọn source type là json và kiểu thư viện là GSON
    // tham số Call là List<Comment> do dữ liệu là dạng Array Json chứa nhiều Comment
    @GET("/comments")
    public Call<List<Comment>> getPostComment(@Query("postId") int id);

    // định nghĩa request http post
    // https://jsonplaceholder.typicode.com/posts
    // dữ liệu gửi lên là JSON object chứa id, userId, body và title
    // nếu gưi thành công dữ liệu trả về là chính JSON object gửi lên
    @POST("/posts")
    public Call<User> createPost(@Body User user);
    @GET("/users")
    public Call<List<User>> getUsers(@Query("page") int page, @Query("limit") int limit);
}
