package com.example.med_exam;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.med_exam.databinding.ActivityMainBinding;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainbinding;

    private int[] artworkImages = {
            R.drawable.artwork_1,
            R.drawable.artwork_2,
            R.drawable.artwork_3,
            R.drawable.artwork_4,
            R.drawable.artwork_5,
            R.drawable.artwork_6
    };

    private String[] artworkTitles = {
            "Irises",
            "Bridge over a Pond of Water Lilies",
            "Two Sisters (On the Terrace)",
            "Rocks at Fontainebleau",
            "The Astronomer",
            "A Sunday on La Grande Jatte — 1884"
    };

    private String[] artworkArtists = {
            "Vincent van Gogh",
            "Claude Monet",
            "Pierre-Auguste Renoir",
            "Paul Cézanne",
            "Johannes Vermeer",
            "Georges Seurat"
    };

    private String[] artworkYears = {
            "1889",
            "1899",
            "1881",
            "1890s",
            "1668",
            "1884–1886"
    };

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainbinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainbinding.getRoot());

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("artwork_index", 0);
        }

        updateArtwork();

        ViewCompat.setOnApplyWindowInsetsListener(mainbinding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainbinding.nextButton.setOnClickListener(v -> {

            if (currentIndex < artworkImages.length - 1) {
                currentIndex++;
                updateArtwork();
            }
        });

        mainbinding.previousButton.setOnClickListener(v -> {

            if (currentIndex > 0) {
                currentIndex--;
                updateArtwork();
            }
        });

        mainbinding.artworkImage.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            intent.putExtra("artwork_title", artworkTitles[currentIndex]);
            intent.putExtra("artwork_artist", artworkArtists[currentIndex]);
            intent.putExtra("artwork_year", artworkYears[currentIndex]);
            intent.putExtra("artwork_image", artworkImages[currentIndex]);

            startActivity(intent);
        });

    }

    private void updateArtwork() {

        mainbinding.artworkImage.setImageResource(
                artworkImages[currentIndex]
        );

        mainbinding.artworkTitle.setText(
                artworkTitles[currentIndex]
        );

        mainbinding.artworkArtist.setText(
                artworkArtists[currentIndex]
                        + " (" + artworkYears[currentIndex] + ")"
        );

        mainbinding.artworkCounter.setText(
                (currentIndex + 1) + " / " + artworkImages.length
        );

        mainbinding.previousButton.setEnabled(currentIndex > 0);

        mainbinding.nextButton.setEnabled(
                currentIndex < artworkImages.length - 1
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("artwork_index", currentIndex);
    }










}