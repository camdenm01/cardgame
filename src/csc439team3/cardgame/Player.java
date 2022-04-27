package csc439team3.cardgame;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The PLayer class contains a simple hand array of length 6 to hold the cards a player currently has
 */
public class Player implements Comparable<Player>{
    public ArrayList<Card> hand = new ArrayList<>();
    public int id;
    public int score = 0;
    static int idCount = 1;
    public Player(){
        id = idCount;
        idCount++;
    }

    /**
     * Checks if all cards in hand are face up
     * @return true if all cards in hand are face up
     */
    public boolean checkIfAllFaceUp(){
        int countFaceDown = 0;
        for(Card card: hand){
            if (card.isFaceDown()){
                countFaceDown++;
            }
        }
        if(countFaceDown == 0) return true;
        else return false;
    }

    /**
     * updates and returns player score
     * @return player score
     */
    public int getScore(){
        for(int i =0; i < hand.size(); i++){
            score+= hand.get(i).getScore();
            if(hand.get(i).isFaceDown()) hand.get(i).flipCard();
        }
        for(int i =0; i < hand.size()/2; i++){
            if(hand.get(i).getScore() == hand.get(i+3).getScore()){
                score -= 2*hand.get(i).getScore();
            }
        }
        return score;
    }

    /**
     * compare this player with another player by score
     * @param other other player
     * @return positive if this player has higher score
     */
    @Override
    public int compareTo(Player other) {
        return score - other.score;
    }
}

