package kz.nic.rle0imagedecoder.controller;

import kz.nic.rle0imagedecoder.model.ImageDTO;
import kz.nic.rle0imagedecoder.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/image/upload")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ImageDTO uploadImage(@RequestParam("file") MultipartFile file) {
        return imageService.processImage(file);
    }
}
