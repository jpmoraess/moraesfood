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

import java.util.List;

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
        return cozinhaRepository.listar();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        final Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha != null)
            return ResponseEntity.ok(cozinha);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        final Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return ResponseEntity.ok(cozinhaService.salvar(cozinhaAtual));
        }
        return ResponseEntity.notFound().build();
    }

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
}
