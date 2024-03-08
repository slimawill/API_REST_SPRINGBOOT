package com.example.OAutoDaCompadecida.Service;

import com.example.OAutoDaCompadecida.Model.Entity.Personagem;
import com.example.OAutoDaCompadecida.Model.DTO.PersonagemDTO;
import com.example.OAutoDaCompadecida.Repository.PersonagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonagemService {

    private PersonagemRepository personagemRepository;

    @Autowired
    public PersonagemService(PersonagemRepository personagemRepository){
        this.personagemRepository = personagemRepository;
    }

    public List<Personagem> getTodosPersonagens(){
        return personagemRepository.findAll();
    }

    public Optional<Personagem> getPersonagemPorId(UUID id){
        return personagemRepository.findById(id);
    }

    @Transactional
    public Optional<Personagem> PutPersonagemPorId(UUID id, PersonagemDTO personagemDTO){
        return personagemRepository.findById(id)
                .map(origem -> {
                    origem.setAltura(personagemDTO.getAltura());
                    origem.setNome(personagemDTO.getNome());
                    origem.setPapelNaHistoria(personagemDTO.getPapelNaHistoria());
                    return personagemRepository.save(origem);
                });
    }

    public Personagem CreateEntityFromDTO(PersonagemDTO personagemDTO) {
        Personagem personagem = new Personagem(
                personagemDTO.getNome(),
                personagemDTO.getPapelNaHistoria(),
                personagemDTO.getAltura(),
                LocalDateTime.now()
        );
        return personagem;
    }

    @Transactional
    public Optional<Personagem> PutPersonagemPorNome(String nome, PersonagemDTO personagemDTO){
        return personagemRepository.findByNome(nome)
                .map(origem -> {
                    origem.setAltura(personagemDTO.getAltura());
                    origem.setNome(personagemDTO.getNome());
                    origem.setPapelNaHistoria(personagemDTO.getPapelNaHistoria());
                    return personagemRepository.save(origem);
                });
    }

    @Transactional
    public Optional<Personagem> PatchPersonagemPorId(UUID id, PersonagemDTO personagemDTO){
        return personagemRepository.findById(id)
                .map(origem -> {
                    if (personagemDTO.getNome() != null) origem.setNome(personagemDTO.getNome());
                    if (personagemDTO.getAltura() != null) origem.setAltura(personagemDTO.getAltura());
                    if (personagemDTO.getPapelNaHistoria() != null) origem.setPapelNaHistoria(personagemDTO.getPapelNaHistoria());
                    return personagemRepository.save(origem);
                });
    }

    @Transactional
    public Optional<Personagem> PatchPersonagemPorNome(String nome, PersonagemDTO personagemDTO){
        return personagemRepository.findByNome(nome)
                .map(origem -> {
                    if (personagemDTO.getNome() != null) origem.setNome(personagemDTO.getNome());
                    if (personagemDTO.getAltura() != null) origem.setAltura(personagemDTO.getAltura());
                    if (personagemDTO.getPapelNaHistoria() != null) origem.setPapelNaHistoria(personagemDTO.getPapelNaHistoria());
                    return personagemRepository.save(origem);
                });
    }

    public Personagem salvarPersonagem(PersonagemDTO personagemDTO){
        personagemDTO.setHoraDeCriacao(LocalDateTime.now());
        return personagemRepository.save(CreateEntityFromDTO(personagemDTO));
    }

    public Optional<Personagem> getPersonagemPorNome(String nome){
        return personagemRepository.findByNome(nome);
    }

    public void deletarPersonagem(UUID id) {
        personagemRepository.deleteById(id);
    }
}
