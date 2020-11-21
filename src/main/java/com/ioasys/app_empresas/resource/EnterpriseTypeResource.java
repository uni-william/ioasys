package com.ioasys.app_empresas.resource;

import com.ioasys.app_empresas.model.EnterpriseType;
import com.ioasys.app_empresas.repository.EnterpriseTypeRepository;
import com.ioasys.app_empresas.service.EnterpriseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterpriseTypes")
public class EnterpriseTypeResource {

    @Autowired
    private EnterpriseTypeRepository enterpriseTypeRepository;

    @Autowired
    private EnterpriseTypeService enterpriseTypeService;
    private EnterpriseType enterpriseTypeSave;


    @GetMapping("/{code}")
    public ResponseEntity<EnterpriseType> findByCode(@PathVariable Long code) {
        Optional<EnterpriseType> optional = enterpriseTypeRepository.findById(code);
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnterpriseType> newEnterpriseType(@Valid @RequestBody EnterpriseType enterpriseType, HttpServletResponse response) {
        EnterpriseType enterpriseTypeSave = enterpriseTypeService.save(enterpriseType);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(enterpriseTypeSave.getCode())
                .toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(enterpriseTypeSave);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long code) {
        enterpriseTypeRepository.deleteById(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<EnterpriseType> update(@PathVariable Long code, @Valid @RequestBody EnterpriseType enterpriseType) {
        EnterpriseType
                enterpriseTypeSave = enterpriseTypeService.update(code, enterpriseType);
        return ResponseEntity.ok(enterpriseTypeSave);
    }

    @GetMapping
    public List<EnterpriseType> findAll() {
        return enterpriseTypeRepository.findAll();
    }

}
