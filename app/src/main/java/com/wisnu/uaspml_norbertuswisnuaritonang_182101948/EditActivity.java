package com.wisnu.uaspml_norbertuswisnuaritonang_182101948;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wisnu.uaspml_norbertuswisnuaritonang_182101948.model.PostPutDelUser;
import com.wisnu.uaspml_norbertuswisnuaritonang_182101948.rest.ApiClient;
import com.wisnu.uaspml_norbertuswisnuaritonang_182101948.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    EditText editId, editEmail, editFirstName, editLastName;
    Button btnUpdate, btnDelete, btnBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editId = (EditText) findViewById(R.id.editID);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editFirstName = (EditText) findViewById(R.id.editNamaDepan);
        editLastName = (EditText) findViewById(R.id.editNamaBelakang);

        Intent mIntent = getIntent();
        editId.setText(mIntent.getStringExtra("Id"));
        editId.setTag(editId.getKeyListener());
        editId.setKeyListener(null);
        editEmail.setText(mIntent.getStringExtra("Email"));
        editFirstName.setText(mIntent.getStringExtra("First Name"));
        editLastName.setText(mIntent.getStringExtra("Last Name"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelUser>updateUserCall=mApiInterface.putUser(
                        editEmail.getText().toString(),
                        editFirstName.getText().toString(),
                        editLastName.getText().toString());
                updateUserCall.enqueue(new Callback<PostPutDelUser>() {
                    @Override
                    public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelUser> deleteUser = mApiInterface.deleteUser(editId.getText().toString());
                    deleteUser.enqueue(new Callback<PostPutDelUser>() {
                        @Override
                        public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}