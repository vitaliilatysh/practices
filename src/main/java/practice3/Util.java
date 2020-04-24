package practice3;

import java.io.IOException;
import java.nio.file.*;

class Util {

    private static final String ENCODING = "UTF-8";

    static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
