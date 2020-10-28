import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class GOL {
    private List<Optional<Point>> boardList = new ArrayList<Optional<Point>>();
    private OptionalInt maxX;
    private OptionalInt maxY;

    public GOL(int width, int height) {
        this.maxX = OptionalInt.of(width);
        this.maxY = OptionalInt.of(height);
    }

    public List<Optional<Point>> getBoardList() {
        return boardList;
    }

    public String printBoard() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < maxX.getAsInt(); i++) {
            for (int j = 0; j < maxY.getAsInt(); j++) {
                if (checkIfPointExistsInList(i, j))
                    line.append(PointState.ALIVE);
                else
                    line.append(PointState.DEAD);
            }
            line.append("\n");
        }
        return line.toString();
    }

    public void setAlive(int x, int y) {
        if (x > maxX.getAsInt() || y > maxY.getAsInt() || x < 0 || y < 0)
            throw new IllegalArgumentException();
        if (!checkIfPointExistsInList(x, y))
            boardList.add(Optional.of(new Point(x, y)));
    }

    public boolean checkIfPointExistsInList(int x, int y) {
        return boardList.stream()
                .map(Optional::get)
                .filter(point -> point.getX() == x)
                .filter(point -> point.getY() == y)
                .count() == 1;
    }

    public void setDead(int x, int y) {
        boardList.remove(boardList.stream()
                .filter(point -> point.get().getX() == x)
                .filter(point -> point.get().getY() == y)
                .map(Optional::get)
                .findFirst());
    }

    public long countAliveNeighbours(Point point, List<Optional<Point>> templist) {
        //public int countAliveNeighbours(int x, int y) {
        int x = point.getX();
        int y = point.getY();
        long count = 0;
        Point aboveleft = new Point(x - 1, y - 1);
        Point abovestraight = new Point(x - 1, y);
        Point abovesright = new Point(x - 1, y + 1);
        Point left = new Point(x, y - 1);
        Point right = new Point(x, y + 1);
        Point underleft = new Point(x + 1, y - 1);
        Point understraight = new Point(x + 1, y);
        Point undersright = new Point(x + 1, y + 1);

        Point[] positionArry = {aboveleft, abovestraight, abovesright, left, right, underleft, understraight, undersright};

        for (int i = 0; i < positionArry.length; i++) {
            count += neighbourCheck(templist, positionArry[i]);
        }

        return count;
    }

    private long neighbourCheck(List<Optional<Point>> templist, Point tempPoint) {
        return templist.stream()
                .map(Optional::get)
                .filter(point1 -> point1.getX() == tempPoint.getX()
                        && point1.getY() == tempPoint.getY())
                .count();
    }

    public void step() {
        List<Optional<Point>> newboardList = new ArrayList<Optional<Point>>();
        for (int i = 0; i < maxX.getAsInt(); i++) {
            for (int j = 0; j < maxY.getAsInt(); j++) {
                Point point = new Point(i, j);
                int aliveNeighboursOnPoint = (int) countAliveNeighbours(point, boardList);
                if (checkIfPointExistsInList(point.getX(), point.getY()) && (aliveNeighboursOnPoint == 2 || aliveNeighboursOnPoint == 3))
                    newboardList.add(Optional.of(point));
                else if (aliveNeighboursOnPoint == 3)
                    newboardList.add(Optional.of(point));
            }
        }

        this.boardList = newboardList;
    }

}

