package csc439team3.cardgame;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class PlayerTest {

    @Test
    void createPlayer(){
        Player player = new Player();
        assertThat(player.hand).isNotNull();
    }

    @Test
    void playerHasUniqueId(){
        Player player = new Player();
        Player player2 = new Player();
        assertThat(player.id).isNotEqualTo(player2.id);

    }

    @Test
    void checkIfAllFaceUp() {
        Player player = new Player();
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        assertThat(player.checkIfAllFaceUp()).isFalse();
    }

    @Test
    void getScore() {
        Player player = new Player();
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,6));
        player.hand.add(new Card(Card.Suit.CLUB,7));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,2));
        player.hand.add(new Card(Card.Suit.CLUB,13));
        assertThat(player.getScore()).isEqualTo(11);
    }

    @Test
    void compareTo() {
        Player player = new Player();
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,6));
        player.hand.add(new Card(Card.Suit.CLUB,7));
        player.hand.add(new Card(Card.Suit.CLUB,5));
        player.hand.add(new Card(Card.Suit.CLUB,2));
        player.hand.add(new Card(Card.Suit.CLUB,13));
        Player player1 = new Player();
        player1.hand.add(new Card(Card.Suit.CLUB,5));
        player1.hand.add(new Card(Card.Suit.CLUB,6));
        player1.hand.add(new Card(Card.Suit.CLUB,2));
        player1.hand.add(new Card(Card.Suit.CLUB,5));
        player1.hand.add(new Card(Card.Suit.CLUB,2));
        player1.hand.add(new Card(Card.Suit.CLUB,13));
        player.getScore();
        player1.getScore();
        assertThat(player.compareTo(player1)).isEqualTo(9);
    }
}
