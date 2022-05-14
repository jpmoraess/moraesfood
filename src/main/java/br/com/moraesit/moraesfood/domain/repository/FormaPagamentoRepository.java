package br.com.moraesit.moraesfood.domain.repository;

import br.com.moraesit.moraesfood.domain.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();

    FormaPagamento buscar(Long id);

    FormaPagamento salvar(FormaPagamento formaPagamento);

    void remover(FormaPagamento formaPagamento);
}
