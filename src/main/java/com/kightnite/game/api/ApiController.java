package com.kightnite.game.api;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kightnite.game.logic.Cocktail;
import com.kightnite.game.logic.Drinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiController {
    public static Cocktail getRandomCocktail(){

        try {
//            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=12768");
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String data = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }

                scanner.close();

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                Drinks drinks = mapper.readValue(data, Drinks.class);

                return drinks.getDrinks().getFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
