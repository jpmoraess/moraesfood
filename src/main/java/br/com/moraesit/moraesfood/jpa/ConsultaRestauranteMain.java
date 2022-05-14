package br.com.moraesit.moraesfood.jpa;

import br.com.moraesit.moraesfood.MoraesfoodApplication;
import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import br.com.moraesit.moraesfood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(MoraesfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);

        final List<Restaurante> restaurantes = restauranteRepository.listar();

        restaurantes.forEach(restaurante -> System.out.printf("Restaurante: %s, Cozinha: %s, Taxa Frete: %f\n", restaurante.getNome(), restaurante.getCozinha().getNome(), restaurante.getTaxaFrete()));
    }
}
