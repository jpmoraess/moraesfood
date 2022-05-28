package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.exception.EntidadeEmUsoException;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.CozinhaRepository;
import br.com.moraesit.moraesfood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;
    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaService cozinhaService, CozinhaRepository cozinhaRepository) {
        this.cozinhaService = cozinhaService;
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        final Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinhaId);
        return cozinhaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        final Optional<Cozinha> cozinhaAtualOptional = cozinhaRepository.findById(cozinhaId);
        if (cozinhaAtualOptional.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtualOptional.get(), "id");
            return ResponseEntity.ok(cozinhaService.salvar(cozinhaAtualOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /* Usar para outros exemplos de tratamento de exception

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity remover(@PathVariable Long cozinhaId) {
        try {
            cozinhaService.remover(cozinhaId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
     */

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        try {
            cozinhaService.remover(cozinhaId);
        } catch (EntidadeNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntidadeEmUsoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
