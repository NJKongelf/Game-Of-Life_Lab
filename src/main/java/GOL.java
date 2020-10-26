import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class GOL {
    int width;
    int height;
    int[][] board;
    List<Point> boardList = new ArrayList<Point>();


    public GOL(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        for (int i = 0; i <= width ; i++) {
            for (int j = 0; j <=height ; j++) {
                boardList.add(new Point(i,j,false));
            }
        }

    }


    private String accept(Point point) {
        if (!point.isState())
            return ".";
        return "*";
    }

    public void printBoard() {
        System.out.println(" -------");

        for (int i = 0; i <=width  ; i++) {
            String line = "|";
            System.out.print(line);
            int finalI = i;

             boardList.stream()
                    .filter(point -> point.getX() == finalI)

                    .forEach(point -> {
                        System.out.print(accept(point));
                    });

            System.out.println(line);
        }

       /* for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0) {
                    line += ".";
                } else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }*/
        System.out.println(" -------\n");
    }

    public void setAlive(int x, int y) {
        boardList.stream()
                .filter(px -> px.getX()==x)
                .filter(py -> py.getY()==y)
                .forEach(point -> point.setState(true));
        //this.board[x][y] = 1;
    }

    public void setDead(int x, int y) {
        boardList.stream()
                .filter(point -> point.getX()==x)
                .filter(point -> point.getY()==y)
                .forEach(point -> point.setState(false));
        // this.board[x][y] = 0;
    }

    public int countAliveNeighbours(Point point, List<Optional<Point>> templist) {
    //public int countAliveNeighbours(int x, int y) {
        int x = point.getX();
        int y = point.getY();
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }

        return this.board[x][y];
    }

    public void step() {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == 1) {
                    if (aliveNeighbours < 2) {
                        newBoard[x][y] = 0;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    } else {
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newBoard[x][y] = 1;
                    }
                }

            }
        }

        this.board = newBoard;
    }

    public static void main(String[] args) {
        GOL simulation = new GOL(8, 5);

        simulation.setAlive(2, 2);
        simulation.setAlive(3, 2);
        simulation.setAlive(4, 2);

        simulation.printBoard();

        simulation.step();

        simulation.printBoard();

        simulation.step();

        simulation.printBoard();

    }
}
class Point{
    private final int x;
    private final int y;
    private boolean state;

    public Point(int x, int y, boolean state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
