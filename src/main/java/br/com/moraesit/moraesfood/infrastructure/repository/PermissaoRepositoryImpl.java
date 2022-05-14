package br.com.moraesit.moraesfood.infrastructure.repository;

import br.com.moraesit.moraesfood.domain.entity.Permissao;
import br.com.moraesit.moraesfood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        return entityManager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return entityManager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = entityManager.find(Permissao.class, permissao.getId());
        entityManager.remove(permissao);
    }
}
