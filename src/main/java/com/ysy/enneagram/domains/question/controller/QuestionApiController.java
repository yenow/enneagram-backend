package com.ysy.enneagram.domains.question.controller;

import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;
import com.ysy.enneagram.domains.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionApiController {

    private final QuestionService questionService;

    @GetMapping("/questions")
    public List<QuestionResponseDto> getAllQuestions() {
        List<QuestionResponseDto> questions = questionService.getAllQuestions();
        return questions;
    }
}
