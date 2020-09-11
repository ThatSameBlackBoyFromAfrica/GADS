package com.chukwuebuka.gads2020leaderboard.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chukwuebuka.gads2020leaderboard.R;
import com.chukwuebuka.gads2020leaderboard.interfaces.PostDataInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmissionActivity extends AppCompatActivity {

    public static Retrofit retrofit;
    public EditText etFirstName;
    public EditText etLastName;
    public EditText etEmail;
    public EditText etGithub;
    public String firstName;
    public String lastName;
    public String email;
    public String github;
    public Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        Toolbar tbSubmission = (Toolbar) findViewById(R.id.tb_submission);
        tbSubmission.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        tbSubmission.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etFirstName = (EditText) findViewById(R.id.tv_first_name);
        etLastName = (EditText) findViewById(R.id.tv_last_name);
        etEmail = (EditText)findViewById(R.id.tv_email);
        etGithub = (EditText) findViewById(R.id.tv_github);
        submitButton = (Button) findViewById(R.id.bn_final_submit);

        findViewById(R.id.bn_final_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromEditText();
                editTextInvisible();

                AlertDialog alertDialog = new AlertDialog.Builder(ProjectSubmissionActivity.this)
                        .setCancelable(true)
                        .setView(R.layout.submit_comfirmation_dialog)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialogInterface, int i) {
                                submit();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editTextVisible();
                                setEditText(firstName, lastName, email, github);
                                dialogInterface.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });


    }

    private void editTextVisible() {
        etFirstName.setVisibility(View.VISIBLE);
        etLastName.setVisibility(View.VISIBLE);
        etGithub.setVisibility(View.VISIBLE);
        etEmail.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    private void getTextFromEditText() {
        firstName = etFirstName.getText().toString().trim();
        lastName = etLastName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        github = etGithub.getText().toString().trim();

        etFirstName.setText("");
        etLastName.setText("");
        etEmail.setText("");
        etGithub.setText("");
    }

    private void setEditText(String firstName, String lastName, String email, String github) {
        etFirstName.setText(firstName);
        etLastName.setText(lastName);
        etEmail.setText(email);
        etGithub.setText(github);
    }


    private void editTextInvisible() {
        etFirstName.setVisibility(View.INVISIBLE);
        etLastName.setVisibility(View.INVISIBLE);
        etGithub.setVisibility(View.INVISIBLE);
        etEmail.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
    }

    private void submit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        createPost(firstName, lastName, email, github);
    }


    private void createPost(final String firstName, final String lastName, final String email, final String github) {
        PostDataInterface postDataInterface = retrofit.create(PostDataInterface.class);

        Call<ResponseBody> call = postDataInterface.createPost(firstName, lastName, email, github);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    AlertDialog alertDialog = new AlertDialog.Builder(ProjectSubmissionActivity.this)
                            .setCancelable(true)
                            .setView(R.layout.submission_unsuccessful_layout)
                            .create();
                    editTextVisible();
                    setEditText(firstName, lastName, email, github);
                    alertDialog.show();
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(ProjectSubmissionActivity.this)
                            .setCancelable(true)
                            .setView(R.layout.submission_successful_layout)
                            .create();
                    editTextVisible();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AlertDialog alertDialog = new AlertDialog.Builder(ProjectSubmissionActivity.this)
                        .setCancelable(true)
                        .setView(R.layout.submission_unsuccessful_layout)
                        .create();
                editTextVisible();
                setEditText(firstName, lastName, email, github);
                alertDialog.show();
            }
        });
    }
}