package csc439team3.cardgame;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static com.google.common.truth.Truth.assertThat;

public class ControllerTest {
    Controller controller;
    @Test
    void getPlayers(){
        controller = new Controller(new TestView(2,1,1));
        controller.getPlayers();
        assertThat(controller.numberOfPlayers).isEqualTo(2);
        assertThat(controller.players.length).isEqualTo(2);
    }

    @Test
    void dealHand(){
        controller = new Controller(new TestView(2, 1, 1));
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
        controller = new Controller(new TestView(2, -1, 1));
        assertThat(controller.playGolf()).isEqualTo(-1); //should return exit code of -1
    }

    @Test
    void gamePlayedUntilDeckEmpty(){
        controller = new Controller(new TestView(2,1,1));
        assertThat(controller.playGolf()).isEqualTo(0); //should return exit code of 0
    }



    //Following tests throw exception because of the exception thrown when a user quits the game
    @Test
    void playerDrawsFromPile() throws Exception {
        controller = new Controller(new TestView(1, 1, 1));
        controller.getPlayers();
        controller.dealHand();
        //get the card to be swapped and then assert it has changed, and that a card has been discarded
        Card startCard = controller.players[0].hand.get(0);
        if(startCard.isFaceDown()) startCard.flipCard();
        controller.getAction(controller.players[0]);
        assertThat(controller.players[0].hand.get(0)).isNotEqualTo(startCard);
        assertThat(controller.deck.discard.size()).isGreaterThan(0);


    }

    @Test
    void playerDrawsFromPileThenDiscards() throws Exception{
        controller = new Controller(new TestView(1, 1, 0));
        controller.getPlayers();
        controller.dealHand();
        //get the card to be pulled and assert that it is in the discard pile
        Card pulledCard = controller.deck.deck.get(controller.deck.deck.size()-1);
        controller.getAction(controller.players[0]);
        assertThat(controller.deck.discard.get(0)).isEqualTo(pulledCard);
    }

    @Test
    void playerDrawsFromDiscard() throws Exception {
        controller = new Controller(new TestView(1, 2, 1));
        controller.getPlayers();
        controller.dealHand();
        //get the card at the top of the discard pile and then assert it is in player hand, and vice versa
        Card testCard = new Card(Card.Suit.CLUB, 5);
        controller.deck.discard.add(testCard);
        Card startCard = controller.players[0].hand.get(0);
        if(startCard.isFaceDown()) startCard.flipCard();
        Card topDiscard = controller.deck.discard.get(controller.deck.discard.size()-1);
        controller.getAction(controller.players[0]);
        assertThat(controller.players[0].hand.get(0)).isEqualTo(testCard);
        assertThat(controller.deck.discard.get(0)).isEqualTo(startCard);
    }

    @Test
    void illegalDrawFromEmptyDiscardPile() throws Exception{
        controller = new Controller(new TestView(2,1,0));
        controller.getPlayers();
        controller.dealHand();
        assertThat(controller.drawFromDiscard(new Player())).isEqualTo(-1);//returns a code of -1 if discard pile empty
    }


}
