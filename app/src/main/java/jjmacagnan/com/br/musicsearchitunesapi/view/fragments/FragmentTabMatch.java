package jjmacagnan.com.br.musicsearchitunesapi.view.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;
import jjmacagnan.com.br.musicsearchitunesapi.view.MusicCard;
import jjmacagnan.com.br.musicsearchitunesapi.view.musiclist.MusicListContract;
import jjmacagnan.com.br.musicsearchitunesapi.view.musiclist.MusicListPresenter;

public class FragmentTabMatch extends Fragment implements MusicListContract.View {

    private SwipePlaceHolderView mSwipeView;
    private static Queue<Track> dataTracks;
    private MusicListPresenter presenter;
    private FrameLayout frameLayout;

    public FragmentTabMatch() {
        presenter = new MusicListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_match, container, false);

        frameLayout = rootView.findViewById(R.id.framelayout_match);

        mSwipeView = rootView.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(5)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.music_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.music_swipe_out_msg_view));

        mSwipeView.disableTouchSwipe();
        rootView.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
                dataTracks.poll();
            }
        });

        rootView.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
                FragmentTabGostei.dataTracks.add(dataTracks.poll());
            }
        });

        return rootView;
    }

    @Override
    public void displayMessage(String message) {
        if (getView() != null)
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTracks(List<Track> dataTrack) {
        if (dataTracks == null)
            dataTracks = new ArrayDeque<>();

        dataTracks.clear();
        dataTracks.addAll(dataTrack);
        mSwipeView.removeAllViews();
        new MusicLoadList().execute();
    }


    public void search(final String strTerm) {
        if (dataTracks != null)
            dataTracks.clear();

        frameLayout.setVisibility(View.VISIBLE);
        presenter.getTracks(strTerm);
    }


    private class MusicLoadList extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            for (Track track : dataTracks) {
                mSwipeView.addView(new MusicCard(getContext(), track, mSwipeView));
            }
        }
    }
}
