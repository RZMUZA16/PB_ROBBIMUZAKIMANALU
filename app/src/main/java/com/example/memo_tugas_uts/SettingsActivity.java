package com.example.memo_tugas_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke DashboardActivity
                Intent intent = new Intent(SettingsActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Optional: agar tidak kembali ke settings saat tombol back ditekan
            }
        });
    }
}
