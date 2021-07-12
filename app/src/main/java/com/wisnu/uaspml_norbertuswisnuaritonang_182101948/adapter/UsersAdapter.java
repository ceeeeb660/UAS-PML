package com.wisnu.uaspml_norbertuswisnuaritonang_182101948.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.wisnu.uaspml_norbertuswisnuaritonang_182101948.R;
import com.wisnu.uaspml_norbertuswisnuaritonang_182101948.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    Context context;
    List<User> mUserList;

    public UsersAdapter(Context context,List<User> userList){
        this.context=context;
        mUserList=userList;
    }

    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {
        holder.mTextViewId.setText("Id = "+mUserList.get(position).getId());
        holder.mTextViewEmail.setText("Email = "+mUserList.get(position).getEmail());
        holder.mTextViewFirstName.setText("First Name = "+mUserList.get(position).getFirst_name());
        holder.mTextViewLastName.setText("Last Name = "+mUserList.get(position).getLast_name());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(mUserList.get(position).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.mImageViewAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id",mUserList.get(position).getId());
                mIntent.putExtra("Email",mUserList.get(position).getEmail());
                mIntent.putExtra("First Name",mUserList.get(position).getFirst_name());
                mIntent.putExtra("Last Name",mUserList.get(position).getLast_name());
                mIntent.putExtra("Avatar",mUserList.get(position).getAvatar());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewName, mTextViewEmail, mTextViewFirstName, mTextViewLastName;
        public ImageView mImageViewAvatar;

        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewId = (TextView)itemView.findViewById(R.id.tvId);
            mTextViewEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            mTextViewFirstName = (TextView)itemView.findViewById(R.id.tvFirstName);
            mTextViewLastName = (TextView)itemView.findViewById(R.id.tvLastName);
            mImageViewAvatar = (ImageView)itemView.findViewById(R.id.ivAvatar);
        }
    }
}

