package com.doctoralliance.adapter.activities.nw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doctoralliance.adapter.R;
import com.doctoralliance.adapter.activities.gu.GithubService;
import com.doctoralliance.adapter.activities.gu.GithubUser;
import com.doctoralliance.adapter.activities.gu.GithubUserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button1;
    RecyclerView r1;


    //Life cycyle Method : called when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Connecting it to XML File
        setContentView(R.layout.activity_main);
        //Connect Button object in Java to button UI in xml (IMP)
        button1 = findViewById(R.id.button1);
        r1 = findViewById(R.id.r1);
        //attach an event
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    getUsers();
                } catch (Exception e) {
                    Log.e("network_error", e.toString());
                }



            }
        });
    }

    private void getUsers() throws Exception {

        //Execute Network call
        //Create Retrofit with Base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create Service using RetroFit
        GithubService service = retrofit.create(GithubService.class);
        //Now Get the data
        Call<List<GithubUser>> usersCall = service.getUsers();
        //I am executing the call
        //I am NOT executing the call, I am enqueuing it
        //Learn : execute vs enqueue , advantages of enqueue over execute
        usersCall.enqueue(new Callback<List<GithubUser>>() {
            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                List<GithubUser> listResponse = response.body();
                //I want to display it in RecyclerView "r1"
                // Create adapter passing in the sample user data
                GithubUserAdapter adapter = new GithubUserAdapter(MainActivity.this);
                // Attach the adapter to the recyclerview to populate items
                r1.setAdapter(adapter);
                // Set layout manager to position the items
                r1.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                adapter.setUsers(listResponse);
            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                Log.e("network_error", t.toString());
            }
        });


    }


}