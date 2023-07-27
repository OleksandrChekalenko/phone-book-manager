package com.chelex.phonebook.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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
    public String uploadFile(MultipartFile file, String folderPath) {
        String filePath = folderPath + "/";
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName, filePath + file.getOriginalFilename(), file.getInputStream(), metadata);
            return "File uploaded: " + file.getOriginalFilename();
        } catch (IOException ioe) {
            log.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException serviceException) {
            log.info("AmazonServiceException: " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            log.info("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }
        return "File not uploaded: " + filePath + file.getOriginalFilename();
    }

    public List<String> getListFilesNames() {
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    public List<S3ObjectSummary> getFiles() {
        return amazonS3Client.listObjects(bucketName).getObjectSummaries();
    }

    /**
     * Downloads file using amazon S3 client from S3 bucket
     *
     * @param keyName file name to download
     * @return ByteArrayOutputStream
     */
    public ByteArrayOutputStream downloadFile(String keyName) {
        S3Object s3Object = amazonS3Client.getObject(bucketName, keyName);
        try {
            byte[] bytes = s3Object.getObjectContent().readAllBytes();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes, 0, bytes.length);
            return byteArrayOutputStream;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Deletes file from AWS S3 bucket
     *
     * @param fileName s3 file name to delete
     * @return string with delete filename
     */
    public String deleteFile(final String filePath, final String fileName) {
        String fullPath = filePath + "/" + fileName;

        if (amazonS3Client.doesObjectExist(bucketName, fullPath)) {
            amazonS3Client.deleteObject(bucketName, fullPath);
            if (!amazonS3Client.doesObjectExist(bucketName, fullPath)) {
                log.info("File is deleted. Full path: " + bucketName + "/" + fullPath);
                return "Deleted File: " + fileName;
            }
        } else {
            throw new EntityNotFoundException("File not found before delete it. The specified key does not exist. "
                    + bucketName + "/" + fullPath);
        }

        throw new EntityNotFoundException("File is NOT deleted for some reason. Full path: " + bucketName + "/" + fullPath);

    }
}
