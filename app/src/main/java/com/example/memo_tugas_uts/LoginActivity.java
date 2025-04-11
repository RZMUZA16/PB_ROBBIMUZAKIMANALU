package com.example.memo_tugas_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private MaterialButton btnLogin;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views - matching IDs from your XML
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        btnLogin = findViewById(R.id.btn_login);
        registerText = findViewById(R.id.register_text);

        // Login button click
        btnLogin.setOnClickListener(v -> {
            if (validateLogin()) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Register text click
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty()) {
            emailInput.setError("Email harus diisi");
            return false;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Password harus diisi");
            return false;
        }

        // Add additional validation as needed
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Email tidak valid");
            return false;
        }

        if (password.length() < 6) {
            passwordInput.setError("Password minimal 6 karakter");
            return false;
        }

        return true; // Return true if validation passes
    }
}