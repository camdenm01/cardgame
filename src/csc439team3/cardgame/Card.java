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
        SPADE
    }
    private Suit suit;
    private int number; //face cards represented as follows J=11, Q=12, K=13, A=14

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
        if(number<2 || number > 14){
            throw new IllegalArgumentException();
        }
        this.number = number;
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
}
