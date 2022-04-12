package csc439team3.cardgame;

/**
 * A test view class that doesn't take user input but will return predetermined values for what the player does
 */
public class TestView extends View {
    public int numberOfPlayers, action, swapWith;

    public TestView(int numberOfPlayers, int action, int swapWith){
        this.numberOfPlayers = numberOfPlayers;
        this.action = action;
        this.swapWith = swapWith;
    }

    @Override
    /**
     * return numberOfPlayers
     */
    public int getPlayers() {
        return numberOfPlayers;
    }

    @Override
    /**
     * return action
     */
    public int getAction() {
        return action;
    }

    @Override
    /**
     * return the card to swap with (or 0 to discard)
     */
    public int keepOrDiscard(Card card) {
        return swapWith;
    }

    @Override
    /**
     * return the card to swap with
     */
    public int keep(Card card) {
        return swapWith;
    }
}
