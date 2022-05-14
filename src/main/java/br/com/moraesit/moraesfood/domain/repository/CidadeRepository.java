package br.com.moraesit.moraesfood.domain.repository;

import br.com.moraesit.moraesfood.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cidade);

    void remover(Cidade cidade);
}
