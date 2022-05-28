package br.com.moraesit.moraesfood.domain.service;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe restaurante com o id: %d";

    private final CozinhaService cozinhaService;
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(CozinhaService cozinhaService, RestauranteRepository restauranteRepository) {
        this.cozinhaService = cozinhaService;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }

    public Restaurante salvar(Restaurante restaurante) {
        final Long cozinhaId = restaurante.getCozinha().getId();

        final Cozinha cozinha = cozinhaService.buscar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
