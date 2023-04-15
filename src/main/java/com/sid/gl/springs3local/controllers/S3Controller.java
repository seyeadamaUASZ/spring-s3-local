package com.sid.gl.springs3local.controllers;

import com.sid.gl.springs3local.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="localstack/")
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @PostMapping(value = "create",consumes = {"application/json"})
    public String createBucket(@RequestParam(name="bucketName") String bucketName){
     s3Service.createBucket(bucketName);
     return "successfully created !!";
    }

    @DeleteMapping(value = "delete", consumes = {"application/json"})
    public String deleteBucket(@RequestParam(name="bucketName") String bucketName){
        s3Service.deleteBucket(bucketName);
        return "successfully created !!";
    }
}
