## Description
This is a simple word guessing game console application using the drinks from https://www.thecocktaildb.com/api.php as words to guess.

  * Game rules:
    * Random cocktail instructions is shown to the player together with number of letters in the name of the cocktail (e.g. "Mojito" -> "_ _ _ _ _ _")
    * Player has 5 attempts to guess the name of the cocktail
    * If player answers correctly the game continues with a new random cocktail and score is increased
    * If player answers wrongly or skips round the game reveals random letters of the drink name and some additional hints
    * If player fails to guess the cocktail after 5 attempts the game ends and high score is updated
    * In one game same cocktail shouldn't appear twice
