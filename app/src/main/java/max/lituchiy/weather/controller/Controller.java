package max.lituchiy.weather.controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import max.lituchiy.weather.model.api.WeatherManager;
import max.lituchiy.weather.model.pojo.FirstDayForecast;
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

                    String city = channel.getJSONObject("location").getString("city");
                    String code = item.getJSONObject("condition").getString("code");
                    String temperature = item.getJSONObject("condition").getString("temp");
                    String chill = channel.getJSONObject("wind").getString("chill");
                    String speed = channel.getJSONObject("wind").getString("speed");
                    String direction = channel.getJSONObject("wind").getString("direction");
                    String pressure = channel.getJSONObject("atmosphere").getString("pressure");
                    String humidity = channel.getJSONObject("atmosphere").getString("humidity");
                    String sunrise = channel.getJSONObject("astronomy").getString("sunrise");
                    String sunset = channel.getJSONObject("astronomy").getString("sunset");
                    String text = item.getJSONObject("condition").getString("text");

                    FirstDayForecast firstDayForecast = new FirstDayForecast.Builder()
                            .mCode(code)
                            .mTemperature(temperature)
                            .mCity(city)
                            .windChill(chill)
                            .windSpeed(speed)
                            .windDirection(direction)
                            .mPressure(pressure)
                            .mHumidity(humidity)
                            .mSunrise(sunrise)
                            .mSunset(sunset)
                            .mCloudy(text)
                            .build();
                    JSONArray array = item.getJSONArray("forecast");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Forecast forecast = new Forecast.Builder()
                                .setDate(object.getString("date"))
                                .setDay(object.getString("day"))
                                .setHigh(object.getString("high"))
                                .setLow(object.getString("low"))
                                .setText(object.getString("text"))
                                .setCode(Integer.parseInt(object.getString("code")))
                                .build();

                        mListener.onFetchProgress(forecast, firstDayForecast);

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

        void onFetchProgress(Forecast forecast, FirstDayForecast firstDayForecast);

        void onFetchProgress(List<Forecast> forecastList);

        void onFetchComplete();

        void onFetchFailed();
    }
}
