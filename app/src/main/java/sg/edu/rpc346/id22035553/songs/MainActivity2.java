package sg.edu.rpc346.id22035553.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> alSong;
    Button star5;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        alSong = new ArrayList<Song>();

        lv = findViewById(R.id.lv);
        star5 = findViewById(R.id.btn5Star);

        aaSong = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, alSong);
        lv.setAdapter(aaSong);

        DBHelper db = new DBHelper(MainActivity2.this);
        alSong.clear();
        alSong.addAll(db.getSong());
        aaSong.notifyDataSetChanged();

        ArrayList<String> data = db.getSongContent();

        CustomAdapter customAdapter = new CustomAdapter(this, alSong);
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song data = alSong.get(position);

                // Create an Intent to launch the third activity
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DBHelper db = new DBHelper(MainActivity2.this);

                ArrayList<Song> fiveStarSongs = new ArrayList<>();

                for (Song song : alSong) {
                    if (song.getStar() == 5) {
                        fiveStarSongs.add(song);
                    }
                }

                aaSong = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, fiveStarSongs);
                lv.setAdapter(aaSong);
            }
        });
    }
}
