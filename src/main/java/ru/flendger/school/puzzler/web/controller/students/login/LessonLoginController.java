package ru.flendger.school.puzzler.web.controller.students.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flendger.school.puzzler.students.model.service.lessonlogin.dto.LessonLoginRequest;

@RestController
@RequestMapping("/api/v1/students/login")
@RequiredArgsConstructor
public class LessonLoginController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LessonLoginRequest loginRequest) {
        return ResponseEntity.ok(loginRequest);
    }
}