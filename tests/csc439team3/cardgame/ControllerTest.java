package csc439team3.cardgame;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static com.google.common.truth.Truth.assertThat;

public class ControllerTest {
    Controller controller;
    @Test
    void getPlayers(){
        controller = new Controller(new TestView(2,1,1,1, 2));
        controller.getPlayers();
        assertThat(controller.numberOfPlayers).isEqualTo(2);
        assertThat(controller.players.length).isEqualTo(2);
    }

    @Test
    void dealHand(){
        controller = new Controller(new TestView(2, 1, 1,1, 2));
        controller.getPlayers(); //needed so that player array isn't null
        controller.dealHand();
        int count = 0;
        for(Card c: controller.players[1].hand){
            if (c.isFaceDown()) count++;
        }
        assertThat(count).isEqualTo(4);
    }

    @Test
    void playerQuits(){
        controller = new Controller(new TestView(2, -1, 1,1, 2));
        assertThat(controller.playGolf()).isEqualTo(-1); //should return exit code of -1
    }

    @Test
    void gamePlayedUntilDeckEmpty(){
        controller = new Controller(new TestView(2,1,1,1, 1));
        assertThat(controller.playGolf()).isEqualTo(0); //should return exit code of 0
    }


    //Following tests throw exception because of the exception thrown when a user quits the game
    @Test
    void playerDrawsFromPile() throws Exception {
        controller = new Controller(new TestView(1, 1, 1,1, 2));
        controller.getPlayers();
        controller.dealHand();
        //get the card to be swapped and then assert it has changed, and that a card has been discarded
        Card startCard = controller.players[0].hand.get(0);
        controller.getAction(controller.players[0]);
        assertThat(controller.players[0].hand.get(0)).isNotEqualTo(startCard);
        assertThat(controller.deck.discard.size()).isGreaterThan(0);
    }

    @Test
    void playerDrawsFromPileThenDiscards() throws Exception{
        controller = new Controller(new TestView(1, 1, 0,1, 2));
        controller.getPlayers();
        controller.dealHand();
        //get the card to be pulled and assert that it is in the discard pile and that card 1 is face up
        Card pulledCard = controller.deck.deck.get(controller.deck.deck.size()-1);
        controller.getAction(controller.players[0]);
        assertThat(controller.deck.discard.get(1)).isEqualTo(pulledCard);
        assertThat(controller.players[0].hand.get(0).isFaceDown()).isFalse(); //assert that desired card is revealed
    }

    @Test
    void playerDrawsFromDiscard() throws Exception {
        controller = new Controller(new TestView(1, 2, 1,1, 2));
        controller.getPlayers();
        controller.dealHand();
        //get the card at the top of the discard pile and then assert it is in player hand, and vice versa
        Card topOfDiscard = controller.deck.discard.get(0);
        Card playersFirstCard = controller.players[0].hand.get(0);
        controller.getAction(controller.players[0]);
        assertThat(controller.players[0].hand.get(0)).isEqualTo(topOfDiscard);
        assertThat(controller.deck.discard.get(0)).isEqualTo(playersFirstCard);
    }


    @Test
    void playerChecksScore() throws Exception {
        controller = new Controller(new TestView(1, 3, 1,1, 2));
        controller.getPlayers();
        controller.dealHand();
        controller.getAction(controller.players[0]);
        assertThat(controller.sortedPlayers).isNotEmpty();
    }

    @Test
    void holeEndsWhenAllCardsUp(){
        controller = new Controller(new TestView(1,1,0,1, 1));
        assertThat(controller.playGolf()).isEqualTo(0);
    }

    @Test
    void endHole(){
        controller = new Controller(new TestView(2,1,1,1, 1));
        controller.getPlayers();
        controller.getHoles();
        controller.dealHand();
        controller.endHole();
        assertThat(controller.players[0].score).isNotNull();
        assertThat(controller.remainingHoles).isEqualTo(0);
    }
}
