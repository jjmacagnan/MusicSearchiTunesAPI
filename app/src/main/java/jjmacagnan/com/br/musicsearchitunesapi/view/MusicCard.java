package jjmacagnan.com.br.musicsearchitunesapi.view;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;
import jjmacagnan.com.br.musicsearchitunesapi.view.fragments.FragmentTabMatch;


@Layout(R.layout.music_card_view)
public class MusicCard {

    @View(R.id.imgArtworkDetail)
    private ImageView imgArtwork;

    @View(R.id.artist_name)
    private TextView artistNameTxt;

    @View(R.id.track_name)
    private TextView trackNameTxt;

    private Track mTrack;
    private Context mContext;

    public MusicCard(Context context, Track track) {
        mContext = context;
        mTrack = track;
    }

    @Resolve
    private void onResolved() {
        String artworkUrl = mTrack.getArtworkUrl100();
        Glide.with(mContext).load(artworkUrl).placeholder(R.drawable.ic_logo).into(imgArtwork);
        artistNameTxt.setText(mTrack.getArtistName());
        trackNameTxt.setText(mTrack.getTrackName());
    }

    @SwipeOut
    private void onSwipedOut() {
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
    }

    @SwipeIn
    private void onSwipeIn() {
    }

    @SwipeInState
    private void onSwipeInState() {
    }

    @SwipeOutState
    private void onSwipeOutState() {
    }

}
