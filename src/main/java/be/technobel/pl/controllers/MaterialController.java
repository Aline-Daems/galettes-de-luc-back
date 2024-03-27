package be.technobel.pl.controllers;

import be.technobel.bl.MaterialService;
import be.technobel.dal.models.entities.Material;
import be.technobel.pl.dtos.MaterialDTO;
import be.technobel.pl.dtos.ProviderDTO;
import be.technobel.pl.forms.MaterialForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/create")
    public void create(@RequestBody MaterialForm materialForm){
        materialService.create(materialForm);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<MaterialDTO>> getAll(){

        List<Material> materials = materialService.getAll();

        List<MaterialDTO>dtos = materials.stream().map(MaterialDTO::fromEntity).toList();

        return ResponseEntity.ok(dtos);

    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Long id){

        materialService.delete(id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")

    public ResponseEntity<MaterialDTO> getOne(@PathVariable Long id){

        return ResponseEntity.ok(MaterialDTO.fromEntity(materialService.getOne(id)));
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update/{id}")
    public void update (@PathVariable Long id, @RequestBody MaterialForm materialForm){

        materialService.update(materialForm, id);
    }
}


