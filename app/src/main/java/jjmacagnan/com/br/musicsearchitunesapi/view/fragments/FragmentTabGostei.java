package jjmacagnan.com.br.musicsearchitunesapi.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import jjmacagnan.com.br.musicsearchitunesapi.R;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;
import jjmacagnan.com.br.musicsearchitunesapi.view.musiclist.MusicAdapter;
import jjmacagnan.com.br.musicsearchitunesapi.view.musiclist.MusicListContract;


public class FragmentTabGostei extends Fragment implements MusicListContract.ViewSavedTracks {

    Context context;
    TextView txtNoSongs;
    ShimmerRecyclerView listTracks;

    static List<Track> dataTracks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gostei, container, false);

        context = getContext();
        dataTracks = new ArrayList<>();

        txtNoSongs = rootView.findViewById(R.id.txtNoSongs);
        listTracks = rootView.findViewById(R.id.listSongs);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listTracks.setLayoutManager(mLayoutManager);
        listTracks.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        listTracks.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


    @Override
    public void displayTracks() {
        MusicAdapter adapter = new MusicAdapter(context, dataTracks);
        listTracks.setAdapter(adapter);
    }
}