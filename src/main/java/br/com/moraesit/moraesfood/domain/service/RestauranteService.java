package br.com.moraesit.moraesfood.domain.service;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.CozinhaRepository;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(CozinhaRepository cozinhaRepository, RestauranteRepository restauranteRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante salvar(Restaurante restaurante) {
        final Long cozinhaId = restaurante.getCozinha().getId();
        final Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cozinha com o id: %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }
}
