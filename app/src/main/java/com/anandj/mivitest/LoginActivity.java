package com.anandj.mivitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creating instance
        final EditText etName = (EditText) findViewById(R.id.et_userName);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);


        //handling login action
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                //Validating user inputs
                if (name.isEmpty()) {
                    showToast("Please enter username");
                } else if (password.isEmpty()) {
                    showToast("Please enter password");
                } else if (name.equalsIgnoreCase("test") && password.equalsIgnoreCase("test123")) {
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();

                }
            }
        });
    }


    /**
     * Show toast alert message
     * @param msg - message wants to show
     */
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
