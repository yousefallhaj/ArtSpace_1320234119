package com.example.med_exam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.med_exam.databinding.ActivitySecondBinding;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private ActivitySecondBinding secondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        secondBinding = ActivitySecondBinding.inflate(
                getLayoutInflater()
        );

        setContentView(secondBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                secondBinding.getRoot(),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        Intent receivedIntent = getIntent();

        String title = receivedIntent.getStringExtra("artwork_title");
        String artist = receivedIntent.getStringExtra("artwork_artist");
        String year = receivedIntent.getStringExtra("artwork_year");

        int image = receivedIntent.getIntExtra("artwork_image", 0);

        secondBinding.detailsTitle.setText(title);

        secondBinding.detailsText.setText(
                artist + " (" + year + ")"
        );

        if (image != 0) {
            secondBinding.detailsImage.setImageResource(image);
        }

        secondBinding.backButton.setOnClickListener(v -> {
            finish();
        });

        secondBinding.websiteButton.setOnClickListener(v -> {

            Intent websiteIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.metmuseum.org")
            );

            startActivity(websiteIntent);
        });
    }
}