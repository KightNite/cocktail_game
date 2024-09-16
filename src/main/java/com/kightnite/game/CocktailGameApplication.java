package com.kightnite.game;

import com.kightnite.game.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocktailGameApplication implements CommandLineRunner {

	@Autowired
	Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override public void run(String... args) throws Exception {
		menu.createMenu();
	}
}
