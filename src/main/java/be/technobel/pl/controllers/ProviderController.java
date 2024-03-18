package be.technobel.pl.controllers;

import be.technobel.bl.ProviderService;
import be.technobel.dal.models.entities.Provider;
import be.technobel.pl.dtos.ProviderDTO;
import be.technobel.pl.forms.ProviderForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }


    @PostMapping("/create")
    public void create(@RequestBody ProviderForm providerForm){

        providerService.create(providerForm);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProviderDTO>> getAll(){

        List<Provider> providers = providerService.getAll();
        List<ProviderDTO> dtos = providers.stream().map(ProviderDTO::fromEntity).toList();

        return   ResponseEntity.ok(dtos);
    }

    @PutMapping("/delete/{id}")
    public void delete(@PathVariable Long id){

        providerService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ProviderForm providerForm){

        providerService.update(providerForm, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getOne(@PathVariable Long id){

        return ResponseEntity.ok(ProviderDTO.fromEntity(providerService.getOne(id)));


    }
}
