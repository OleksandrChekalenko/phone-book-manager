package com.chelex.phonebook.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class S3Utils {

    private static final String TYPE_REGEX = "\\.";

    private static final String TXT_TYPE = "txt";
    private static final String PNG_TYPE = "png";
    private static final String JPG_TYPE = "jpg";

    public static MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split(TYPE_REGEX);
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        return switch (fileExtension) {
                case TXT_TYPE -> MediaType.TEXT_PLAIN;
                case PNG_TYPE -> MediaType.IMAGE_PNG;
                case JPG_TYPE -> MediaType.IMAGE_JPEG;
                default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}
