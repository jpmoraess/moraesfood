package br.com.moraesit.moraesfood.infrastructure.repository;

import br.com.moraesit.moraesfood.domain.entity.Cidade;
import br.com.moraesit.moraesfood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        final Cidade cidade = buscar(id);
        if (cidade == null)
            throw new EmptyResultDataAccessException(1);
        entityManager.remove(cidade);
    }
}
