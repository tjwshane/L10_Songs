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

public class MainActivity3 extends AppCompatActivity {

    EditText title, singer, year;
    RadioGroup stars;
    Button update, delete, cancel;
    Song data;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        title = findViewById(R.id.Title);
        singer = findViewById(R.id.Singers);
        year = findViewById(R.id.Year);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.Delete);
        stars = findViewById(R.id.radioStars);
        cancel = findViewById(R.id.Cancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        title.setText(data.getTitle());
        singer.setText(data.getSingers());
        year.setText(String.valueOf(data.getYear()));
        stars.check(data.getStar());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setTitle(title.getText().toString());
                data.setSingers(singer.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                int songRating = 0;

                int rating = stars.getCheckedRadioButtonId();
                if (rating == R.id.Star1){
                    songRating = 1;
                } else if (rating == R.id.Star2) {
                    songRating = 2;
                } else if (rating == R.id.Star3) {
                    songRating = 3;
                } else if (rating == R.id.Star4) {
                    songRating = 4;
                } else {
                    songRating = 5;
                }

                data.setStars(songRating);

                dbh.updateSong(data);
                dbh.close();

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteSong(data.get_id());
                dbh.close();

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
