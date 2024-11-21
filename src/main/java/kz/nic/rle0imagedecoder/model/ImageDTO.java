package kz.nic.rle0imagedecoder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImageDTO {
    private double originalEntropy;
    private double encodedEntropy;
    private double compressionRatio;
    private double redundancy;
}