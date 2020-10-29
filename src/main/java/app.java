import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class app {
    public static void main(String[] args) {
        StringBuilder line = new StringBuilder();
        GOL gameoflife = new GOL(30, 30);
        gameoflife.setAlive(8, 8);
        gameoflife.setAlive(9, 9);
        gameoflife.setAlive(10, 10);

        gameoflife.setAlive(11, 9);
        gameoflife.setAlive(12, 8);
        for (int i = 0; i < 100; i++) {

            System.out.println(gameoflife.printBoard());
            line.append("Generation "+i+"\n");
            line.append(gameoflife.printBoard());
            line.append("\n");
            gameoflife.step();
        }


        try {
            FileWriter out = new FileWriter(new File("output.txt"));
            out.write(String.valueOf(line));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
