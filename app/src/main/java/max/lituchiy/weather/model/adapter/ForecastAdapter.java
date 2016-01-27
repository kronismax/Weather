package max.lituchiy.weather.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import max.lituchiy.weather.R;
import max.lituchiy.weather.model.pojo.Forecast;

public class ForecastAdapter extends ArrayAdapter<Forecast> {

    private final Context context;

    public ForecastAdapter(Context context, List<Forecast> objects) {
        super(context, R.layout.item_row, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.item_row, parent, false);

        Forecast forecast = getItem(position);
        TextView date = (TextView) customView.findViewById(R.id.date);
        TextView day = (TextView) customView.findViewById(R.id.day);
        TextView high = (TextView) customView.findViewById(R.id.high);
        TextView low = (TextView) customView.findViewById(R.id.low);
        TextView text = (TextView) customView.findViewById(R.id.text);
        ImageView image = (ImageView) customView.findViewById(R.id.forecastImage);

        date.setText(forecast.mDate);
        day.setText(forecast.mDay);
        high.setText("High " + forecast.mHigh + "°C");
        low.setText("Low " + forecast.mLow + "°C");
        text.setText(forecast.mText);
        int id = context.getResources().getIdentifier("drawable/icon_" + forecast.mCode, null, context.getPackageName());
        image.setBackgroundResource(id);
        return customView;
    }

}
