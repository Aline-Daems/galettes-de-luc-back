package be.technobel.pl.controllers;

import be.technobel.bl.ReceiptService;
import be.technobel.pl.forms.ReceiptForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reception")
public class ReceiptController {
    private ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/create")
    public void create(@RequestBody ReceiptForm form){
        receiptService.create(form);
    }

}
