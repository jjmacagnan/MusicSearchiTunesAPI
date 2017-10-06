package jjmacagnan.com.br.musicsearchitunesapi.view.musiclist;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;
import jjmacagnan.com.br.musicsearchitunesapi.view.musicdetail.MusicDetailView;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder>{

    private Context context;
    private List<Track> trackList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout row;
        ImageView imgTrackArtwork;
        TextView txtTrackName, txtArtistNameNGenre;

        MyViewHolder(View view) {
            super(view);
            row = view.findViewById(R.id.music_item_row);
            imgTrackArtwork = view.findViewById(R.id.artwork);
            txtTrackName = view.findViewById(R.id.track_name);
            txtArtistNameNGenre = view.findViewById(R.id.artist_name);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, MusicDetailView.class);
                    detail.putExtra("track", trackList.get(getAdapterPosition()));
                    context.startActivity(detail);
                }
            });
        }
    }

    public MusicAdapter(Context context, List<Track> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Track track = trackList.get(position);

        String artworkUrl = track.getArtworkUrl100();
        Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_logo).into(holder.imgTrackArtwork);

        holder.txtTrackName.setText(track.getTrackName());
        holder.txtArtistNameNGenre.setText(track.getArtistName() + " | " + track.getPrimaryGenreName());
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }
}
