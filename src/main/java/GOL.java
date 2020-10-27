import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GOL {
    int width;
    int height;
    int[][] board;
    List<Optional<Point>> boardList = new ArrayList<Optional<Point>>();


    public GOL(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardList.add(Optional.of(new Point(i, j, false)));
            }
        }

    }


    private String accept(Point point) {
        if (!point.isState())
            return ".";
        return "*";
    }

    public void printBoard() {
        System.out.println("-------");

        for (int i = 0; i < width; i++) {
            String line = "|";
            System.out.print(line);
            int finalI = i;

            boardList.stream()
                    .filter(point ->
                            point.get().getX() == finalI)
                    .forEach(point -> {
                        System.out.print(accept(point.get()));
                    });

            System.out.println(line);
        }
        System.out.println("-------\n");
    }

    public void setAlive(int x, int y) {
        boardList.stream()
                .filter(px -> px.get().getX() == x)
                .filter(py -> py.get().getY() == y)
                .forEach(point -> point.get().setState(true));
    }

    public void setDead(int x, int y) {
        boardList.stream()
                .filter(point -> point.get().getX() == x)
                .filter(point -> point.get().getY() == y)
                .forEach(point -> point.get().setState(false));
    }

    public long countAliveNeighbours(Point point, List<Optional<Point>> templist) {
        //public int countAliveNeighbours(int x, int y) {
        int x = point.getX();
        int y = point.getY();
        long count;
        long above1 = templist.stream()
                .filter(point1 -> point1.get().getX() == x - 1)
                .filter(point1 -> point1.get().getY() == y - 1)
                .filter(point1 -> point1.get().isState())
                .count();
        long above2 = templist.stream()
                .filter(point1 -> point1.get().getX() == x - 1)
                .filter(point1 -> point1.get().getY() == y)
                .filter(point1 -> point1.get().isState())
                .count();
        long above3 = templist.stream()
                .filter(point1 -> point1.get().getX() == x - 1)
                .filter(point1 -> point1.get().getY() == y + 1)
                .filter(point1 -> point1.get().isState())
                .count();
        long aSide1 = templist.stream()
                .filter(point1 -> point1.get().getX() == x)
                .filter(point1 -> point1.get().getY() == y - 1)
                .filter(point1 -> point1.get().isState())
                .count();
        long aSide2 = templist.stream()
                .filter(point1 -> point1.get().getX() == x)
                .filter(point1 -> point1.get().getY() == y + 1)
                .filter(point1 -> point1.get().isState())
                .count();
        long under1 = templist.stream()
                .filter(point1 -> point1.get().getX() == x + 1)
                .filter(point1 -> point1.get().getY() == y - 1)
                .filter(point1 -> point1.get().isState())
                .count();
        long under2 = templist.stream()
                .filter(point1 -> point1.get().getX() == x + 1)
                .filter(point1 -> point1.get().getY() == y)
                .filter(point1 -> point1.get().isState())
                .count();
        long under3 = templist.stream()
                .filter(point1 -> point1.get().getX() == x + 1)
                .filter(point1 -> point1.get().getY() == y + 1)
                .filter(point1 -> point1.get().isState())
                .count();
        count = above1 + above2 + above3 + aSide1 + aSide2 + under1 + under2 + under3;
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
        List<Optional<Point>> newboardList = new ArrayList<Optional<Point>>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newboardList.add(Optional.of(new Point(i, j, true)));
            }
        }
        newboardList.stream()
                .map(Optional::get)
                .forEach(point -> {
                    if (boardList.stream()
                            .filter(p -> p.get().getX() == point.getX())
                            .filter(p -> p.get().getY() == point.getY())
                            .filter(p -> p.get().isState())
                            .count() == 1) {

                        int aliveNeighbours = (int) countAliveNeighbours(point, boardList);
                        point.setState(aliveNeighbours == 2);
                    } else {
                        int aliveNeighbours = (int) countAliveNeighbours(point, boardList);
                        point.setState(aliveNeighbours == 3);
                    }
                });

        this.boardList = newboardList;
    }

    public static void main(String[] args) {
        GOL simulation = new GOL(7, 8);

        simulation.setAlive(2, 4);
        simulation.setAlive(3, 4);
        simulation.setAlive(4, 4);

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
        return this.y;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
