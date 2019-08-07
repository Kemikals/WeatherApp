package dev.kemikals;

import java.net.*;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        String query = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&APPID=%s";

        System.out.print("Enter a city: ");
        Config config = new Config();
        Scanner input = new Scanner(System.in);
        String city = input.nextLine();
        URL url = new URL(String.format(query, city.trim(), config.getKey()));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";

        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext()) {
            inline += sc.nextLine();
        }
        sc.close();

        Gson gson = new Gson();
        Weather weather = gson.fromJson(inline, Weather.class);
        System.out.println("City: " + weather.getName());
        System.out.println("Current Temp: " + weather.getMain().getTemp());
        System.out.println("  Max: " + weather.getMain().getTempMax());
        System.out.println("  Min: " + weather.getMain().getTempMin());
        System.out.println("Visibility: " + weather.getVisibility());
        System.out.println("Wind Speed: " + weather.getWind().getSpeed());
        System.out.println(weather.getSys().getSunrise());

    }
}
