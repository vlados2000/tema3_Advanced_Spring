package com.example.tema_3_advanced.services;

import com.example.tema_3_advanced.models.OperationDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;


// Service care are metode Async, pornim async cu @EnableAsync
@Service
@EnableAsync
public class AsyncService {

    private Float doSum(Float nr1, Float nr2) throws InterruptedException {
        Thread.sleep(500);
        return nr1 + nr2;
    }

    private Float doSub(Float nr1, Float nr2) throws InterruptedException {
        Thread.sleep(500);
        return nr1 - nr2;
    }

    private Float doMul(Float nr1, Float nr2) throws InterruptedException {
        Thread.sleep(500);
        return nr1 * nr2;
    }

    private Float doDiv(Float nr1, Float nr2) throws InterruptedException {
        Thread.sleep(500);
        return nr1 / nr2;
    }

    // Metoda asincrona
    @Async
    public void calculateAsync(String fileName, Queue<OperationDto> operations) {
        FileWriter file;

        try {
            file = new FileWriter(fileName);
            BufferedWriter fileWriter = new BufferedWriter(file);

            while (!operations.isEmpty()) {
                OperationDto obj = operations.remove();
                switch (obj.getOperation()) {
                    case "sum" -> fileWriter.write(doSum(obj.getNr1(), obj.getNr2()) + "\n");
                    case "div" -> fileWriter.write(doDiv(obj.getNr1(), obj.getNr2()) + "\n");
                    case "diff" -> fileWriter.write(doSub(obj.getNr1(), obj.getNr2()) + "\n");
                    case "mult" -> fileWriter.write(doMul(obj.getNr1(), obj.getNr2()) + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException | InterruptedException except) {
            except.printStackTrace();
        }
    }
}
