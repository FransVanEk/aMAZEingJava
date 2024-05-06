package nl.novi.amazeing.factories;

import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.Player;

public class Challenge {

    private final Maze maze;

    public Player getPlayer() {
        return player;
    }

    private final Player player;

    public Challenge(Maze maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    public Maze getMaze() {
        return maze;
    }
}
