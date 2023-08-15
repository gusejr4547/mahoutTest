package com.example.mahouttest;

import lombok.RequiredArgsConstructor;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping("/myF")
    public ResponseEntity susume(
            @RequestParam("memberId") long memberId) throws IOException, TasteException {

        List<RecommendedItem> recommendedItems = myService.getItems(memberId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
