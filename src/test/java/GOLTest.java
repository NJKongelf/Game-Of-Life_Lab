import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GOLTest {

    GOL gameOfLife;
    @BeforeEach
    @Disabled
    void setup(){

    }

    @Test
    @DisplayName("Testing one point and get every one back to see that point")
    void shouldReturnAnIntOFHowManyActiveCells() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(1, 1);
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(1, 2, false), gameOfLife.getBoardList()));
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(2, 2, false), gameOfLife.getBoardList()));
    }

    @Test
    @DisplayName("Testing several points and get back neigbours sees the points")
    void shouldReturnAnIntOFHowManyActiveCellsWhenTestingSevralPoints() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(0, 1);
        gameOfLife.setAlive(1, 1);
        gameOfLife.setAlive(2, 1);
        assertEquals(3, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));
        assertEquals(2, gameOfLife.countAliveNeighbours(new Point(2, 2, false), gameOfLife.getBoardList()));

    }

    @Test
    @DisplayName("Testing several points and killing one point and get back neigbours sees the points")
    void shouldReturnAnIntOFHowManyActiveCellsAfterKillinCells() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(0, 1);
        gameOfLife.setAlive(1, 1);
        gameOfLife.setAlive(2, 1);
        assertEquals(3, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));
        assertEquals(2, gameOfLife.countAliveNeighbours(new Point(2, 2, false), gameOfLife.getBoardList()));
        gameOfLife.setAlive(0, 1);
        gameOfLife.setAlive(1, 1);
        gameOfLife.setDead(2, 1);
        assertEquals(2, gameOfLife.countAliveNeighbours(new Point(1, 0, false), gameOfLife.getBoardList()));
        assertEquals(1, gameOfLife.countAliveNeighbours(new Point(2, 2, false), gameOfLife.getBoardList()));
    }

    @Test
    @DisplayName("Testing so we get back an string with grid of live and dead cells")
    void shouldReturnAnStringOFHowManyActiveCellsWhenTestingSevralPoints() {
        gameOfLife = new GOL(3, 3);
        gameOfLife.setAlive(0, 1);
        gameOfLife.setAlive(1, 1);
        gameOfLife.setAlive(2, 1);
        String Line = ".*.\n" + ".*.\n" + ".*.\n";
        assertEquals(Line, gameOfLife.printBoard());
        Line = "...\n" + "***\n" + "...\n";
        gameOfLife.step();
        assertEquals(Line, gameOfLife.printBoard());
    }

}
