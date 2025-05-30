package it.unibo.goosegame.model.player.impl;

import it.unibo.goosegame.controller.cardsatchel.CardSatchelController;
import it.unibo.goosegame.model.player.api.Player;

import java.awt.Color;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of {@link Player}.
 */
public final class PlayerImpl implements Player {
    /**
     * Array containing all the possible player colors.
     */
    private static final Color[] COLORS = { Color.red, Color.yellow, Color.green, Color.blue, Color.cyan };

    private final String name;            // Name of the player, could be hardcoded or dynamic
    private final Color color;            // Color of the player's in game icon
    private int position;                 // Real time position of the player
    private final CardSatchelController stachel;

    /**
     * Constructor for the {@link PlayerImpl} class.
     *
     * @param name name of the player
     * @param colorIndex color index of player's icon
     */
    public PlayerImpl(final String name, final int colorIndex) {
        this.name = name;
        this.color = COLORS[colorIndex];
        this.position = 0;
        this.stachel = new CardSatchelController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goTo(final int cellIndex) {
        this.position = cellIndex;
    }

    // === GETTERS ===
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Color> getColorsList() {
        return List.of(COLORS);
    }

    /**
     * Returns the player's satchel.
     * The returned object is mutable and modifications will affect the player directly.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    @Override
    public CardSatchelController getSatchel() {
        return this.stachel;
    }
}
