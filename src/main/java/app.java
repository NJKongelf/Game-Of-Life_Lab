import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class app {
    public static void main(String[] args) {

        GOL gameoflife = new GOL(3, 3);
        gameoflife.setAlive(0, 1);
        gameoflife.setAlive(1, 1);
        gameoflife.setAlive(2, 1);
        StringBuilder line = new StringBuilder();
        System.out.println(gameoflife.printBoard());
        line.append("Generation 1\n");
        line.append(gameoflife.printBoard());
        line.append("\n");
        gameoflife.step();
        System.out.println(gameoflife.printBoard());
        line.append("Generation 2\n");
        line.append(gameoflife.printBoard());
        line.append("\n");
        gameoflife.step();
        System.out.println(gameoflife.printBoard());
        line.append("Generation 3\n");
        line.append(gameoflife.printBoard());
        line.append("\n");

        gameoflife = new GOL(3, 3);
        gameoflife.setAlive(0, 0);
        gameoflife.setAlive(0, 2);
        gameoflife.setAlive(1, 1);
        gameoflife.setAlive(2, 0);

        System.out.println(gameoflife.printBoard());
        line.append("Generation 1\n");
        line.append(gameoflife.printBoard());
        line.append("\n");
        gameoflife.step();
        System.out.println(gameoflife.printBoard());
        line.append("Generation 2\n");
        line.append(gameoflife.printBoard());
        line.append("\n");
        gameoflife.step();
        System.out.println(gameoflife.printBoard());
        line.append("Generation 3\n");
        line.append(gameoflife.printBoard());
        line.append("\n");

        try {
            FileWriter out = new FileWriter(new File("output.txt"));
            out.write(String.valueOf(line));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
