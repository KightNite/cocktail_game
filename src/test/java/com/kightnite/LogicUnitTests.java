package com.kightnite;

import com.kightnite.game.api.ApiController;
import com.kightnite.game.logic.Cocktail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LogicUnitTests {

    @Test
    public void getCocktailFromApi() {

        Cocktail cocktail = ApiController.getRandomCocktail();

        Assertions.assertNotNull(cocktail);
    }


}
