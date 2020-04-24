package practice4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {

    static String read(String path, String encoding) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, encoding);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    static void write(int[] arrayToWrite, String filename, String encoding) {
        File file = new File(filename);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), encoding))) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < arrayToWrite.length; i++) {
                stringBuilder.append(String.format("%01d ", arrayToWrite[i]));
            }

            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
