package com.ioasys.app_empresas.resource;

import com.ioasys.app_empresas.model.Enterprise;
import com.ioasys.app_empresas.repository.EnterpriseRepository;
import com.ioasys.app_empresas.repository.filter.EnterpriseFilter;
import com.ioasys.app_empresas.service.EnterpriseService;
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
@RequestMapping("/enterprises")
public class EnterpriseResource {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseService enterpriseService;
    private Enterprise enterpriseSave;



    @GetMapping("/{code}")
    public ResponseEntity<Enterprise> findByCode(@PathVariable Long code) {
        Optional<Enterprise> optional = enterpriseRepository.findById(code);
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Enterprise> newEnterprise(@Valid @RequestBody Enterprise enterprise, HttpServletResponse response) {
        Enterprise enterpriseSave = enterpriseService.save(enterprise);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(enterpriseSave.getCode())
                .toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(enterpriseSave);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long code) {
        enterpriseRepository.deleteById(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Enterprise> update(@PathVariable Long code, @Valid @RequestBody Enterprise enterprise) {
        Enterprise
                enterpriseSave = enterpriseService.update(code, enterprise);
        return ResponseEntity.ok(enterpriseSave);
    }

    @GetMapping
    public List<Enterprise> search(EnterpriseFilter filter) {
        return enterpriseRepository.filter(filter);
    }


}
