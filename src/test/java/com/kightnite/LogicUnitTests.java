package com.kightnite;

import com.kightnite.game.logic.Logic;
import com.kightnite.game.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LogicUnitTests {

    @Test
    public void GetCocktailFromApi() {
        Logic logic = new Logic(new Menu());

        logic.getCocktail();

        Assertions.assertNotNull(logic.cocktail);
    }


}
