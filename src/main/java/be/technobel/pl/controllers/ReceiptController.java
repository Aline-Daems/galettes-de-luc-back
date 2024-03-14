package be.technobel.pl.controllers;

import be.technobel.bl.ReceiptService;
import be.technobel.dal.models.entities.Receipt;
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
    public ResponseEntity<Long> create(@RequestBody ReceiptForm form){
        Long id = receiptService.create(form);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/file/{id}")
    public void fileUpdate(@RequestBody String file, @PathVariable Long id){


              receiptService.dataImage(conversion(file), id);



    }

    @GetMapping("/last")
    public Receipt last(){

        return receiptService.getLast();
    }

    private static byte[] conversion(String image){
        byte[] imageByte =  jakarta.xml.bind.DatatypeConverter.parseBase64Binary(image);

        return imageByte;
    }

}
