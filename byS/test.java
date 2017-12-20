import sample.board.*;

import java.util.HashMap;
import java.util.HashSet;

public class test {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Board board = new Board(4);
        HashSet<Field> fields = board.getFields();

        System.out.println("eh");
    }
}
