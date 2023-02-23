import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> newList = new ArrayList<>();
        System.out.println(new File(".").getAbsoluteFile()); // for testing
        try {
            File file = new File("./src/input.txt");
            Scanner scanner = new Scanner(file);



            while (scanner.hasNextLine()) {
                newList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // for testing
        for (var c: newList)
            System.out.println(c);

    }
}