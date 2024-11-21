package kz.nic.rle0imagedecoder.service;

import kz.nic.rle0imagedecoder.model.RLEData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RLEFileReader {
    public RLEData readFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] dimensions = reader.readLine().split(" ");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        List<int[]> encodedData = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            int red = Integer.parseInt(tokens[0]);
            int green = Integer.parseInt(tokens[1]);
            int blue = Integer.parseInt(tokens[2]);
            int count = Integer.parseInt(tokens[3]);
            encodedData.add(new int[]{red, green, blue, count});
        }
        reader.close();
        return new RLEData(encodedData, width, height);
    }
}
