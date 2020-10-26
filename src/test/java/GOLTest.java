import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GOLTest {

    GOL gameOfLife;
    @BeforeEach
    @Disabled
    void setup(){

    }

    @Test
    void shouldReturnValuesOfInput(){
    gameOfLife = new GOL(2,3);
        assertEquals(gameOfLife.height,3);
        assertEquals(gameOfLife.width,2);
    }
    @Test
    void shouldReturnAnArrayOfInput(){
        gameOfLife = new GOL(4,2);

        assertEquals(gameOfLife.board.length,4);
        assertEquals(gameOfLife.height,2);
    }

}
