package csc439team3.cardgame;

/**
 * Card class represents a playing card. Each card has a suit and number, face values are represented as
 * values above 10. Card has standard getter and setter methods, along with isRed(), isBlack(), and compareTo().
 */
public class Card {
    public enum Suit{
        CLUB,
        DIAMOND,
        HEART,
        SPADE,
        //Removed joker and associated tests, not in wikipedia rules
    }
    private Suit suit;
    private int number; //face cards represented as follows J=11, Q=12, K=13, A=1,
    private int score; // A=1, J,Q=10, K=0, 2=-2
    private boolean faceDown = true;

    /**
     * Card constructor sets members to arguments.
     * @param suit card suit
     * @param number card number
     */
    public Card(Suit suit, int number){
        setSuit(suit);
        setNumber(number);
    }

    /**
     * Returns card suit
     * @return card suit
     */
    public Suit getSuit(){
        return suit;
    }

    /**
     * Returns card number
     * @return card number
     */
    public int getNumber(){
        return number;
    }

    /**
     * Returns score of card
     * @return card score
     */
    public int getScore(){return score;}

    /**
     * Sets card suit
     * @param suit card suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Sets card number
     * @param number card number
     */
    public void setNumber(int number) {
        if(number<0 || number > 13){
            throw new IllegalArgumentException();
        }
        this.number = number;
        setScore();
    }

    /**
     * sets card score
     */
    public void setScore(){
        if(number == 2){
            score = -2;
        }
        else if (number == 11 || number == 12){
            score = 10;
        }
        else if (number == 13){
            score = 0;
        }
        else {
            score = number;
        }

    }

    /**
     * Checks if card suit is red
     * @return true for cards with suit diamond or heart
     */
    public boolean isRed(){
        return (suit== Suit.DIAMOND || suit== Suit.HEART);
    }

    /**
     * Checks if card suit is black
     * @return true for cards with suit club or spade
     */
    public boolean isBlack(){
        return (suit== Suit.CLUB || suit== Suit.SPADE);
    }

    /**
     * Checks if another card is higher, lower, or equal to its value
     * @param other Card being compared
     * @return 1 if the other card is lower, -1 if higher, 0 if equal
     */
    public int compareTo(Card other){
        if(number > other.getNumber()){
            return 1;
        }
        else if(number < other.getNumber()){
            return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * makes card face up
     */
    public void faceUp(){
        if(isFaceDown()){
            faceDown=false;
        }
    }

    /**
     * flips the card to face up
     */
    public void flipCard(){
        faceDown = !faceDown;
    }

    /**
     * Tells if card is currently face down and unreadable
     * @return boolean if card is face down or up
     */
    public boolean isFaceDown(){
        return faceDown;
    }

    /**
     * A method to print the card so a player can see the cards they have.
     * Replaces numbers with A, J, Q, K when appropriate.
     * @return a string representation of the card
     */
    public String printCard(){
        if(faceDown) return "------ ";
        char value = ' ';
        if(number > 1 && number <=10 ) return (number + " " +suit + " ");
        else{
            if(number ==1) value = 'A';
            else if(number == 11) value = 'J';
            else if(number == 12) value = 'Q';
            else if(number == 13) value = 'K';
        }
        return (value + " " + suit);
    }

}
