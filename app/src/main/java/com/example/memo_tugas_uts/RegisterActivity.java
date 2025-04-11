package com.example.memo_tugas_uts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi view
        etBirthDate = findViewById(R.id.etBirthDate);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView btnLogin = findViewById(R.id.btnLogin);

        etBirthDate.setOnClickListener(v -> showDatePickerDialog());

        btnRegister.setOnClickListener(v -> {
            if (validateForm()) {

                Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etBirthDate.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private boolean validateForm() {
        TextInputEditText etFullName = findViewById(R.id.etFullName);
        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        TextInputEditText etLocation = findViewById(R.id.etLocation);

        if (etFullName.getText().toString().isEmpty()) {
            etFullName.setError("Nama lengkap harus diisi");
            return false;
        }

        if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Email harus diisi");
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Password harus diisi");
            return false;
        }

        if (etBirthDate.getText().toString().isEmpty()) {
            etBirthDate.setError("Tanggal lahir harus diisi");
            return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etLocation.getText().toString().isEmpty()) {
            etLocation.setError("Lokasi harus diisi");
            return false;
        }

        return true;
    }
}