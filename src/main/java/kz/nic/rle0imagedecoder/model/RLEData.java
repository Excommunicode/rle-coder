package kz.nic.rle0imagedecoder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RLEData {
    private List<int[]> encodedData;
    private int width;
    private int height;
}
