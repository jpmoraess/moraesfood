package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
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
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaService.buscar(cozinhaId);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaService.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.remover(cozinhaId);
    }
}
