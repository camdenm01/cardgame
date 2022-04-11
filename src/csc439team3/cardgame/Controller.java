package csc439team3.cardgame;

import java.util.Collections;
import java.util.Scanner;

/**
 * The controller class is what contains all of the logic of the game for things such as
 * player actions (drawing, discarding, flipping), and cycling through the players
 */
public class Controller {
    static int numberOfPlayers;
    static Deck deck;
    static Player[] players;

    public Controller(int n){
        numberOfPlayers = n;
        players = new Player[n];
        for(int i = 0; i< n; i++){
            players[i] = new Player();
        }
        deck = new Deck(numberOfPlayers);
    }

    /**
     * Gives a hand of 6 cards to each player with 2 random ones facing up.
     */
    public void dealHand(){
        for(Player p: players){
            for(int i = 0; i < 6; i++){
                p.hand.add(deck.deck.get(deck.deck.size()-1)); //put the "top card" of the deck and puts into player's hand
                deck.deck.remove(deck.deck.size()-1); //remove the card from the deck so it can't be pulled again
                if(i<2) p.hand.get(i).flipCard(); //two cards get turned face up
            }
            Collections.shuffle(p.hand); //shuffle player's hand, effectively making the two face up cards in random spots
        }
    }


}
