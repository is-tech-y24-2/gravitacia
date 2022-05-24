package com.kotiki.controller;

import Entities.Owner;
import com.kotiki.Exeptions.CantFindById;
import com.kotiki.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kotiki.service.OwnerService;
import com.kotiki.utils.OwnerDTOMapping;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owner")
@ComponentScan("utils")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerDTOMapping ownerDtoMapping;

    @GetMapping("/all")
    public ResponseEntity getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        List<OwnerDTO> ownersDTO = owners
                .stream()
                .map(i -> ownerDtoMapping.mapToDTO(i))
                .collect(Collectors.toList());
        return new ResponseEntity(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOwner(
            @PathVariable Long id
    ) {
        Owner owner = null;
        OwnerDTO ownerDTO = null;
        try {
            owner = ownerService.findOwnerById(id);
            ownerDTO = ownerDtoMapping.mapToDTO(owner);
        } catch (CantFindById e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ownerDTO, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity createOwner(@RequestBody OwnerDTO ownerDTO) {
        Long id = null;
        try {
            Owner owner = ownerDtoMapping.mapToEntity(ownerDTO);
            id = ownerService.saveNewOwner(owner);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/all/delete")
    public ResponseEntity deleteAllOwners() {
        ownerService.deleteAllOwners();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/addcat/{catId}")
    public ResponseEntity addCatToOwner(
            @PathVariable Long id,
            @PathVariable Long catId
    ) {
        try {
            ownerService.addCat(id, catId);
        } catch (CantFindById e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}