package com.kightnite.game.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class Drinks {
    @JsonProperty("drinks")
    @Getter
    List<Cocktail> drinks;
}
