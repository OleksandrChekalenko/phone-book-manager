package com.chelex.phonebook.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.chelex.phonebook.service.S3BucketStorageService;
import com.chelex.phonebook.util.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("/api/v1")
@RequiredArgsConstructor
public class S3BucketStorageController {

    private final S3BucketStorageService s3Service;

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
                                             @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(s3Service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/all-files-names")
    public ResponseEntity<List<String>> getFilesNames() {
        return new ResponseEntity<>(s3Service.getListFilesNames(), HttpStatus.OK);
    }

    @GetMapping("/all-files")
    public ResponseEntity<List<S3ObjectSummary>> getFiles() {
        return new ResponseEntity<>(s3Service.getFiles(), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        return ResponseEntity.ok()
                .contentType(S3Utils.contentType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(s3Service.downloadFile(fileName).toByteArray());
    }

    @GetMapping(value = "/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable() String fileName) {
        return new ResponseEntity<>(s3Service.deleteFile(fileName), HttpStatus.OK);
    }
}
