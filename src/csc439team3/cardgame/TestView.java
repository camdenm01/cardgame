package csc439team3.cardgame;

/**
 * A test view class that doesn't take user input but will return predetermined values for what the player does
 */
public class TestView extends View {
    public int numberOfPlayers, action, swapWith, revealCard, numberOfHoles;

    public TestView(int numberOfPlayers, int action, int swapWith, int revealCard, int numberOfHoles){
        this.numberOfPlayers = numberOfPlayers;
        this.action = action;
        this.swapWith = swapWith;
        this.revealCard = revealCard;
        this.numberOfHoles = numberOfHoles;
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
     * return numberOfHoles
     */
    public int getHoles() {return numberOfHoles;}

    @Override
    /**
     * return action
     */
    public int getAction() {
        return action;
    }

    /**
     * return card index to reveal
     */
    public int wantToReveal(){
        return revealCard;
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
