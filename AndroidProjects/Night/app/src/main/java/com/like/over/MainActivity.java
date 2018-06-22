package com.like.over;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Button webpageButton, shareButton, locationButton, ownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webpageButton = (Button) findViewById(R.id.webpage_button);
        shareButton = (Button) findViewById(R.id.share_button);
        locationButton = (Button) findViewById(R.id.adress_button);
        ownButton = (Button) findViewById(R.id.create_button);

        // adding the click listeners
        webpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlAsString = "http://www.youtube.com";
                Uri webpage = Uri.parse(urlAsString);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adressString = "1600 Amphitheatre Parkway, CA";
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .query(adressString);
                Uri addressUri = builder.build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(addressUri);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToShare = "Sharing the coolest thing I've learned so far. You should " +
                        "check out Udacity and Google's Android Nanodegree!";
                String mimeType = "text/plain";
                String title = "Learning how to share";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle(title)
                        .setText(textToShare)
                        .startChooser();
            }
        });

        ownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Creater Update", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
