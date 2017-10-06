package jjmacagnan.com.br.musicsearchitunesapi.view;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;

import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;
import jjmacagnan.com.br.musicsearchitunesapi.view.fragments.FragmentTabGostei;
import jjmacagnan.com.br.musicsearchitunesapi.view.fragments.FragmentTabMatch;


@Layout(R.layout.music_card_view)
public class MusicCard {

    @View(R.id.imgArtworkDetail)
    private ImageView mImgArtwork;

    @View(R.id.artist_name)
    private TextView mArtistNameTxt;

    @View(R.id.track_name)
    private TextView mTrackNameTxt;

    private Track mTrack;
    private Context mContext;

    public MusicCard(Context context, Track track) {
        mContext = context;
        mTrack = track;
    }

    @Resolve
    private void onResolved() {
        String artworkUrl = mTrack.getArtworkUrl100();
        Glide.with(mContext).load(artworkUrl).placeholder(R.drawable.ic_logo).into(mImgArtwork);
        mArtistNameTxt.setText(mTrack.getArtistName());
        mTrackNameTxt.setText(mTrack.getTrackName());
    }

    @SwipeInDirectional
    public void onSwipeInDirectional() {
        FragmentTabGostei.getListDataTracks().add(FragmentTabMatch.getQueueDataTracks().poll());
    }

    @SwipeOutDirectional
    public void onSwipeOutDirectional() {
        FragmentTabMatch.getQueueDataTracks().poll();
    }

}
