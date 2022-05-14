package br.com.moraesit.moraesfood.domain.repository;

import br.com.moraesit.moraesfood.domain.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);
}
