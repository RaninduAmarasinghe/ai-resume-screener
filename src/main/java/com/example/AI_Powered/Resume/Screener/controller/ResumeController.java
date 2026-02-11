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

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(@RequestPart("resume") MultipartFile resumePdf,
                                           @RequestPart("jobDescription")String jobDescription )
    {
        if(resumePdf.isEmpty()){
            return ResponseEntity.badRequest().body("resume file is required");
        }
        if(jobDescription == null || jobDescription.isBlank()){
            return ResponseEntity.badRequest().body("job description is required");
        }
        try {
            String resumeText = pdfParserService.extractText(resumePdf);

            return  ResponseEntity.ok(
                    new ResumeAnalysisResponse(
                            resumeText.length(),
                            jobDescription.length(),
                            "Resume and Job Description received successfully"
                    )
            );

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Failed to analyze resume" + e.getMessage());
        }
    }
}
