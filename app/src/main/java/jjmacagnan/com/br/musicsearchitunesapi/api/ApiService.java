package jjmacagnan.com.br.musicsearchitunesapi.api;


import jjmacagnan.com.br.musicsearchitunesapi.api.model.TrackModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<TrackModel> getTracks(@Query("term") String term);
}
