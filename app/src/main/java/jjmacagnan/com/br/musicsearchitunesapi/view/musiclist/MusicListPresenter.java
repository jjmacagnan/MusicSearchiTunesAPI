package jjmacagnan.com.br.musicsearchitunesapi.view.musiclist;


import jjmacagnan.com.br.musicsearchitunesapi.api.ApiService;
import jjmacagnan.com.br.musicsearchitunesapi.api.ServiceFactory;
import jjmacagnan.com.br.musicsearchitunesapi.api.model.TrackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicListPresenter implements MusicListContract.Presenter {

    private MusicListContract.View musicListView;

    public MusicListPresenter(MusicListContract.View musicListView) {
        this.musicListView = musicListView;
    }

    @Override
    public void getTracks(String term) {
        ApiService service = ServiceFactory.getInstance();
        Call<TrackModel> trackModelCall = service.getTracks(term);
        trackModelCall.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(Call<TrackModel> call, Response<TrackModel> response) {
                TrackModel trackModel = response.body();
                if (trackModel.getResultCount() > 0) {
                    musicListView.displayTracks(trackModel.getTracks());
                } else {
                    musicListView.displayMessage("Não encontrado, Tente novamente");
                }
            }

            @Override
            public void onFailure(Call<TrackModel> call, Throwable t) {
                musicListView.displayMessage("Falha na requisição, Tente novamente");
            }
        });

    }
}
