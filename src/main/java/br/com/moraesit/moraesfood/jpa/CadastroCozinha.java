package br.com.moraesit.moraesfood.jpa;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }
}
