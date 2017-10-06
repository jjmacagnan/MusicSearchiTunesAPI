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

    private Context mContext;
    private LinearLayout mLinearLayout;
    private ImageView mImgArtwork;
    private TextView mTxtArtistName, mTxtTrackName;
    private VideoView mVideoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_detail);

        mContext = MusicDetailView.this;

        mLinearLayout = (LinearLayout) findViewById(R.id.music_detail_main);
        mImgArtwork = (ImageView) findViewById(R.id.imgArtworkDetail);
        mTxtArtistName = (TextView) findViewById(R.id.artist_name_detail);
        mTxtTrackName = (TextView) findViewById(R.id.track_name_detail);
        mVideoView = (VideoView) findViewById(R.id.videoView);

        try {
            displayTrack((Track) getIntent().getSerializableExtra("track"));
        } catch (Exception e) {
            displayMessage(getString(R.string.falha_informacao));
        }
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(mLinearLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTrack(Track track) {
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(track.getTrackName());
        }

        String artworkUrl = track.getArtworkUrl100();
        Glide.with(mContext).load(artworkUrl).placeholder(R.drawable.ic_logo).into(mImgArtwork);

        mTxtArtistName.setText(track.getArtistName());
        mTxtTrackName.setText(track.getTrackName());

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        Uri video = Uri.parse(track.getPreviewUrl());
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(video);
        mVideoView.start();
    }
}
