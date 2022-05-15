package br.com.moraesit.moraesfood.domain.repository;

import br.com.moraesit.moraesfood.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Long id);
}
