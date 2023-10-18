package org.farmu.controller;

import org.farmu.model.Image;
import org.farmu.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageResizerController {

    private static final String OUTPUT_PATH = "outputImage.png";
    @Autowired
    private ImageRepository repository;

    @PostMapping("/image/{name}/resize/{size}")
    public Image getUrl(@RequestBody String imagePath, @PathVariable String name, @PathVariable int size) {
        final String outputPath = resizeImage(imagePath, name, size);

        return repository.save(Image.builder()
                .inputImg(imagePath + "/" + name)
                .outputPath(outputPath)
                .newHeight(size)
                .newWidth(size).build());
    }

    private String resizeImage(final String imagePath, final String name, final int size) {
        String outputImagePath = null;
        try {
            final BufferedImage originalImage = ImageIO.read(new File(imagePath + "/" + name));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            final BufferedImage resizedImage = new BufferedImage(size, size, type);
            resizedImage.getGraphics().drawImage(originalImage, 0, 0, size, size, null);

            outputImagePath = imagePath + "/output.png";

            ImageIO.write(resizedImage, "png", new File(outputImagePath));

            System.out.println("Image resized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputImagePath;
    }
}