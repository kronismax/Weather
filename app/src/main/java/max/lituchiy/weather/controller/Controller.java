package max.lituchiy.weather.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import max.lituchiy.weather.model.api.WeatherManager;
import max.lituchiy.weather.model.pojo.Forecast;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private ForecastCallbackListener mListener;
    private WeatherManager mApiManager;

    public Controller(ForecastCallbackListener listener) {
        mListener = listener;
        mApiManager = new WeatherManager();
    }

    public void startFetching() {
        mApiManager.getForecastApi().getForecast(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
//                    query results channel item forecast
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject query = jsonObject.getJSONObject("query");
                    JSONObject results = query.getJSONObject("results");
                    JSONObject channel = results.getJSONObject("channel");
                    JSONObject item = channel.getJSONObject("item");

                    JSONArray array = item.getJSONArray("forecast");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Forecast forecast = new Forecast.Builder()
                                .setDate(object.getString("date"))
                                .setDay(object.getString("day"))
                                .setHigh(object.getString("high"))
                                .setLow(object.getString("low"))
                                .setText(object.getString("text"))
                                .build();

                        mListener.onFetchProgress(forecast);

                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }

    public interface ForecastCallbackListener {

        void onFetchStart();
        void onFetchProgress(Forecast forecast);
        void onFetchProgress(List<Forecast> forecastList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
