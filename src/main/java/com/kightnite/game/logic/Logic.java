package com.kightnite.game.logic;

import com.kightnite.game.api.ApiController;
import com.kightnite.game.menu.Menu;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;

public class Logic {
    private final Menu menu;
    private int score = 0;
    public Cocktail cocktail;
    private HashSet<Integer> guessedDrinks = new HashSet<>();
    private int round = 0;
    private int attempts = 5;

    public Logic(Menu menu) {
        this.menu = menu;
    }


    public int startGame() {
        System.out.println("-----GAME START-----");

        do {
            attempts = 5;
            startRound();
        } while (attempts >= 1);

        System.out.println("GAME OVER | The drink was: " + cocktail.name);
        System.out.println("Your score: " + score);
        return score;
    }

    private void startRound() {
        this.round += 1;
        this.cocktail = getCocktail();

        if (this.cocktail == null){
            // End game
            attempts = 0;
            return;
        }

        GuessWord word = new GuessWord(cocktail);

        do {
            printDrinkInfo(word);

            String input = menu.getInput();

            if (input.equalsIgnoreCase(cocktail.name)) {
                score += calculateScore();
                System.out.println("----------------\nCORRECT!!\n----------------");
                break;
            } else {
                System.out.println("----------------\nWRONG!!!\n----------------");
                attempts -= 1;
                word.revealLetters();
            }

        } while (attempts >= 1);

    }

    public Cocktail getCocktail() {
        Cocktail randomCocktail;
        do {
            System.out.println("Picking drink...");
            randomCocktail = ApiController.getRandomCocktail();

            if (randomCocktail == null){
                System.out.println("Failed to get drink. Press Enter to try again.");
                menu.scanner.nextLine();
                continue;
            }
            if (!guessedDrinks.contains(randomCocktail.id)) {
                randomCocktail.name = StringUtils.stripAccents(randomCocktail.name);
                break;
            }

            System.out.println("Duplicate cocktail found. Press Enter to try again or type 'end' to finish the game and save your score");
            String input = menu.scanner.nextLine();

            if (input.equalsIgnoreCase("end")){
                return null;
            }

        } while (true);

        guessedDrinks.add(randomCocktail.id);

        return randomCocktail;
    }

    private int calculateScore() {
        // The longer the streak the more point you get per guess
        int scoreMultiplier = Math.max(round / 5, 1);

        return 10 * attempts * scoreMultiplier;
    }

    private void printDrinkInfo(GuessWord word) {
        // Print Common info
        System.out.println("Guess this drink!");
        System.out.println("Player: " + menu.selectedUser.getName() + " | HIGH SCORE: " + menu.selectedUser.getScore());
        System.out.println("Current round: " + round + " | Score: " + score);
        System.out.println("Attempts left: " +this.attempts);
        System.out.println(word);
        System.out.println(cocktail.instructions);

        // Print hints based on attempt number
        String category = "Hidden";
        String alcoholic = "Hidden";
        String glass = "Hidden";
        if (attempts <= 4) {
            alcoholic = cocktail.alcoholic;
        }
        if (attempts <= 3) {
            glass = cocktail.glass;
        }
        if (attempts <= 2) {
            category = cocktail.category;
        }

        System.out.println("Is Alcoholic?: " + alcoholic);
        System.out.println("Served glass: " + glass);
        System.out.println("Category: " + category);

    }
}
