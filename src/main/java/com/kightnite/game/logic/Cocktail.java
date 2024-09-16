package com.kightnite.game.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Cocktail {
    @JsonProperty("strDrink")
    @Getter
    String name;
    @JsonProperty("idDrink")
    int id;
    @JsonProperty("strInstructions")
    String instructions;

    @JsonProperty("strAlcoholic")
    String alcoholic;
    @JsonProperty("strCategory")
    String category;
    @JsonProperty("strGlass")
    String glass;
}

