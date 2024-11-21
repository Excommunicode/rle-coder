package kz.nic.rle0imagedecoder.service;

import java.awt.image.BufferedImage;
import java.util.List;

public class RLEDecoder {
    public BufferedImage decode(List<int[]> encodedData, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x = 0, y = 0;

        for (int[] data : encodedData) {
            int red = data[0];
            int green = data[1];
            int blue = data[2];
            int count = data[3];

            for (int i = 0; i < count; i++) {
                int rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
                x++;
                if (x == width) {
                    x = 0;
                    y++;
                }
            }
        }
        return image;
    }
}
