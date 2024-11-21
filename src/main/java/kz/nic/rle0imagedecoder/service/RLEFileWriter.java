package kz.nic.rle0imagedecoder.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RLEFileWriter {
    public void writeToFile(List<int[]> encodedData, String filePath, int width, int height) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(width + " " + height);
        writer.newLine();

        for (int[] data : encodedData) {
            writer.write(data[0] + " " + data[1] + " " + data[2] + " " + data[3]);
            writer.newLine();
        }
        writer.close();
    }
}
