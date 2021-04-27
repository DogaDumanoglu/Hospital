import java.io.*;

public class OperationLogger {
    File file = new File("output.txt");

    public void writeLine(String str) throws IOException {
        /*This function writes all operations and their results (add patient,vs..)*/
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(str);
        bWriter.close();
    }
}
