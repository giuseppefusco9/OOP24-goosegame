package it.unibo.goosegame.view.minigames.herdinghound.api;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

import it.unibo.goosegame.controller.minigames.herdinghound.HerdingHoundController;

/**
 * API for the Herding Hound minigame view.
 */
public interface HerdingHoundView {
    /**
     * Starts the initial countdown, then executes the callback.
     * @param onFinish callback to execute at the end of the countdown
     */
    void startCountdown(Runnable onFinish);

    /**
     * Returns true if the countdown is active.
     * @return true if the countdown is active, false otherwise
     */
    boolean isCountdownActive();

    /**
     * Starts the end-of-game blinking animation.
     * @param frame JFrame on which to show the final panel
     * @param hasWon true if the player has won
     */
    void startBlinking(JFrame frame, boolean hasWon);

    /**
     * Updates the view (calls repaint).
     */
    void updateView();

    /**
     * Shows the end-of-game panel.
     * @param frame JFrame on which to show the panel
     * @param hasWon true if the player has won
     */
    void showGameOverPanel(JFrame frame, boolean hasWon);

    /**
     * Adds a key listener to the view.
     * @param keyAdapter
     */
    void addKeyListener(KeyAdapter keyAdapter);

    /**
     * Sets the view focusable.
     * @param b
     */
    void setFocusable(boolean b);

    /**
     * Requests focus in the window.
     * @return true if the focus was successfully requested
     */
    boolean requestFocusInWindow();

    /**
     * Sets the controller.
     * @param controller
     */
    void setController(HerdingHoundController controller);
}
