package dev.kemikals;
import java.net.*;
import java.util.Scanner;
import com.google.gson.*;
import java.io.IOException;
/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        String query = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&APPID=";

        System.out.print("Enter a city: ");
        Scanner input = new Scanner(System.in);
        String city = input.nextLine();
        URL url = new URL(String.format(query, city.trim()));
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
        System.out.println("Wind Speed: " + weather.getWind().getSpeed());

    }
}
