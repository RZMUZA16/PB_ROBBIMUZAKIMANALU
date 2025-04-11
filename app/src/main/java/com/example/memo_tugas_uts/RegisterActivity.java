package com.example.memo_tugas_uts;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etBirthDate;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Firebase instance
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        etBirthDate = findViewById(R.id.etBirthDate);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView btnLogin = findViewById(R.id.btnLogin);

        etBirthDate.setOnClickListener(v -> showDatePickerDialog());

        btnRegister.setOnClickListener(v -> {
            if (validateForm()) {
                registerUser();
            }
        });

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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

    private void registerUser() {
        TextInputEditText etFullName = findViewById(R.id.etFullName);
        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        TextInputEditText etLocation = findViewById(R.id.etLocation);
        RadioGroup rgGender = findViewById(R.id.rgGender);

        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String birthDate = etBirthDate.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String uid = firebaseUser.getUid();
                            com.example.model.memo_tugas_uts.User user = new com.example.model.memo_tugas_uts.User(fullName, email, password, birthDate, gender, location);

                            mDatabase.child(uid).setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(this, DashboardActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(this, "Gagal simpan data: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(this, "Registrasi gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
