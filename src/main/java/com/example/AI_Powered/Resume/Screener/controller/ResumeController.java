package com.example.AI_Powered.Resume.Screener.controller;

import com.example.AI_Powered.Resume.Screener.dto.ResumeAnalysisResponse;
import com.example.AI_Powered.Resume.Screener.service.AiAnalysisService;
import com.example.AI_Powered.Resume.Screener.service.PdfParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/resume")
public class ResumeController {

    private final PdfParserService pdfParserService;
    private final AiAnalysisService aiAnalysisService;

    public ResumeController(PdfParserService pdfParserService, AiAnalysisService aiAnalysisService) {

        this.pdfParserService = pdfParserService;
        this.aiAnalysisService = aiAnalysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(@RequestPart("resume") MultipartFile resumePdf,
                                           @RequestParam("jobDescription")String jobDescription )
    {
        if(resumePdf.isEmpty()){
            return ResponseEntity.badRequest().body("resume file is required");
        }
        if(jobDescription == null || jobDescription.isBlank()){
            return ResponseEntity.badRequest().body("job description is required");
        }
        try {
            String resumeText = pdfParserService.extractText(resumePdf);
            String aiResponse = aiAnalysisService.analyze(resumeText, jobDescription);
            return  ResponseEntity.ok( aiResponse);

                /*   new ResumeAnalysisResponse(
                            resumeText.length(),
                            jobDescription.length(),
                            "Resume and Job Description received successfully"
                    )
            ); */

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Failed to analyze resume" + e.getMessage());
        }
    }
}
