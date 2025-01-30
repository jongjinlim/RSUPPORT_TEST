package com.example.rsupporttest.services.attach.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

@Service
public class AttachService {

	private final S3Client s3Client;

	@Value("${spring.aws.s3.bucket}")
	private String bucketName;

	public AttachService(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	public String uploadFileToS3(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		s3Client.putObject(
				PutObjectRequest.builder()
						.bucket(bucketName)
						.key(fileName)
						.build(),
				Path.of(file.getOriginalFilename())
		);
		return "http://localhost:4566/" + bucketName + "/" + fileName;
	}

	public void deleteFileFromS3(String fileUrl) {
		String fileKey = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3Client.deleteObject(
				DeleteObjectRequest.builder()
						.bucket(bucketName)
						.key(fileKey)
						.build()
		);
	}
}

