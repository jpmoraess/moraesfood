package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import br.com.moraesit.moraesfood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<?> buscar(@PathVariable Long restauranteId) {
        final Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restauranteId);
        return restauranteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            final Optional<Restaurante> restauranteAtualOptional = restauranteRepository.findById(restauranteId);
            if (restauranteAtualOptional.isPresent()) {
                BeanUtils.copyProperties(restaurante, restauranteAtualOptional.get(),
                        "id", "formaPagamentos", "endereco", "dataCadastro", "produtos");
                return ResponseEntity.ok(restauranteService.salvar(restauranteAtualOptional.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> args) {
        final Optional<Restaurante> restauranteAtualOptional = restauranteRepository.findById(restauranteId);

        if (restauranteAtualOptional.isEmpty())
            return ResponseEntity.notFound().build();

        merge(args, restauranteAtualOptional.get());

        return atualizar(restauranteId, restauranteAtualOptional.get());
    }

    private void merge(Map<String, Object> args, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        final Restaurante restauranteOrigem = objectMapper.convertValue(args, Restaurante.class);
        args.forEach((property, value) -> {
            final Field field = ReflectionUtils.findField(Restaurante.class, property);
            if (field != null) {
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            }
        });
    }
}
