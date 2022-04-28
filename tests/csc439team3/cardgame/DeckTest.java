package csc439team3.cardgame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTest {
    @Test
    void createDeck(){
        Deck deck = new Deck(4);
        assertThat(deck.deck.size()).isEqualTo(52);
    }

    @Test
    void createDoubleDeck(){
        Deck deck = new Deck(5);
        assertThat(deck.deck.size()).isEqualTo(104);
    }

    @Test
    void shuffle(){
        Deck deck = new Deck(2);
        Deck notShuffled = new Deck(2);
        notShuffled.deck = (ArrayList<Card>) deck.deck.clone();
        deck.shuffle();
        assertThat(deck.deck).isNotEqualTo(notShuffled.deck);
    }


}
