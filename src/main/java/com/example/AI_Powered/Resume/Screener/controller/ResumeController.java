package com.example.AI_Powered.Resume.Screener.controller;

import com.example.AI_Powered.Resume.Screener.service.PdfParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/resume")
public class ResumeController {

    private final PdfParserService pdfParserService;

    public ResumeController(PdfParserService pdfParserService) {
        this.pdfParserService = pdfParserService;
    }
    @PostMapping("/parse")
    public ResponseEntity<?> parseResume(@RequestPart("resume") MultipartFile resumePdf){
        if(resumePdf.isEmpty()){
            return ResponseEntity.badRequest().body("resume file is required");
        }
        try{
            String extractedText = pdfParserService.extractText(resumePdf);
            return ResponseEntity.ok(extractedText);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Failed to parse resume" + e.getMessage());
        }
    }
}
