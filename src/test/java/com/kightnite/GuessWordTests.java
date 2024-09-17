package com.kightnite;

import com.kightnite.game.logic.Cocktail;
import com.kightnite.game.logic.GuessWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuessWordTests {

    GuessWord guessWord;

    @Test
    public void testSpacesAndSymbolsAreRevealed() {
        // Case 1
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Te st");
        guessWord = new GuessWord(cocktail);

        Assertions.assertEquals(guessWord.toString(), "_ _   _ _ ");
        Assertions.assertEquals(guessWord.revealedLetters.size(), 1);

        // Case 2
        cocktail.setName("Te-st");
        guessWord = new GuessWord(cocktail);
        Assertions.assertEquals(guessWord.toString(), "_ _ - _ _ ");
        Assertions.assertEquals(guessWord.revealedLetters.size(), 1);

        // Case 3
        cocktail.setName("Te.st");
        guessWord = new GuessWord(cocktail);
        Assertions.assertEquals(guessWord.toString(), "_ _ . _ _ ");
        Assertions.assertEquals(guessWord.revealedLetters.size(), 1);

        // Case 4
        cocktail.setName("1 (23)");
        guessWord = new GuessWord(cocktail);
        Assertions.assertEquals(guessWord.toString(), "_   ( _ _ ) ");
        Assertions.assertEquals(guessWord.revealedLetters.size(), 3);
    }

    @Test
    public void testNumberOfLettersAreRevealedBasedOnWordLength() {

        // Case 1 - reveals 1 letter
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Test");
        guessWord = new GuessWord(cocktail);

        Assertions.assertEquals(guessWord.revealedLetters.size(), 0);

        guessWord.revealLetters();
        Assertions.assertEquals(guessWord.revealedLetters.size(), 1);

        // Case 2 - reveals 2 letters
        cocktail.setName("TestSample2");
        guessWord = new GuessWord(cocktail);

        Assertions.assertEquals(guessWord.revealedLetters.size(), 0);

        guessWord.revealLetters();
        Assertions.assertEquals(guessWord.revealedLetters.size(), 2);
    }

    @Test
    public void revealLettersDoesNotRevealLastLetterTest() {

        // Case 1 - reveals 1 letter
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Test");
        guessWord = new GuessWord(cocktail);

        Assertions.assertEquals(guessWord.revealedLetters.size(), 0);

        guessWord.revealLetters();
        Assertions.assertEquals(guessWord.revealedLetters.size(), 1);

        // Case 2 - reveals 2 letters
        cocktail.setName("TestSample2");
        guessWord = new GuessWord(cocktail);

        Assertions.assertEquals(guessWord.revealedLetters.size(), 0);

        guessWord.revealLetters();
        Assertions.assertEquals(guessWord.revealedLetters.size(), 2);
    }
}
