package csc439team3.cardgame;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ControllerTest {
    Controller controller = new Controller(2);
    @Test
    void checkNumberOfPlayers(){
        assertThat(controller.numberOfPlayers).isEqualTo(2);
        assertThat(controller.players.length).isEqualTo(2);
    }

    @Test
    void dealHand(){
        controller.dealHand();
        assertThat(controller.players[1].hand.get(5)).isInstanceOf(Card.class);
    }

}
