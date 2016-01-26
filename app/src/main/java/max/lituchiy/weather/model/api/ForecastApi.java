package max.lituchiy.weather.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface ForecastApi {

    @GET("/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%22Dnipropetrovsk,%20Ukraine%22)%20and%20u=%27c%27%20&format=json")
    void getForecast(Callback<String> forecasts);
}
