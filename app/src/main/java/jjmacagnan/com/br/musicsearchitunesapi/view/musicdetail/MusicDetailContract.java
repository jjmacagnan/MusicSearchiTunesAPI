package jjmacagnan.com.br.musicsearchitunesapi.view.musicdetail;


import jjmacagnan.com.br.musicsearchitunesapi.api.model.Track;

public class MusicDetailContract {

    interface View {
        void displayMessage(String message);

        void displayTrack(Track track);
    }

}
