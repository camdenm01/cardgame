package csc439team3.cardgame;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {
    Card card = new Card(Card.Suit.CLUB, 11);

    @Test
    void getSuit() {
        assertThat(card.getSuit()).isEqualTo(Card.Suit.CLUB);
    }

    @Test
    void getNumber() {
        assertThat(card.getNumber()).isEqualTo(11);
    }

    @Test
    void setSuit() {
        card.setSuit(Card.Suit.DIAMOND);
        assertThat(card.getSuit()).isEqualTo(Card.Suit.DIAMOND);
    }

    @Test
    void setNumber() {
        card.setNumber(5);
        assertThat(card.getNumber()).isEqualTo(5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->card.setNumber(-1));
        Card joker = new Card(Card.Suit.JOKER, 0);
        assertThat(joker.getNumber()).isEqualTo(0);
    }

    @Test
    void isRed() {
        assertThat(card.isRed()).isFalse();
    }

    @Test
    void isBlack() {
        assertThat(card.isBlack()).isTrue();
    }

    @Test
    void compareTo() {
        Card lower = new Card(Card.Suit.CLUB,5);
        Card higher = new Card(Card.Suit.CLUB,13);
        Card equal = new Card(Card.Suit.CLUB, 11);

        assertThat(card.compareTo(lower)).isEqualTo(1);
        assertThat(card.compareTo(higher)).isEqualTo(-1);
        assertThat(card.compareTo(equal)).isEqualTo(0);
    }

    @Test
    void getScore() {
        assertThat(card.getScore()).isEqualTo(10);
    }

    @Test
    void setScore() {
        card.setNumber(5);
        card.setScore();
        assertThat(card.getScore()).isEqualTo(5);
    }

    @Test
    void flipCard() {
        card.flipCard();
        assertThat(card.isFaceDown()).isEqualTo(false);
    }

    @Test
    void isFaceDown() {
        assertThat(card.isFaceDown()).isEqualTo(true);
    }

    @Test
    void printCard(){
        Card king = new Card(Card.Suit.CLUB, 13);
        king.flipCard();
        assertThat(king.printCard()).isEqualTo("K CLUB");
    }
}

