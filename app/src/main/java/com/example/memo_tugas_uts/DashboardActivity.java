package com.example.memo_tugas_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardActivity extends AppCompatActivity {

    private ImageView accountIcon, plusIcon, settingsIcon;
    private CalendarView calendarView;
    private Button logoutButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inisialisasi view
        accountIcon = findViewById(R.id.account_icon);
        plusIcon = findViewById(R.id.plus_icon);
        settingsIcon = findViewById(R.id.settings_icon);
        calendarView = findViewById(R.id.calendar_view);
        logoutButton = findViewById(R.id.logout_button);
        recyclerView = findViewById(R.id.recycler_view);

        // Klik account icon
        accountIcon.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class))
        );

        // Klik plus icon
        plusIcon.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, AddItemActivity.class))
        );

        // Klik settings icon
        settingsIcon.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, SettingsActivity.class))
        );

        logoutButton.setOnClickListener(view -> logout());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(DashboardActivity.this, "Tanggal dipilih: " + selectedDate, Toast.LENGTH_SHORT).show();
        });


    }

    private void logout() {
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
