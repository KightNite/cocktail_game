package com.kightnite.game.menu;

import com.kightnite.game.logic.Logic;
import com.kightnite.game.persistence.model.User;
import com.kightnite.game.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    private UserRepository userRepository;
    public Scanner scanner = new Scanner(System.in);
    public User selectedUser;

    public Menu() {
    }

    public void createMenu() {

        menu: while (true) {
            System.out.println("---------MAIN MENU---------");
            int selection;

            System.out.println("1. Play");
            System.out.println("2. Exit\n");

            selection = this.getIntInput();

            switch (selection) {
                case 1:
                    selectPlay();
                    continue;
                case 2:
                    break menu;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public String getInput() {
        try {
            return scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input.");
            return null;
        }
    }

    public int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. Enter a number.");
            return -1;
        }
    }

    private void selectPlay() {

        selectedUser = choosePlayer();

        Logic gameLogic = new Logic(this);

        int score = gameLogic.startGame();

        if (selectedUser.getScore() < score) {
            selectedUser.setScore(score);
            saveUser(selectedUser);
        }
    }

    private User choosePlayer() {

        List<User> users = getUsersScore();

        System.out.println("-------HIGH SCORES-------");
        int num = 1;
        for (User user : users) {
            System.out.println(num + ". " + user.getName() + ": " + user.getScore());
            num++;
        }

        System.out.println("Select Player or input '0' to create new player");
        User user;

        while (true) {
            int input = getIntInput();

            if (input == 0) {
                user = createUser();
                break;
            }

            try {
                user = users.get(input - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such user. Try again.");
            }

        }

        return user;
    }

    private List<User> getUsersScore() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    }

    private User createUser() {
        System.out.println("Enter name: ");
        String input = getInput();
        User user = new User(input, 0);

        saveUser(user);
        return user;
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }
}
