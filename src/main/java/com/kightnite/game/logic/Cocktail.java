package com.kightnite.game.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Cocktail {
    @JsonProperty("strDrink")
    @Getter
    @Setter
    public String name;
    @JsonProperty("idDrink")
    public int id;
    @JsonProperty("strInstructions")
    public String instructions;

    @JsonProperty("strAlcoholic")
    public String alcoholic;
    @JsonProperty("strCategory")
    public String category;
    @JsonProperty("strGlass")
    public String glass;
}

