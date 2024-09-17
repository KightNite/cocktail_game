package com.kightnite.game.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GuessWord {
    private final Cocktail cocktail;
    public int length;
    public HashSet<Integer> revealedLetters = new HashSet<>();
    // Number of letters not counting the spaces
    public int letterCount;



    public GuessWord(Cocktail cocktail){
        this.cocktail = cocktail;
        length = cocktail.name.length();
        revealSpacesAndSymbols();
        letterCount = cocktail.name.length() - revealedLetters.size();
    }

    public void revealLetters() {
        // Set number of letters revealed
        int revealNum = letterCount / 5;
        if (revealNum == 0) revealNum = 1;

        int revealedCount = revealedLetters.size();

        // Don't reveal the last letter
        if (length - revealedCount == 1) return;

        Random random = new Random();
        ArrayList<Integer> unrevealedLetters = new ArrayList<>();

        // Create list of unrevealed letters
        for (int i = 0; i < length; i++) {
            if (!revealedLetters.contains(i)) {
                unrevealedLetters.add(i);
            }
        }

        // Reveal letters randomly
        for (int i = 0; i < revealNum; i++) {
            int randomIndex = random.nextInt(unrevealedLetters.size());
            revealedLetters.add(unrevealedLetters.get(randomIndex));
            unrevealedLetters.remove(randomIndex);
        }

    }

    // Reveal spaces, dots and dashes in the name.
    private void revealSpacesAndSymbols() {

        for (int i = 0; i < length; i++) {
            char c = cocktail.name.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                revealedLetters.add(i);
            }
        }
    }

    @Override
    public String toString() {

        String result = "";
        for (int i = 0; i < length; i++) {
            char c = cocktail.name.charAt(i);
            if (revealedLetters.contains(i)) {
                result += c + " ";
            } else {
                result += "_ ";
            }
        }

//        result += "\n " +cocktail.name;
        return result;
    }
}
