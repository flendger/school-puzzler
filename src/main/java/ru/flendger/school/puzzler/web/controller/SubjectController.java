package ru.flendger.school.puzzler.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flendger.school.puzzler.model.service.input.SubjectService;

@RestController
@RequestMapping("/api/v1/admin/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(subjectService.findAll());
    }
}