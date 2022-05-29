package br.com.moraesit.moraesfood.domain.service;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final CozinhaService cozinhaService;
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(CozinhaService cozinhaService, RestauranteRepository restauranteRepository) {
        this.cozinhaService = cozinhaService;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.buscar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
