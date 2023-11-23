package com.example.apidemo.controller;

import com.example.apidemo.models.ResponseObject;
import com.example.apidemo.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //This controller receive file/image from client
    @Autowired
    private IStorageService storageService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            //save files to a folder => use a service
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("ok","upload file successfilly",generatedFileName)
            );
            //b8bc5be5f5e54817a586bfccdd76393e.jpg => how to open this file in Web Browser?

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok",exception.getMessage(),"")
            );
        }
    }
    //get image's url
    @GetMapping("/files/{fileName:.+}")
    public  ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){

    }
}
