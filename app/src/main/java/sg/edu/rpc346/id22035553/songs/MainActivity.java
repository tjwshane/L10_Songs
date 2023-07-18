package sg.edu.rpc346.id22035553.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import sg.edu.rpc346.id22035553.songs.R;

public class MainActivity extends AppCompatActivity {

        EditText SongTitle, Singer, Year;
        RadioGroup stars;
        Button Insert, ShowList;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            SongTitle = findViewById(R.id.etSongTitle);
            Singer = findViewById(R.id.etSinger);
            Year = findViewById(R.id.etYear);
            stars = findViewById(R.id.radioButtonStars);
            Insert = findViewById(R.id.btnInsert);
            ShowList = findViewById(R.id.btnShowList);

            Insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DBHelper dbHelper = new DBHelper(MainActivity.this);

                    String title = SongTitle.getText().toString();
                    String singers = Singer.getText().toString();
                    int year = Integer.parseInt(Year.getText().toString());
                    int rating = 0;

                    int songRating = stars.getCheckedRadioButtonId();

                    if (songRating == R.id.rb1) {
                        rating = 1;
                    } else if (songRating == R.id.rb2) {
                        rating = 2;
                    } else if (songRating == R.id.rb3) {
                        rating = 3;
                    } else if (songRating == R.id.rb4) {
                        rating = 4;
                    } else {
                        rating = 5;
                    }
                    dbHelper.insertSong(title, singers, year, rating);
                }
            });

            ShowList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Launch new activity to display records
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            });
        }
    }