package com.chelex.phonebook.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3BucketStorageService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 amazonS3Client;

    /**
     * Upload file into AWS S3
     *
     * @param file {MultipartFile} file
     * @return String
     */
    public String uploadFile(MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            return "File uploaded: " + file.getName();
        } catch (IOException ioe) {
            log.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException serviceException) {
            log.info("AmazonServiceException: " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            log.info("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }
        return "File not uploaded: " + file.getName();
    }

    public List<String> getListFilesNames() {
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public List<S3ObjectSummary> getFiles() {
        return amazonS3Client.listObjects(bucketName).getObjectSummaries();
    }
}
