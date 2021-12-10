package com.itz.base.helpers.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Service
public class AwsS3Service {

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Async
    public void uploadFile(MultipartFile multipartFile, String fileName) {
        String fileUrl = "";
        try {
            File file = convertMultiPartFileToFile(multipartFile);
//            String fileName = generateFileName(multipartFile.getOriginalFilename());
//            fileUrl= getFileUrl(fileName);

            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return fileUrl;
    }

    public String getFileUrl(String fileName) {
        return endpointUrl + "/" + bucketName + "/" + fileName;
    }

    private File convertToFile(final MultipartFile multipartFile) throws Exception {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            throw new Exception(ex.getMessage());
        }
        return file;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) throws Exception {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            throw new Exception(ex.getMessage());
        }
        return file;
    }

    public String generateFileName(String fileName) {
        return new Date().getTime() + "-" + fileName.replace(" ", "_");
    }


    private PutObjectResult uploadFileTos3bucket(String fileName, File file) {
        return amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        if (fileUrl == null)
            return "Null Value";
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }
}
