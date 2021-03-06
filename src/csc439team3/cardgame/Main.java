package csc439team3.cardgame;

/**
 * Main class contains main method which will simulate the play of a card game.
 */
public class Main {
    /**
     * Used to simulate the process of a card game.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.playGolf();
    }
}