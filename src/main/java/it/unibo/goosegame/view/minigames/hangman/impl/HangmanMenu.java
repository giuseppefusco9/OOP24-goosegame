package it.unibo.goosegame.view.minigames.hangman.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import it.unibo.goosegame.controller.minigames.hangman.api.HangmanController;
import it.unibo.goosegame.controller.minigames.hangman.impl.HangmanControllerImpl;
import it.unibo.goosegame.model.general.MinigamesModel.GameState;
import it.unibo.goosegame.model.minigames.hangman.api.HangmanModel;
import it.unibo.goosegame.model.minigames.hangman.impl.HangmanModelImpl;
import it.unibo.goosegame.view.general.impl.MinigameMenuImpl;
import it.unibo.goosegame.view.minigames.hangman.api.HangmanView;

/**
 * Class representing the Hangman game menu.
 * Extends the abstract class MinigameMenuImpl.
 */
public class HangmanMenu extends MinigameMenuImpl {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(HangmanMenu.class.getName());
    private transient HangmanController controller;
    /**
    * The HangmanMenu class represents the menu for the game.
    */
    public HangmanMenu() { 
        super(
            "/img/minigames/background/hangmanImage.png", 
            "Hangman", 
            """
            The game involves guessing a randomly selected word. 
            Initially, the word is represented by a series of symbols: 
                + for vowels
                - for consonants.

            The player has a maximum of 5 incorrect guesses(lives):
            If the player exhausts all their lives without guessing the word correctly, they lose.
            If the player correctly identifies all the letters in the word before running out of lives, they win.
            """
        );
        this.initialize();
    }

    /**
     * @return lines A list of words.
     * @throws IOException If an error occurs while loading the file.
     */
    private List<String> loadWords() {
        final List<String> lines = new ArrayList<>();
        try (InputStream inputStream = HangmanViewImpl.class.getClassLoader().getResourceAsStream("text/parole.txt")) {
            if (inputStream == null) {
                JOptionPane.showMessageDialog(null, "Unable to find the words file.", "Error", JOptionPane.ERROR_MESSAGE);
                return lines;
            }
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                while (scanner.hasNextLine()) {
                   final String word = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                    if (!word.isEmpty()) {
                        lines.add(word);
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading or closing the words file", e);
            JOptionPane.showMessageDialog(null, "Error reading the words file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lines;
    }

    /**
     * Initialize view.
     */
    private void initialize() {
        final HangmanModel model = new HangmanModelImpl(loadWords().toArray(new String[0]));
        getStartButton().addActionListener(e -> {
            final HangmanView view = new HangmanViewImpl();
            view.initializeView();
            this.controller = new HangmanControllerImpl(view, model);
            view.setController(controller);
            this.controller.startGame();
            super.setVisible(false);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return this.controller == null ? GameState.NOT_STARTED : controller.getGameState();
    }
}
