package org.openweathermap.driver;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Api {

    static String apiKey = "Insira sua APIKEY";

    public static String getLatLon(String cidade) {
        String retorno = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet("http://api.openweathermap.org/geo/1.0/direct?q=" + cidade + "&appid=" + apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp.replace("[", "").replace("]", ""));
            System.out.println("valor da lat: " + obj.getBigDecimal("lat"));
            System.out.println("valor da lon: " + obj.getBigDecimal("lon"));
            retorno = "lat="
                    .concat(String.valueOf(obj.get("lat")))
                    .concat("&lon=")
                    .concat(String.valueOf(obj.get("lon")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;

    }

    public static String currentWeather(String cidade) {
        String retorno = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uriCelsius = "https://api.openweathermap.org/data/2.5/weather?" + getLatLon(cidade) + "&appid=" + apiKey + "&units=metric";
            HttpGet getCelsius = new HttpGet(uriCelsius);
            HttpResponse responseCelsius = client.execute(getCelsius);
            String respCelsius = EntityUtils.toString(responseCelsius.getEntity());
            JSONObject objCelsius = new JSONObject(respCelsius);

            System.out.println(objCelsius.getJSONObject("main"));
            JSONObject mainCelsius = objCelsius.getJSONObject("main");
            System.out.println("Retorno da API com o valor da temperatura em °C: " + mainCelsius.get("temp"));

            String uriFahrenheit = "https://api.openweathermap.org/data/2.5/weather?" + getLatLon(cidade) + "&appid=" + apiKey + "&units=imperial";
            HttpGet getFahrenheit = new HttpGet(uriFahrenheit);
            HttpResponse responseFahrenheit = client.execute(getFahrenheit);
            String respFahrenheit = EntityUtils.toString(responseFahrenheit.getEntity());
            JSONObject objFahrenheit = new JSONObject(respFahrenheit);

            System.out.println(objFahrenheit.getJSONObject("main"));
            JSONObject mainFahrenheit = objFahrenheit.getJSONObject("main");
            System.out.println("Retorno da API com o valor da temperatura em °F: " + mainFahrenheit.get("temp"));

            retorno = String.valueOf(objCelsius.get("name"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

}
