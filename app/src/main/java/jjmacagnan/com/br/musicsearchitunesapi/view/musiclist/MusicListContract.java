package jjmacagnan.com.br.musicsearchitunesapi.view.musiclist;


import java.util.List;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;


public class MusicListContract {

    public interface View {
        void displayMessage(String message);

        void displayTracks(List<Track> dataTrack);
    }

    public interface ViewSavedTracks {
        void displayTracks();
    }

    interface Presenter {
        void getTracks(String term);
    }
}
