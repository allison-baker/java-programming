import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
public class CopyFile1 {
    public static void main(String[] args) {
        // set file path & name (Java uses forward slash)
        String sourceFile = "whatIsJava.txt";
        String targetFile = "target.txt";

        // try with resources -
        // anything in try block will have auto close
        try (
                FileReader fReader = new FileReader(sourceFile); //open for read
                BufferedReader bReader = new BufferedReader(fReader);
                FileWriter writer = new FileWriter(targetFile); // open for write

        ) {
            while (true) {
                String line = bReader.readLine(); // read line, null if error
                if (line == null) {
                    break;
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}