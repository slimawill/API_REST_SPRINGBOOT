package com.example.OAutoDaCompadecida.PersonagemServiceTest;

import com.example.OAutoDaCompadecida.Model.DTO.PersonagemDTO;
import com.example.OAutoDaCompadecida.Model.Entity.Personagem;
import com.example.OAutoDaCompadecida.Repository.PersonagemRepository;
import com.example.OAutoDaCompadecida.Service.PersonagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonagemServiceTest {

    @Mock
    private PersonagemRepository personagemRepository;

    @InjectMocks
    private PersonagemService personagemService;

    @Test
    void getTodosPersonagens() {
        Personagem personagem1 = new Personagem(UUID.randomUUID(), "Personagem 1", "Papel 1", 1.75F, LocalDateTime.now());
        Personagem personagem2 = new Personagem(UUID.randomUUID(), "Personagem 2", "Papel 2", 1.80F, LocalDateTime.now());
        List<Personagem> personagens = Arrays.asList(personagem1, personagem2);
        when(personagemRepository.findAll()).thenReturn(personagens);

        List<Personagem> result = personagemService.getTodosPersonagens();

        assertEquals(personagens, result);
    }

    @Test
    void getPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        Personagem personagem = new Personagem(personagemId, "Personagem", "Papel", 1.75F, LocalDateTime.now());
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.of(personagem));

        Optional<Personagem> result = personagemService.getPersonagemPorId(personagemId);

        assertTrue(result.isPresent());
        assertEquals(personagem, result.get());
    }

    @Test
    void getPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.getPersonagemPorId(personagemId);

        assertTrue(result.isEmpty());
    }

    @Test
    void putPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        Personagem personagem = new Personagem(personagemId, "Personagem", "Papel", 1.75F, LocalDateTime.now());
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80F);
        Personagem personagemAtualizado = new Personagem(personagemId, "Personagem Atualizado", "Papel Atualizado", 1.80f, LocalDateTime.now());
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.of(personagem));
        when(personagemRepository.save(personagem)).thenReturn(personagemAtualizado);

        Optional<Personagem> result = personagemService.PutPersonagemPorId(personagemId, personagemDTO);

        assertTrue(result.isPresent());
        assertEquals(personagemAtualizado, result.get());
    }

    @Test
    void putPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80F);
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.PutPersonagemPorId(personagemId, personagemDTO);

        assertTrue(result.isEmpty());
    }

    @Test
    void putPersonagemPorNomeExistente() {
        String nome = "Personagem";
        Personagem personagem = new Personagem(UUID.randomUUID(), nome, "Papel", 1.75F, LocalDateTime.now());
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80F);
        Personagem personagemAtualizado = new Personagem(personagem.getUuid(), "Personagem Atualizado", "Papel Atualizado", 1.80F, LocalDateTime.now());
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.of(personagem));
        when(personagemRepository.save(personagem)).thenReturn(personagemAtualizado);

        Optional<Personagem> result = personagemService.PutPersonagemPorNome(nome, personagemDTO);

        assertTrue(result.isPresent());
        assertEquals(personagemAtualizado, result.get());
    }

    @Test
    void putPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO("Personagem Atualizado", "Papel Atualizado", 1.80F);
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.PutPersonagemPorNome(nome, personagemDTO);

        assertTrue(result.isEmpty());
    }

    @Test
    void patchPersonagemPorIdExistente() {
        UUID personagemId = UUID.randomUUID();
        Personagem personagem = new Personagem(personagemId, "Personagem", "Papel", 1.75F, LocalDateTime.now());
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        Personagem personagemAtualizado = new Personagem(personagemId, "Personagem", "Papel", 1.80F, LocalDateTime.now());
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.of(personagem));
        when(personagemRepository.save(personagem)).thenReturn(personagemAtualizado);

        Optional<Personagem> result = personagemService.PatchPersonagemPorId(personagemId, personagemDTO);

        assertTrue(result.isPresent());
        assertEquals(personagemAtualizado, result.get());
    }

    @Test
    void patchPersonagemPorIdNaoExistente() {
        UUID personagemId = UUID.randomUUID();
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        when(personagemRepository.findById(personagemId)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.PatchPersonagemPorId(personagemId, personagemDTO);

        assertTrue(result.isEmpty());
    }

    @Test
    void patchPersonagemPorNomeExistente() {
        String nome = "Personagem";
        Personagem personagem = new Personagem(UUID.randomUUID(), nome, "Papel", 1.75F, LocalDateTime.now());
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        Personagem personagemAtualizado = new Personagem(personagem.getUuid(), nome, "Papel", 1.80F, LocalDateTime.now());
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.of(personagem));
        when(personagemRepository.save(personagem)).thenReturn(personagemAtualizado);

        Optional<Personagem> result = personagemService.PatchPersonagemPorNome(nome, personagemDTO);

        assertTrue(result.isPresent());
        assertEquals(personagemAtualizado, result.get());
    }

    @Test
    void patchPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        PersonagemDTO personagemDTO = new PersonagemDTO();
        personagemDTO.setAltura(1.80F);
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.PatchPersonagemPorNome(nome, personagemDTO);

        assertTrue(result.isEmpty());
    }

    @Test
    void salvarPersonagem() {
        PersonagemDTO personagemDTO = new PersonagemDTO("Novo Personagem", "Novo Papel", 1.75F);
        Personagem personagemSalvo = new Personagem(UUID.randomUUID(), "Novo Personagem", "Novo Papel", 1.75F, LocalDateTime.now());
        when(personagemRepository.save(any(Personagem.class))).thenReturn(personagemSalvo);

        Personagem result = personagemService.salvarPersonagem(personagemDTO);

        assertEquals(personagemSalvo, result);
    }

    @Test
    void getPersonagemPorNomeExistente() {
        String nome = "Personagem";
        Personagem personagem = new Personagem(UUID.randomUUID(), nome, "Papel", 1.75F, LocalDateTime.now());
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.of(personagem));

        Optional<Personagem> result = personagemService.getPersonagemPorNome(nome);

        assertTrue(result.isPresent());
        assertEquals(personagem, result.get());
    }

    @Test
    void getPersonagemPorNomeNaoExistente() {
        String nome = "Personagem";
        when(personagemRepository.findByNome(nome)).thenReturn(Optional.empty());

        Optional<Personagem> result = personagemService.getPersonagemPorNome(nome);

        assertTrue(result.isEmpty());
    }

    @Test
    void deletarPersonagem() {
        UUID personagemId = UUID.randomUUID();

        personagemService.deletarPersonagem(personagemId);

        verify(personagemRepository, times(1)).deleteById(personagemId);
    }
}