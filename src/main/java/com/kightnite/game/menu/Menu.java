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

            selection = this.GetIntInput();

            switch (selection) {
                case 1:
                    PlayMenu();
                    continue;
                case 2:
                    break menu;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public String GetInput() {
        try {
            return scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input.");
            return null;
        }
    }

    public int GetIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. Enter a number.");
            return -1;
        }
    }

    private void PlayMenu() {

        selectedUser = ChoosePlayer();

        Logic gameLogic = new Logic(this);

        int score = gameLogic.StartGame();

        if (selectedUser.getScore() < score) {
            selectedUser.setScore(score);
            SaveUser(selectedUser);
        }
    }

    private User ChoosePlayer() {

        List<User> users = GetUsersScore();

        System.out.println("-------HIGH SCORES-------");
        int num = 1;
        for (User user : users) {
            System.out.println(num + ". " + user.getName() + ": " + user.getScore());
            num++;
        }

        System.out.println("Select Player or input '0' to create new player");
        User user;

        while (true) {
            int input = GetIntInput();

            if (input == 0) {
                user = CreateUser();
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

    private List<User> GetUsersScore() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
    }

    private User CreateUser() {
        System.out.println("Enter name: ");
        String input = GetInput();
        User user = new User(input, 0);

        SaveUser(user);
        return user;
    }

    private void SaveUser(User user) {
        userRepository.save(user);
    }
}
