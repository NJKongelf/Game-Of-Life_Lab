import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class GOL {
    private List<Optional<Point>> boardList = new ArrayList<Optional<Point>>();

    public GOL(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardList.add(Optional.of(new Point(i, j, false)));
            }
        }

    }

    public List<Optional<Point>> getBoardList() {
        return boardList;
    }

    private String accept(Point point) {
        if (!point.isState())
            return ".";
        return "*";
    }

    public void printBoard() {
        System.out.println("-------");
        OptionalInt opX = boardList.stream().mapToInt(p -> p.get().getX()).max();
        for (int i = 0; i < opX.getAsInt() + 1; i++) {
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
        long count = 0;
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x - 1
                        && point1.getY() == y - 1
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x - 1
                        && point1.getY() == y
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x - 1
                        && point1.getY() == y + 1
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x
                        && point1.getY() == y - 1
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x
                        && point1.getY() == y + 1
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x + 1
                        && point1.getY() == y - 1
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x + 1
                        && point1.getY() == y
                        && point1.isState())
                .count();
        count += templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == x + 1
                        && point1.getY() == y + 1
                        && point1.isState())
                .count();
        return count;
    }


    public void step() {
        List<Optional<Point>> newboardList = new ArrayList<Optional<Point>>();
        OptionalInt opX = boardList.stream().mapToInt(p -> p.get().getX()).max();
        OptionalInt opY = boardList.stream().mapToInt(p -> p.get().getY()).max();
        for (int i = 0; i < opX.getAsInt() + 1; i++) {
            for (int j = 0; j < opY.getAsInt() + 1; j++) {
                newboardList.add(Optional.of(new Point(i, j, true)));
            }
        }
        newboardList.stream()
                .map(Optional::get)
                .forEach(point -> {
                    int aliveNeighbours = (int) countAliveNeighbours(point, boardList);
                    if (boardList.stream()
                            .filter(p -> p.get().getX() == point.getX())
                            .filter(p -> p.get().getY() == point.getY())
                            .filter(p -> p.get().isState())
                            .count() == 1) {
                        point.setState(aliveNeighbours == 2);
                    } else {
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

        simulation.setAlive(2, 4);
        simulation.setAlive(3, 4);
        simulation.setAlive(4, 4);

    }
}

