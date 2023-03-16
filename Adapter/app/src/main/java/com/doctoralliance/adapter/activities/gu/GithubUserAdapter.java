package com.doctoralliance.adapter.activities.gu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doctoralliance.adapter.R;
import com.doctoralliance.adapter.R;

import com.doctoralliance.adapter.activities.nw.SecondPageNavigate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

//Define a ROW in XML to display data : UI for ROW
//go to res/layout
//RC::NEW::Layout Resource file
public class GithubUserAdapter extends
        RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>  {
    Activity activity;
    List<GithubUser> users=new ArrayList<>();
    public void setUsers(List<GithubUser> list){
        users.clear();
        users.addAll(list);
    }
    public  GithubUserAdapter(Activity activity){
        this.activity=activity;
    }

    //CALLBACKS
    //---- To Create ViewHolder
    @NonNull
    @Override
    public GithubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Get the App
        Context context = parent.getContext();

        //Get the Inflater (IMP) --- Used to inflate (READ) ROW LAYOUT
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userView = inflater.inflate(R.layout.row_github_user, parent, false);



        // Return a new holder instance
        GithubUserViewHolder viewHolder = new GithubUserViewHolder(userView);
        return viewHolder;
    }

    //-- to Bind Data to View Holder
    @Override
    public void onBindViewHolder(@NonNull GithubUserViewHolder holder, int position) {
        GithubUser user = users.get(position);

        // Set item views based on your views and data model
        TextView user_login = holder.user_login;
        ImageView user_image=holder.user_image;
        //We will use Glide Library to Display Image
        Glide.with(activity).load(user.getAvatar_url()).into(user_image);
        user_login.setText(user.getLogin());

        //Attaching click Listener inside onCreateViewHolder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get User Details and Display
                Log.e("test_message","------------clicked "+user.getLogin());
                //Lets Pass Data from This Activity to Other as JSON STRING
                //We have to pass entire Object
                Intent i=new Intent(activity, SecondPageNavigate.class);
                //Convert Object to Json String
                String jsonString=new Gson().toJson(user);
                //passing jsonString to other Activity as "user"
                i.putExtra("user",jsonString);
                activity.startActivity(i);

            }
        });
    }

    //--- HOW MANY RECORDS
    @Override
    public int getItemCount() {
        return users.size();
    }

    //Its good to create View Holder as an Inner class
    //View Holder : Mapped to Row in XML Layout
    class GithubUserViewHolder extends RecyclerView.ViewHolder{
        ImageView user_image;
        TextView user_login;
        //View itemView --> means ROW
        public GithubUserViewHolder(@NonNull View itemView) {
            super(itemView);
            user_image=itemView.findViewById(R.id.user_image);
            user_login=itemView.findViewById(R.id.user_login);

        }
    }

}
