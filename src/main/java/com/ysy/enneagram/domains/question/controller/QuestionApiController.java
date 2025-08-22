package com.ysy.enneagram.domains.question.controller;

import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;
import com.ysy.enneagram.domains.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionApiController {

    private final QuestionService questionService;

    @CrossOrigin(origins = "*")
    @GetMapping("/questions")
    public List<QuestionResponseDto> getAllQuestions() {
        List<QuestionResponseDto> questions = questionService.getAllQuestions();
        return questions;
    }
}
