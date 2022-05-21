package br.com.moraesit.moraesfood.infrastructure.repository.spec;

import br.com.moraesit.moraesfood.domain.entity.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis() {
        return ((root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO));
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return ((root, query, builder) -> builder.like(root.get("taxaFrete"), "%" + nome + "%"));
    }
}
