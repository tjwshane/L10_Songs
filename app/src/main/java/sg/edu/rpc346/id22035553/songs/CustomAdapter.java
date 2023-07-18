package sg.edu.rpc346.id22035553.songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {

    private Context context;
    private ArrayList<Song> songList;

    public CustomAdapter(Context context, ArrayList<Song> songList) {
        super(context, 0, songList);
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        }

        // Get the Song object at the current position
        Song song = songList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView singersTextView = convertView.findViewById(R.id.singersTextView);
        TextView yearTextView = convertView.findViewById(R.id.yearTextView);
        TextView starsTextView = convertView.findViewById(R.id.starsTextView);

        titleTextView.setText(song.getTitle());
        singersTextView.setText(song.getSingers());
        yearTextView.setText(String.valueOf(song.getYear()));

        String starsString = "";
        for (int i = 0; i < song.getStar(); i++) {
            starsString += "*";
        }
        starsTextView.setText(starsString);

        return convertView;
    }
}
