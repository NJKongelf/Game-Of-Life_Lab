import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GOLTest {

    GOL gameOfLife;
    @BeforeEach
    @Disabled
    void setup(){

    }

    @Test
    void shouldReturnAnIntOFHowManyActiveCells() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(1, 1);
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(1, 2, false), gameOfLife.getBoardList()));
    }

    @Test
    void shouldReturnAnIntOFHowManyActiveCellsWhenTestingSevralPoints() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(0, 1);
        gameOfLife.setAlive(1, 1);
        gameOfLife.setAlive(2, 1);
        assertEquals(3, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));

    }
}
