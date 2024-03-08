package com.example.OAutoDaCompadecida.Controller;

import com.example.OAutoDaCompadecida.Model.Entity.Personagem;
import com.example.OAutoDaCompadecida.Model.DTO.PersonagemDTO;
import com.example.OAutoDaCompadecida.Service.PersonagemService;
import com.example.OAutoDaCompadecida.Validation.PersonagemValidation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private PersonagemService personagemService;

    @Autowired
    public PersonagemController(PersonagemService personagemService){
        this.personagemService = personagemService;
    }

    @GetMapping
    public ResponseEntity<List<Personagem>> getTodosPersonagens(){
        return ResponseEntity.ok(personagemService.getTodosPersonagens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getPersonagemPorId(@PathVariable UUID id){
        return personagemService.getPersonagemPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Personagem> getPersonagemPorNome(@PathVariable String nome){
        return personagemService.getPersonagemPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> PutPersonagemPorId(@PathVariable UUID id, @RequestBody @Validated(PersonagemValidation.OnNotPatch.class) PersonagemDTO personagemDTO){
        return personagemService.PutPersonagemPorId(id, personagemDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Personagem> PatchPersonagemPorId(@PathVariable UUID id, @RequestBody @Validated(PersonagemValidation.OnPatch.class) PersonagemDTO personagemDTO){
        return personagemService.PatchPersonagemPorId(id, personagemDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/nome/{nome}")
    public ResponseEntity<Personagem> PutPersonagemPorNome(@PathVariable String nome,@Validated(PersonagemValidation.OnNotPatch.class) @RequestBody PersonagemDTO personagemDTO){
        return personagemService.PutPersonagemPorNome(nome, personagemDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/nome/{nome}")
    public ResponseEntity<Personagem> PatchPersonagemPorNome(@PathVariable String nome, @RequestBody @Validated(PersonagemValidation.OnPatch.class) PersonagemDTO personagemDTO){
        return personagemService.PatchPersonagemPorNome(nome, personagemDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPersonagemPorId(@PathVariable UUID id){
        personagemService.deletarPersonagem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Personagem> salvarPersonagem(@RequestBody @Validated(PersonagemValidation.OnNotPatch.class) PersonagemDTO personagemDTO){
        Personagem personagem = personagemService.salvarPersonagem(personagemDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(personagem);
    }
}
