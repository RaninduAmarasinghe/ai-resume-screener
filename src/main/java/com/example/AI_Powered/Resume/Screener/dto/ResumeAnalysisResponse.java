package com.example.AI_Powered.Resume.Screener.dto;

public class ResumeAnalysisResponse {

    private int resumeTextLength;
    private int jobDescriptionLength;
    private String message;

    public ResumeAnalysisResponse(int resumeTextLength, int jobDescriptionLength, String message) {
        this.resumeTextLength = resumeTextLength;
        this.jobDescriptionLength = jobDescriptionLength;
        this.message = message;
    }
    public int getResumeTextLength() {
        return resumeTextLength;
    }
    public int getJobDescriptionLength() {
        return jobDescriptionLength;
    }
    public String getMessage() {
        return message;
    }

    }

