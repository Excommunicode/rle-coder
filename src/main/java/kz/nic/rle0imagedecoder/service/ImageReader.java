package kz.nic.rle0imagedecoder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageReader {

    public int[][][] processImage(MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();


        BufferedImage image = ImageIO.read(inputStream);
        int height = image.getHeight();
        int width = image.getWidth();


        int[][][] rgbArray = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                rgbArray[y][x][0] = (pixel >> 16) & 0xff;
                rgbArray[y][x][1] = (pixel >> 8) & 0xff;
                rgbArray[y][x][2] = pixel & 0xff;
            }
        }
        return rgbArray;
    }
}

