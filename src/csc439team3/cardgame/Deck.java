package csc439team3.cardgame;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck = new ArrayList<>();

    public Deck(int numberOfPlayers){
        if(numberOfPlayers > 4) {
            doubleDeck();
        }
        else{
            createDeck();
        }
    }

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

    public void doubleDeck(){
        createDeck();
        createDeck();
    }

}
