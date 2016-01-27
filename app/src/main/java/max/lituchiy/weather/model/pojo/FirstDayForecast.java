package max.lituchiy.weather.model.pojo;

public class FirstDayForecast {

    public String mCode, mTemperature, mCity, windChill, windSpeed, windDirection, mPressure, mHumidity, mSunrise, mSunset, mCloudy;

    public FirstDayForecast(Builder builder) {
        mCode = builder.mCode;
        mTemperature = builder.mTemperature;
        mCity = builder.mCity;
        windChill = builder.windChill;
        windSpeed = builder.windSpeed;
        windDirection = builder.windDirection;
        mPressure = builder.mPressure;
        mHumidity = builder.mHumidity;
        mSunrise = builder.mSunrise;
        mSunset = builder.mSunset;
        mCloudy = builder.mCloudy;
    }

    public static final class Builder {
        private String mCode;
        private String mTemperature;
        private String mCity;
        private String windChill;
        private String windSpeed;
        private String windDirection;
        private String mPressure;
        private String mHumidity;
        private String mSunrise;
        private String mSunset;
        private String mCloudy;


        public Builder mCode(String val) {
            mCode = val;
            return this;
        }

        public Builder mTemperature(String val) {
            mTemperature = val;
            return this;
        }

        public Builder mCity(String val) {
            mCity = val;
            return this;
        }

        public Builder windChill(String val) {
            windChill = val;
            return this;
        }

        public Builder windSpeed(String val) {
            windSpeed = val;
            return this;
        }

        public Builder windDirection(String val) {
            windDirection = val;
            return this;
        }

        public Builder mPressure(String val) {
            mPressure = val;
            return this;
        }

        public Builder mHumidity(String val) {
            mHumidity = val;
            return this;
        }

        public Builder mSunrise(String val) {
            mSunrise = val;
            return this;
        }

        public Builder mSunset(String val) {
            mSunset = val;
            return this;
        }

        public Builder mCloudy(String val) {
            mCloudy = val;
            return this;
        }

        public FirstDayForecast build() {
            return new FirstDayForecast(this);
        }
    }
}
