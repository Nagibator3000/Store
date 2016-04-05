package weather;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherManager {
    private  static WeatherManager instance;

    public WeatherManager() {
    }
    public static WeatherManager getInstance(){
        if (instance==null){
            instance = new WeatherManager();
        }
        return instance;
    }

    long tmp;

   public float getWeatherInVrn() throws IOException {
       System.out.println("getWeatherInVrn start");
       WeatherResponse weatherResponse = new Gson().fromJson(getUrl("http://api.openweathermap.org/data/2.5/weather?q=voronezh&appid=ef0947e508f1ab41afed02f25aa8db65&units=metric"), WeatherResponse.class);
       System.out.println("getWeatherInVrn end");
       return  weatherResponse.main.temp;
   }
    public float getWeatherInStPtr() throws IOException {
        System.out.println("getWeatherInStPtr start");
        WeatherResponse weatherResponse = new Gson().fromJson(getUrl("http://api.openweathermap.org/data/2.5/weather?q=saintpetersburg&appid=ef0947e508f1ab41afed02f25aa8db65&units=metric"), WeatherResponse.class);
        System.out.println("getWeatherInStPtr end");
        return  weatherResponse.main.temp;
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
