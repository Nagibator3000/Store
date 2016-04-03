package web;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.I2F;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherMesenger {
    private  static WeatherMesenger instance;

    public WeatherMesenger() {
    }
    public static WeatherMesenger getInstance(){
        if (instance==null){
            instance = new WeatherMesenger();
        }
        return instance;
    }

    long tmp;

   public float getWeather() throws IOException {
       WeatherRespones weatherRespones = new Gson().fromJson(getUrl("http://api.openweathermap.org/data/2.5/weather?q=voronezh&appid=ef0947e508f1ab41afed02f25aa8db65&units=metric"), WeatherRespones.class);
       return  weatherRespones.main.temp;
   }
    public static String getUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
