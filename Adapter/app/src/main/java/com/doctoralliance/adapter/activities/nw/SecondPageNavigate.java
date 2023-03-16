package com.doctoralliance.adapter.activities.nw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doctoralliance.adapter.R;
import com.doctoralliance.adapter.activities.gu.GithubUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecondPageNavigate extends AppCompatActivity {
    Button button2;
    TextView repos_url;
    TextView events_url;
    TextView received_events_url;

    //    "repos_url": "https://api.github.com/users/mojombo/repos",
//            "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
//            "received_events_url": "https://api.github.com/users/mojombo/received_events",
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_navigate);
        Intent i=getIntent();
        //new Gson().fromJson -- JSON DECOEDE
        GithubUser user = new Gson().fromJson(i.getStringExtra("user"),GithubUser.class);
        button2=findViewById(R.id.button2);
        repos_url=findViewById(R.id.repos_url);
        events_url=findViewById(R.id.events_url);
        received_events_url=findViewById(R.id.received_events_url);
        repos_url.setText(user.getRepos_url());
        events_url.setText(user.getEvents_url());
        received_events_url.setText(user.getReceived_events_url());


        //attach an event
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SecondPageNavigate.this,MainActivity.class);
                //start
                //Intents are launched using startActivity method
                startActivity(i);
            }
        });
    }

}