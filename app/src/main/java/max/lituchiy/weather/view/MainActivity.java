package max.lituchiy.weather.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import max.lituchiy.weather.R;
import max.lituchiy.weather.controller.Controller;
import max.lituchiy.weather.model.adapter.ForecastAdapter;
import max.lituchiy.weather.model.pojo.FirstDayForecast;
import max.lituchiy.weather.model.pojo.Forecast;
import max.lituchiy.weather.model.utilities.ExpandableHeightListView;

public class MainActivity extends AppCompatActivity implements Controller.ForecastCallbackListener {

    private Toolbar mToolbar;
    private ExpandableHeightListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Forecast> mForecastList = new ArrayList<>();
    private ForecastAdapter mForecastAdapter;
    private Controller mController;

    ImageView code;
    private TextView temp;
    private TextView city;
    private TextView windChill;
    private TextView windSpeed;
    private TextView windDirection;
    private TextView pressure;
    private TextView humidity;
    private TextView sunrise;
    private TextView sunset;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();
    }

    private void configToolbar() {
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void configViews() {
        mListView = (ExpandableHeightListView) findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe);

        mForecastAdapter = new ForecastAdapter(this, mForecastList);
        mListView.setAdapter(mForecastAdapter);
        mListView.setExpanded(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mForecastList.clear();
                mForecastAdapter = new ForecastAdapter(getApplicationContext(), mForecastList);
                mController.startFetching();
            }
        });

    }

    private void configFirstDayViews(FirstDayForecast firstDayForecast) {
        code = (ImageView) findViewById(R.id.forecastImage);
        temp = (TextView) findViewById(R.id.temperatureTextView);
        city = (TextView) findViewById(R.id.cityTextView);
        windChill = (TextView) findViewById(R.id.windChillTextView);
        windSpeed = (TextView) findViewById(R.id.windSpeedTextView);
        windDirection = (TextView) findViewById(R.id.windDirectionTextView);
        pressure = (TextView) findViewById(R.id.pressureTextView);
        humidity = (TextView) findViewById(R.id.humidityTextView);
        sunrise = (TextView) findViewById(R.id.sunriseTextView);
        sunset = (TextView) findViewById(R.id.sunsetTextView);
        text = (TextView) findViewById(R.id.textTextView);

        int id = getResources().getIdentifier("drawable/icon_" + firstDayForecast.mCode, null, getPackageName());
        code.setBackgroundResource(id);
        temp.setText(firstDayForecast.mTemperature + "°C");

        String[] string = firstDayForecast.mCity.split(" ");
        city.setText(string[0]);
        windChill.setText("Wind chill " + firstDayForecast.windChill + "°C");
        windSpeed.setText("Wind speed " + firstDayForecast.windSpeed + " m/s");
        windDirection.setText("Wind direction " + firstDayForecast.windDirection + "°");
        pressure.setText("Pressure " + firstDayForecast.mPressure + " mmHg");
        humidity.setText("Humidity " + firstDayForecast.mHumidity + "%");
        sunrise.setText("Sunrise " + firstDayForecast.mSunrise);
        sunset.setText("Sunset " + firstDayForecast.mSunset);
        text.setText(firstDayForecast.mCloudy);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Forecast forecast, FirstDayForecast firstDayForecast) {
        mForecastList.add(forecast);
        mForecastAdapter.notifyDataSetChanged();
        configFirstDayViews(firstDayForecast);
    }

    @Override
    public void onFetchProgress(List<Forecast> forecastList) {

    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}
