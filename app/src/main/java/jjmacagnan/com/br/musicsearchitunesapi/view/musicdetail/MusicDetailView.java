package jjmacagnan.com.br.musicsearchitunesapi.view.musicdetail;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;

public class MusicDetailView extends AppCompatActivity implements MusicDetailContract.View {

    Context context;
    LinearLayout main;
    ImageView imgArtwork;
    TextView txtArtistName, txtTrackName;
    VideoView videoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_detail);

        context = MusicDetailView.this;

        main = (LinearLayout) findViewById(R.id.music_detail_main);
        imgArtwork = (ImageView) findViewById(R.id.imgArtworkDetail);
        txtArtistName = (TextView) findViewById(R.id.artist_name_detail);
        txtTrackName = (TextView) findViewById(R.id.track_name_detail);
        videoView = (VideoView) findViewById(R.id.videoView);

        try {
            displayTrack((Track) getIntent().getSerializableExtra("track"));
        } catch (Exception e) {
            displayMessage("Problem while getting song info, Try again.");
        }

    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void displayTrack(Track track) {
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(track.getTrackName());
        }

        String artworkUrl = track.getArtworkUrl100();
        Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_logo).into(imgArtwork);

        txtArtistName.setText(track.getArtistName());

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(track.getPreviewUrl());
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    }
}
