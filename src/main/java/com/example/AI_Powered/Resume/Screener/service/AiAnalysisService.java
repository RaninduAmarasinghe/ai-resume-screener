package com.example.AI_Powered.Resume.Screener.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiAnalysisService {

    private final ChatClient chatClient;
    public AiAnalysisService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyze (String resumeText, String jobDescription){
        String prompt = """
                 You are an AI resume screening assistant.
                
                                Compare the following resume with the job description.
                
                                Resume:
                                %s
                
                                Job Description:
                                %s
                
                                Respond ONLY in JSON with:
                                {
                                  "matchedSkills": [],
                                  "missingSkills": [],
                                  "suggestions": []
                                }
                                """.formatted(resumeText,jobDescription);
return chatClient.prompt()
        .user(prompt)
        .call()
        .content();
    }
    
}
