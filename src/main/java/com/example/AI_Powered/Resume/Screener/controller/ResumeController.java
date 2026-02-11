package com.example.AI_Powered.Resume.Screener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/resume")
public class ResumeController {

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
