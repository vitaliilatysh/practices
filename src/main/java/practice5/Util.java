package practice5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Util {

    static List<String> readAllLines(String path) {
        List<String> lineAmount = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                FileReader fr = new FileReader(file.getAbsoluteFile());
                BufferedReader bufferedReader = new BufferedReader(fr);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lineAmount.add(line);
                }
                bufferedReader.close();
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineAmount;
    }

    synchronized static void writeToFile(String filePath) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        int currentAmountOfLines = Util.readAllLines(filePath).size();
        long fileLength = file.length();
        file.seek(fileLength);
        file.writeBytes(new String(new char[20]).replace("\0", String.valueOf(currentAmountOfLines)));
        file.writeBytes(System.getProperty("line.separator"));
        file.close();
    }

}

