package com.agrichallenge.agdata.controller;

import com.agrichallenge.agdata.model.AgData;
import com.agrichallenge.agdata.service.AgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agdata")
public class AgDataController {

    @Autowired
    private AgDataService agDataService;

    @GetMapping("")
    public ResponseEntity<List<AgData>> getAllProducts() throws IOException {
        List<AgData> products = agDataService.loadAgData();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // TODO: GET /api/agdata/crop-count?cropName=corn
@GetMapping("/crop-count")
public ResponseEntity<Long> getCropCount(@RequestParam String cropName) {
    Long avg = agDataService.getCropCount(cropName);
    return new ResponseEntity<>(avg, HttpStatus.OK);
}


    // TODO: GET /api/agdata/average-yield?cropName=wheat
@GetMapping("/average-yield")
public ResponseEntity<Double> getAverageYield(@RequestParam String cropName) {
    double avg = agDataService.getAverageYield(cropName);
    return new ResponseEntity<>(avg, HttpStatus.OK);
}


    // TODO: GET /api/agdata/by-region?region=Midwest
 @GetMapping("/by-region")
public ResponseEntity<List<AgData>> getRecordsByRegion(@RequestParam String region) {
    List<AgData> records = agDataService.getRecordsByRegion(region);

    // If no records are found, return 204 No Content
    if (records.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

    // Return 200 OK with the list of records
    return new ResponseEntity<>(records, HttpStatus.OK); // 200 OK
}




}
