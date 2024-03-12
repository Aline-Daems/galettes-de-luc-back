package be.technobel.pl.controllers;

import be.technobel.bl.ReceiptService;
import be.technobel.pl.forms.ReceiptForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/create")
    public void create(@RequestBody ReceiptForm form){
        receiptService.create(form);
    }

    @PutMapping("/file/{id}")
    public ResponseEntity<String> fileUpdate(@RequestPart("file")MultipartFile file, @PathVariable Long id){

     try{
         return receiptService.dataImage(file, id);
     }catch (IOException e){
         return new ResponseEntity<>("upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
     }


    }

}
