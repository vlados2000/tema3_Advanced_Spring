package com.example.tema_3_advanced.controllers;

import com.example.tema_3_advanced.models.OperationDto;
import com.example.tema_3_advanced.services.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MathController {
    private final MathService mathService;

    @PostMapping("/do-math")
    public String getInput(@RequestBody ArrayList<OperationDto> listOfObjects) {
        return mathService.parseInput(listOfObjects);
    }

    @GetMapping("/check-finished/{fileName}")
    public ResponseEntity<Boolean> checkFinished(@PathVariable String fileName) {
        return mathService.checkFinished(fileName);
    }

    @GetMapping("/results/{fileName}")
    public ResponseEntity<String> getResults(@PathVariable String fileName) {
        return mathService.getResults(fileName);
    }
}
