package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.repository.CozinhaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }
}
