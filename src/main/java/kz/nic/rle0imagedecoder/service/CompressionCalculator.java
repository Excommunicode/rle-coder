package kz.nic.rle0imagedecoder.service;

public class CompressionCalculator {
    public double calculateCompressionRatio(int originalSize, int compressedSize) {
        return (double) originalSize / compressedSize;
    }

    public double calculateRedundancy(double maxEntropy, double actualEntropy) {
        return maxEntropy - actualEntropy;
    }
}