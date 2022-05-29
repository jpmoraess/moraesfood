package br.com.moraesit.moraesfood.api.controller;

import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.exception.NegocioException;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import br.com.moraesit.moraesfood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return restauranteService.buscar(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try {
            return restauranteService.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formaPagamentos", "endereco", "dataCadastro", "produtos");
            return restauranteService.salvar(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> args) {
        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

        merge(args, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
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
