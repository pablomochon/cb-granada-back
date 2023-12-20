package com.basketballticketsproject.basketballticketsproject.controler;

import com.basketballticketsproject.basketballticketsproject.service.FileStorageService;
import com.basketballticketsproject.basketballticketsproject.service.SorteoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cbgranada-api/v1")
@Slf4j
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SorteoService sorteoService;

    //metodo para añadir un partido, junto con su pdf de entradas
    @PostMapping("/uploadFile")
    public int uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String partido) throws IOException {
        return  fileStorageService.storeFile(file, partido);

    }


    //metodo para obtener la entrada
    @GetMapping("/getFileByName/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        byte[] imageData = fileStorageService.getFiles(fileName);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).body(imageData);
    }

    @GetMapping("/enviarEntrada/{fecha}/{userID}")
    public ResponseEntity<byte[]> enviarEntrada(@PathVariable String fecha, @PathVariable UUID userID) {
        byte[] entrada = sorteoService.enviarEntrada(fecha, userID);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).body(entrada);
    }


}