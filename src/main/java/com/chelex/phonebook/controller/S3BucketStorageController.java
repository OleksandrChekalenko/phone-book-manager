package com.chelex.phonebook.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.chelex.phonebook.service.S3BucketStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class S3BucketStorageController {

    private final S3BucketStorageService service;

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
                                             @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/all-files-names")
    public ResponseEntity<List<String>> getFilesNames() {
        return new ResponseEntity<>(service.getListFilesNames(), HttpStatus.OK);
    }

    @GetMapping("/all-files")
    public ResponseEntity<List<S3ObjectSummary>> getFiles() {
        return new ResponseEntity<>(service.getFiles(), HttpStatus.OK);
    }
}
