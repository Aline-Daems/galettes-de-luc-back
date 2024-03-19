package be.technobel.pl.controllers;

import be.technobel.bl.MaterialService;
import be.technobel.dal.models.entities.Material;
import be.technobel.pl.dtos.MaterialDTO;
import be.technobel.pl.dtos.ProviderDTO;
import be.technobel.pl.forms.MaterialForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/create")
    public void create(@RequestBody MaterialForm materialForm){
        materialService.create(materialForm);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaterialDTO>> getAll(){

        List<Material> materials = materialService.getAll();

        List<MaterialDTO>dtos = materials.stream().map(MaterialDTO::fromEntity).toList();

        return ResponseEntity.ok(dtos);

    }


    @PutMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Long id){

        materialService.delete(id);
    }

    @GetMapping("/{id}")

    public ResponseEntity<MaterialDTO> getOne(@PathVariable Long id){

        return ResponseEntity.ok(MaterialDTO.fromEntity(materialService.getOne(id)));
    }

    @PutMapping("/update/{id}")
    public void update (@PathVariable Long id, @RequestBody MaterialForm materialForm){

        materialService.update(materialForm, id);
    }
}

