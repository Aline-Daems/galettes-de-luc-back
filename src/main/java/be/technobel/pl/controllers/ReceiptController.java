package be.technobel.pl.controllers;

import be.technobel.bl.ReceiptService;
import be.technobel.dal.models.entities.Receipt;

import be.technobel.dal.repositories.ReceiptRepository;
import be.technobel.pl.dtos.ReceiptDTO;
import be.technobel.pl.dtos.ReceiptDTOMinus;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private ReceiptService receiptService;

    private ReceiptRepository receiptRepository;


    public ReceiptController(ReceiptService receiptService, ReceiptRepository receiptRepository) {
        this.receiptService = receiptService;
        this.receiptRepository = receiptRepository;
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ReceiptForm form) throws MessagingException {
        Long id = receiptService.create(form);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/file/{id}")
    public ResponseEntity<String> fileUpdate(@PathVariable Long id, @RequestPart("file") MultipartFile file){

      try {

          Receipt receipt = receiptService.getOne(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));

          receipt.setImageData(file.getBytes());

          receiptRepository.save(receipt);

          System.out.println("Téléchargement réussi");

          return ResponseEntity.ok("Téléchargement ok");


      }catch (IOException e){
          return new ResponseEntity<>("Echec", HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getOne(@PathVariable Long id){

        return  ResponseEntity.ok(ReceiptDTO.fromEntity(receiptService.getOne(id).orElseThrow(() -> new EntityNotFoundException("Id not found"))));

    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/photo/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id){

        byte[] imageData = receiptService.getImageData(id);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        httpHeaders.setContentLength(imageData.length);

        return new ResponseEntity<>(imageData, httpHeaders, HttpStatus.OK);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity <List<ReceiptDTOMinus>> getAll(){

        List<ReceiptDTOMinus> dtos = receiptService.getAll();
        return  ResponseEntity.ok(dtos);
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("delete/{id}")
    public void deleteRecipt(@PathVariable Long id){

        receiptService.delete(id);
    }



    private static byte[] conversion(String image){
        byte[] imageByte =  jakarta.xml.bind.DatatypeConverter.parseBase64Binary(image);

        return imageByte;
    }

}
