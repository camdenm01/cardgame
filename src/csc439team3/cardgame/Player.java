package csc439team3.cardgame;

import java.util.ArrayList;

/**
 * The PLayer class contains a simple hand array of length 6 to hold the cards a player currently has
 */
public class Player {
    public ArrayList<Card> hand = new ArrayList<>();
    public int id;
    static int idCount = 1;
    public Player(){
        id = idCount;
        idCount++;
    }

}
