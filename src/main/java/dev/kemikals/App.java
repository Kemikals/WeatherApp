package dev.kemikals;

import java.net.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;

import java.awt.Image;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        String weatherQuery = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&APPID=%s";
        String heatMapQuery = "https://tile.openweathermap.org/map/temp_new/3/2/2.png?appid=%s";

        System.out.print("Enter a city: ");
        Config config = new Config();
        Scanner input = new Scanner(System.in);
        String city = input.nextLine();

     

        Gson gson = new Gson();
        Weather weather = gson.fromJson(getJson(weatherQuery, city, config), Weather.class);
        System.out.println("City: " + weather.getName());
        System.out.println("Current Temp: " + weather.getMain().getTemp());
        System.out.println("  Max: " + weather.getMain().getTempMax());
        System.out.println("  Min: " + weather.getMain().getTempMin());
        System.out.println("Visibility: " + weather.getVisibility());
        System.out.println("Wind Speed: " + weather.getWind().getSpeed());

        JFrame jframe = new JFrame();
        URL imageUrl = new URL(String.format(heatMapQuery, config.getKey()));
        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
        conn.connect();
        System.out.println(imageUrl);
        Image image = ImageIO.read(imageUrl.openStream());
        ImageIcon image2 = new ImageIcon(image);
        JLabel label = new JLabel(image2);
        jframe.add(label);
        jframe.setSize(800,600);
        jframe.setVisible(true);

    }

    public static String getJson(String query, String input, Config config) {
        String inline = "";

        try {
            URL url = new URL(String.format(query, input.trim(), config.getKey()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            sc.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inline;

    }

}
