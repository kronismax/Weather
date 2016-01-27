package max.lituchiy.weather.model.pojo;

public class Forecast {

    public String mDate, mDay, mHigh, mLow, mText;
    public int mCode;

    private Forecast(Builder builder) {
        mDate = builder.mDate;
        mDay = builder.mDay;
        mHigh = builder.mHigh;
        mLow = builder.mLow;
        mText = builder.mText;
        mCode = builder.mCode;
    }

    public static class Builder {

        private String mDate, mDay, mHigh, mLow, mText;
        private int mCode;

        public Builder setDate(String mDate) {
            this.mDate = mDate;
            return this;
        }

        public Builder setDay(String mDay) {
            this.mDay = mDay;
            return this;
        }

        public Builder setHigh(String mHigh) {
            this.mHigh = mHigh;
            return this;
        }

        public Builder setLow(String mLow) {
            this.mLow = mLow;
            return this;
        }

        public Builder setText(String mText) {
            this.mText = mText;
            return this;
        }

        public Builder setCode(int mCode) {
            this.mCode = mCode;
            return this;
        }

        public Forecast build() {
            return new Forecast(this);
        }

    }
}
