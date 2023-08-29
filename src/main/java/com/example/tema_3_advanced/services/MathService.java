package com.example.tema_3_advanced.services;

import com.example.tema_3_advanced.models.OperationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MathService {
    // Key - numele fisier, Value - coada de operatii, rezultatele carora se vor scrie in fisierul Key
    private final Map<String, Queue<OperationDto>> fileOperationsMap = new HashMap<>();

    private final AsyncService asyncService;

    // Numarul de fisiere create pana acum
    private Integer nrOfFiles = 0;

    public String parseInput(ArrayList<OperationDto> listOfObjects) {
        String fileName = "input" + nrOfFiles + ".txt";
        Queue<OperationDto> operations = new LinkedList<>(listOfObjects);

        fileOperationsMap.put(fileName, operations);

        asyncService.calculateAsync(fileName, operations);
        nrOfFiles++;
        return fileName;
    }

    public ResponseEntity<Boolean> checkFinished(String fileName) {
        Queue<OperationDto> queue = fileOperationsMap.get(fileName);
        // Daca nu exista asa fisier - intorc 404 si null
        if (queue == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(queue.isEmpty());
    }

    public ResponseEntity<String> getResults(String fileName) {
        Queue<OperationDto> queue = fileOperationsMap.get(fileName);
        // Daca nu exista asa fisier - intorc 404 si null
        if (queue == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!queue.isEmpty()) {
            // inca nu e gata
            return ResponseEntity.accepted().body(null);
        }
        return ResponseEntity.ok().body(readFromFile(fileName));
    }

    private String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String strCurrentLine;
            while ((strCurrentLine = bufferedReader.readLine()) != null) {
                sb.append(strCurrentLine).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
