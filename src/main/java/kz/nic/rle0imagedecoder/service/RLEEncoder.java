package kz.nic.rle0imagedecoder.service;

import java.util.ArrayList;
import java.util.List;

public class RLEEncoder {
    public List<int[]> encode(int[][][] rgbArray) {
        List<int[]> encodedData = new ArrayList<>();
        int width = rgbArray[0].length;


        List<int[]> pixelList = new ArrayList<>();
        for (int[][] ints : rgbArray) {
            for (int x = 0; x < width; x++) {
                pixelList.add(ints[x]);
            }
        }

        int[] previousPixel = pixelList.getFirst();
        int count = 1;

        for (int i = 1; i < pixelList.size(); i++) {
            int[] currentPixel = pixelList.get(i);
            if (isSamePixel(previousPixel, currentPixel)) {
                count++;
            } else {
                encodedData.add(new int[]{previousPixel[0], previousPixel[1], previousPixel[2], count});
                previousPixel = currentPixel;
                count = 1;
            }
        }

        encodedData.add(new int[]{previousPixel[0], previousPixel[1], previousPixel[2], count});
        return encodedData;
    }

    private boolean isSamePixel(int[] pixel1, int[] pixel2) {
        return pixel1[0] == pixel2[0] && pixel1[1] == pixel2[1] && pixel1[2] == pixel2[2];
    }
}