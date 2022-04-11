package csc439team3.cardgame;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class PlayerTest {

    @Test
    void createPlayer(){
        Player player = new Player();
        assertThat(player.hand).isNotNull();
    }

}
