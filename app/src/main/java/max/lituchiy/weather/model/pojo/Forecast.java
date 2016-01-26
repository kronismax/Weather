package max.lituchiy.weather.model.pojo;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import max.lituchiy.weather.R;

public class Forecast {

    public String mDate, mDay, mHigh, mLow, mText;
    public Drawable drawable = new BitmapDrawable(String.valueOf(R.drawable.a));

    private Forecast(Builder builder) {
        mDate = builder.mDate;
        mDay = builder.mDay;
        mHigh = builder.mHigh;
        mLow = builder.mLow;
        mText = builder.mText;
    }

    public static class Builder {

        private String mDate, mDay, mHigh, mLow, mText;

        public Builder setDate(String mDate) {
            this.mDate = mDate;
            return Builder.this;
        }

        public Builder setDay(String mDay) {
            this.mDay = mDay;
            return Builder.this;
        }

        public Builder setHigh(String mHigh) {
            this.mHigh = mHigh;
            return Builder.this;
        }

        public Builder setLow(String mLow) {
            this.mLow = mLow;
            return Builder.this;
        }

        public Builder setText(String mText) {
            this.mText = mText;
            return Builder.this;
        }

        public Forecast build() {
            return new Forecast(Builder.this);
        }

    }
}
