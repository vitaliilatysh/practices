package practice6;

import java.io.IOException;
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
}
