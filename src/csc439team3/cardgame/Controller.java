package csc439team3.cardgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * The controller class is what contains all of the logic of the game for things such as
 * player actions (drawing, discarding, flipping), and cycling through the players
 */
public class Controller {
    int numberOfPlayers;
    int numberOfHoles;
    int remainingHoles;
    static Deck deck;
    public static Player[] players;
    public Player[] sortedPlayers;
    public View view;

    /**
     * Controller constructor accepts view
     * @param view
     */
    public Controller(View view){
        this.view = view;
    }

    /**
     * This method cycle through players and calls the other methods to take a player's turn
     * as long as there are cards in the deck
     */
    public int playGolf(){
        view.printTitleScreen();
        getPlayers();
        getHoles();
        try {
            outer:
            while (remainingHoles > 0) {
                dealHand();
                while (deck.deck.size() > 1) {
                    for (Player p : players) {
                        view.displayHand(p);
                        view.displayTopOfDiscard(deck);
                        getAction(p);
                        if(p.checkIfAllFaceUp()){
                            endHole();
                            continue outer;
                        }
                    }
                }
                endHole();
            }
        }
        catch(Exception quit){
            view.quitGame();
            return -1;
        }
        return 0;

    }

    /**
     * Gets the number of players and adds that number to the player array
     */
    public void getPlayers(){
        numberOfPlayers = view.getPlayers();
        players = new Player[numberOfPlayers];
        for(int i = 0; i< numberOfPlayers; i++){
            players[i] = new Player();
        }


    }

    /**
     * Gets the number of holes to play
     */
    public void getHoles(){
        numberOfHoles = view.getHoles();
        remainingHoles = numberOfHoles;

    }






    /**
     * Gives a hand of 6 cards to each player with 2 random ones facing up. Also initializes discard pile for start of game.
     */
    public void dealHand(){
        deck = new Deck(numberOfPlayers);
        for(Player p: players){
            p.hand.clear();
            for(int i = 0; i < 6; i++){
                p.hand.add(deck.deck.get(deck.deck.size()-1)); //put the "top card" of the deck and puts into player's hand
                deck.deck.remove(deck.deck.size()-1); //remove the card from the deck so it can't be pulled again
                if(i<2) p.hand.get(i).flipCard(); //two cards get turned face up
            }
            Collections.shuffle(p.hand); //shuffle player's hand, effectively making the two face up cards in random spots
        }
        deck.discard.add(deck.deck.get(deck.deck.size()-1)); //put the "top card" of the deck into the discard
        deck.deck.remove(deck.deck.size()-1); //remove the card from the deck so it can't be pulled again
        deck.discard.get(0).flipCard(); //flip the discarded card face up
    }

    /**
     * Uses the view to get what action the current player will take
     * @param p the current player
     * @throws Exception for if the player chooses to quit
     */
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
        else if(action == 3){
            if(sortedPlayers == null) {
                sortedPlayers = players.clone();
            }
            view.printScoreboard(numberOfHoles, remainingHoles, sortedPlayers);
            view.displayHand(p);
            getAction(p);
            }

    }

    /**
     * This method is called when the player draws a card from the deck
     * @param p current player
     */
    public void drawFromPile(Player p){
        Card newCard = deck.deck.get(deck.deck.size()-1);
        deck.deck.remove(deck.deck.size()-1);
        newCard.flipCard();
        int swapWith = view.keepOrDiscard(newCard);
        if(swapWith == 0){ //user declined to swap
            deck.discard.add(newCard);
            int card = view.wantToReveal(); //see which card user wants to reveal
            if(card != 0) {//user wants to reveal a card
                if(p.hand.get(card-1).isFaceDown()){
                    p.hand.get(card-1).flipCard();
                    view.displayHand(p);
                }
            }
        }
        else{ //user wants to swap
            deck.discard.add(p.hand.get(swapWith - 1));
            if(deck.discard.get(deck.discard.size()-1).isFaceDown()){
                deck.discard.get(deck.discard.size()-1).flipCard();
            }
            p.hand.set(swapWith - 1, newCard);
        }


    }

    /**
     * This method is called when the player pulls from the discard pile
     * @param p current player
     * @throws Exception if discard pile is empty, and when reprompted the player chooses to quit
     */
    public int drawFromDiscard(Player p) throws Exception {
        Card newCard = deck.discard.get(deck.discard.size()-1);
        deck.discard.remove(deck.discard.size()-1);
        int swapWith = view.keep(newCard);
        deck.discard.add(p.hand.get(swapWith - 1));
        deck.discard.get(deck.discard.size()-1).faceUp();
        p.hand.set(swapWith - 1, newCard);
        return 0;
    }

    /**
     * Decrement numberOfHoles for main loop, calculate player scores with rank
     */
    public void endHole(){
        for(Player player: players){
            player.getScore();
            view.displayHand(player);
        }
        sortedPlayers = players.clone();
        Arrays.sort(sortedPlayers);
        view.printScoreboard(numberOfHoles, remainingHoles, sortedPlayers);
        if(remainingHoles == 1){
            view.printWinner(sortedPlayers);
        }
        remainingHoles--;

    }

}
