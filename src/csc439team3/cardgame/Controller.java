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
    static View view;

    public Controller(View view){
        this.view = view;
    }

    public void playGolf(){
        getPlayers();
        dealHand();
        try {
            for (int i = 0; i < 9; i++) {
                for (Player p : players) {
                    view.displayHand(p);
                    getAction(p);
                }
            }
        }
        catch(Exception quit){
            view.quitGame();
        }

    }

    public void getPlayers(){
        numberOfPlayers = view.getPlayers();
        players = new Player[numberOfPlayers];
        for(int i = 0; i< numberOfPlayers; i++){
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

    public void getAction(Player p) throws Exception {
        int action = view.getAction();
        if(action == -1){
            throw new Exception();
        }
        else if(action == 1){
            drawFromPile(p);
        }
        else if(action == 2){
            drawFromDiscard(p);
        }
    }

    public void drawFromPile(Player p){
        Card newCard = deck.deck.get(deck.deck.size()-1);
        deck.deck.remove(deck.deck.size()-1);
        newCard.flipCard();
        int swapWith = view.keepOrDiscard(newCard);
        if(swapWith == 0){
            deck.discard.add(newCard);
        }
        else{
            deck.discard.add(p.hand.get(swapWith - 1));
            p.hand.set(swapWith - 1, newCard);
        }


    }

    public void drawFromDiscard(Player p) throws Exception {
        if(deck.discard.size() == 0){
            System.out.println("No cards in the discard pile");
            getAction(p);
            return;
        }
        Card newCard = deck.discard.get(deck.discard.size()-1);
        deck.discard.remove(deck.discard.size()-1);
        if(newCard.isFaceDown()) {
            newCard.flipCard();
        }
        int swapWith = view.keep(newCard);
        deck.discard.add(p.hand.get(swapWith - 1));
        p.hand.set(swapWith - 1, newCard);
    }


}
