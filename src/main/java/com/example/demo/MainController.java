package com.example.demo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class MainController {

    @PostMapping(headers ={"content-type=multipart/form-data"})
    public String parseImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        BufferedImage bImageFromConvert = ImageIO.read(file.getInputStream());
        return getImgText(bImageFromConvert);
    }

    public String getImgText(BufferedImage file) {
        ITesseract instance = new Tesseract();
        try
        {
            instance.setDatapath("src/main/resources");
            instance.setLanguage("rus");
            instance.setPageSegMode(1);
            instance.setOcrEngineMode(1);
            String imgText = instance.doOCR(file);
            return imgText;
        }
        catch (TesseractException e)
        {
            e.getMessage();
            return "Error while reading image";
        }
    }


}
