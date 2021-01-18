package game;

import game.player.HumanConnectPlayer;
import game.player.RandomPlayer;

// :NOTE: # Не реализован турнир
// :NOTE: # Не реализованы компьютерные игроки
public class Main {
    public static void main(String[] args) {
        final Game game = new Game(true, new HumanConnectPlayer(), new RandomPlayer());
        int result;
        do {
            result = game.play(new ConnectState());
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}