package com.example.jobsearch.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequest {
    private Long movieId;
    private MultipartFile file;
}
