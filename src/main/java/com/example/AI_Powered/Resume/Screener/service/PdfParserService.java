package com.example.AI_Powered.Resume.Screener.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfParserService {

    private final Tika tika = new Tika();

    public String extractText(MultipartFile file){
        try (InputStream inputStream = file.getInputStream()) {
            return tika.parseToString(inputStream);
        } catch (IOException | TikaException e) {
            throw new RuntimeException(e);
        }
    }
}
