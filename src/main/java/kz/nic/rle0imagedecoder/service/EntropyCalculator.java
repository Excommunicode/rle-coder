package kz.nic.rle0imagedecoder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntropyCalculator {
    public double calculateEntropy(int[][][] rgbArray) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int totalPixels = rgbArray.length * rgbArray[0].length;

        for (int[][] row : rgbArray) {
            for (int[] pixel : row) {
                int rgb = (pixel[0] << 16) | (pixel[1] << 8) | pixel[2];
                frequencyMap.put(rgb, frequencyMap.getOrDefault(rgb, 0) + 1);
            }
        }

        double entropy = 0.0;
        for (int freq : frequencyMap.values()) {
            double probability = (double) freq / totalPixels;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }

    public double calculateEncodedEntropy(List<int[]> encodedData, int totalSymbols) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int[] data : encodedData) {
            int rgb = (data[0] << 16) | (data[1] << 8) | data[2];
            int count = data[3];
            frequencyMap.put(rgb, frequencyMap.getOrDefault(rgb, 0) + count);
        }

        double entropy = 0.0;
        for (int freq : frequencyMap.values()) {
            double probability = (double) freq / totalSymbols;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }
}
