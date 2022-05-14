package br.com.moraesit.moraesfood.domain.repository;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Cozinha cozinha);
}
