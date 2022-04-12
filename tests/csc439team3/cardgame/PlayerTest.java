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

}
