package com.doctoralliance.adapter.activities.gu;





import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface GithubService {
    @GET("users")
    Call<List<GithubUser>> getUsers();
}
