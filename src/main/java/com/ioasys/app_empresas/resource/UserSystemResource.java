package com.ioasys.app_empresas.resource;

import com.ioasys.app_empresas.UserSystemRepository;
import com.ioasys.app_empresas.model.UserSystem;
import com.ioasys.app_empresas.service.UserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserSystemResource {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Autowired
    private UserSystemService userSystemService;
    private UserSystem userSystemSave;



    @GetMapping("/{id}")
    public ResponseEntity<UserSystem> findByCode(@PathVariable Long id) {
        Optional<UserSystem> optional = userSystemRepository.findById(id);
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserSystem> newUserSystem(@Valid @RequestBody UserSystem userSystem, HttpServletResponse response) {
        UserSystem userSystemSave = userSystemService.save(userSystem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(userSystemSave.getId())
                .toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(userSystemSave);
    }

    @DeleteMapping("/{id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userSystemRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSystem> update(@PathVariable Long id, @Valid @RequestBody UserSystem userSystem) {
        UserSystem userSystemSave = userSystemService.update(id, userSystem);
        return ResponseEntity.ok(userSystemSave);
    }


}
