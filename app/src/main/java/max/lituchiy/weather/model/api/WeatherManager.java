package max.lituchiy.weather.model.api;

import com.google.gson.GsonBuilder;

import max.lituchiy.weather.model.utilities.Constants;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class WeatherManager {

    private ForecastApi mForecastApi;

    public ForecastApi getForecastApi() {

        if(mForecastApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mForecastApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(ForecastApi.class);
        }
        return mForecastApi;
    }

}
