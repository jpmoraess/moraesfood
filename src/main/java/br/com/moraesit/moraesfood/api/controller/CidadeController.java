package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Cidade;
import br.com.moraesit.moraesfood.domain.exception.EntidadeEmUsoException;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.CidadeRepository;
import br.com.moraesit.moraesfood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeRepository cidadeRepository;

    public CidadeController(CidadeService cidadeService, CidadeRepository cidadeRepository) {
        this.cidadeService = cidadeService;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        final Cidade cidade = cidadeRepository.buscar(cidadeId);
        if (cidade != null)
            return ResponseEntity.ok(cidade);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidadeService.salvar(cidade));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeAId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        final Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        if (cidadeAtual != null) {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return ResponseEntity.ok(cidadeService.salvar(cidadeAtual));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cidadeAId}")
    public ResponseEntity<?> remover(@PathVariable Long cidadeAId) {
        try {
            cidadeService.excluir(cidadeAId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
