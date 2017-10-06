package jjmacagnan.com.br.musicsearchitunesapi.api.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Track> musics = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Track> getTracks() {
        return musics;
    }

    public void setTracks(List<Track> musics) {
        this.musics = musics;
    }
}
