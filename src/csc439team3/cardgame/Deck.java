package csc439team3.cardgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Creates a deck of 52 cards for under 4 players, 104 for over 4 players, and a corresponding discard pile
 * Contains methods to shuffle the deck.
 */
public class Deck {
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> discard = new ArrayList<>();

    public Deck(int numberOfPlayers){
        if(numberOfPlayers > 4) {
            doubleDeck();
        }
        else{
            createDeck();
        }
    }

    /**
     * creates a deck by creating cards of numbers 1-13 for each of the 4 suits
     */
    public void createDeck(){
        Card.Suit suit = Card.Suit.CLUB;
        for(int i= 0; i<4; i++) {
            switch(i) {
                case 0:
                    suit = Card.Suit.CLUB;
                    break;
                case 1:
                    suit = Card.Suit.DIAMOND;
                    break;
                case 2:
                    suit = Card.Suit.SPADE;
                    break;
                case 3:
                    suit = Card.Suit.HEART;
                    break;
            }
            for (int j=1; j < 14; j++) {
                deck.add(new Card(suit, j));
            }
        }
    }

    /**
     * calls createDeck twice to add two full decks to the deck arrayList
     */
    public void doubleDeck(){
        createDeck();
        createDeck();
    }

    /**
     * uses Collections.shuffle to shuffle the deck
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }

}
