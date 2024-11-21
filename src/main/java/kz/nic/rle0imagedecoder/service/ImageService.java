package kz.nic.rle0imagedecoder.service;

import kz.nic.rle0imagedecoder.model.ImageDTO;
import kz.nic.rle0imagedecoder.model.RLEData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageReader imageReader;

    @SneakyThrows
    public ImageDTO processImage(MultipartFile file) {

        int[][][] rgbArray = imageReader.processImage(file);


        RLEEncoder encoder = new RLEEncoder();
        List<int[]> encodedData = encoder.encode(rgbArray);


        String encodedFilePath = "encoded.rle";
        int width = rgbArray[0].length;
        int height = rgbArray.length;
        RLEFileWriter fileWriter = new RLEFileWriter();
        fileWriter.writeToFile(encodedData, encodedFilePath, width, height);


        EntropyCalculator entropyCalculator = new EntropyCalculator();
        double originalEntropy = entropyCalculator.calculateEntropy(rgbArray);
        int totalPixels = width * height;
        double encodedEntropy = entropyCalculator.calculateEncodedEntropy(encodedData, totalPixels);


        CompressionCalculator compressionCalculator = new CompressionCalculator();
        int originalSize = totalPixels * 24;
        int compressedSize = encodedData.size() * (24 + 32);
        double compressionRatio = compressionCalculator.calculateCompressionRatio(originalSize, compressedSize);
        double redundancy = compressionCalculator.calculateRedundancy(24, encodedEntropy); // Максимальная энтропия 24 бита


        RLEFileReader fileReader = new RLEFileReader();
        RLEData rleData = fileReader.readFromFile(encodedFilePath);
        RLEDecoder decoder = new RLEDecoder();
        BufferedImage decodedImage = decoder.decode(rleData.getEncodedData(), rleData.getWidth(), rleData.getHeight());


        ImageIO.write(decodedImage, "jpg", new File("decoded.jpg"));


        System.out.println("Оригинальная энтропия: " + originalEntropy);
        System.out.println("Энтропия закодированного сообщения: " + encodedEntropy);
        System.out.println("Коэффициент сжатия: " + compressionRatio);
        System.out.println("Коэффициент избыточности: " + redundancy);

        return ImageDTO.builder()
                .originalEntropy(originalEntropy)
                .encodedEntropy(encodedEntropy)
                .compressionRatio(compressionRatio)
                .redundancy(redundancy)
                .build();
    }


}


