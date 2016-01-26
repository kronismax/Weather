package max.lituchiy.weather.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import max.lituchiy.weather.R;
import max.lituchiy.weather.controller.Controller;
import max.lituchiy.weather.model.adapter.ForecastAdapter;
import max.lituchiy.weather.model.pojo.Forecast;
import max.lituchiy.weather.model.utilities.ExpandableHeightListView;

public class MainActivity extends AppCompatActivity implements Controller.ForecastCallbackListener {

    private Toolbar mToolbar;
    private ExpandableHeightListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Forecast> mForecastList = new ArrayList<>();
    private ForecastAdapter mForecastAdapter;
    private Controller mController;

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
        mListView.setExpanded(true); //!
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mForecastAdapter = new ForecastAdapter(getApplicationContext(), mForecastList);
                mController.startFetching();
            }
        });

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
    public void onFetchProgress(Forecast forecast) {
        mForecastList.add(forecast);
        mForecastAdapter.notifyDataSetChanged();
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
