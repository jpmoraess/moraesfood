package br.com.moraesit.moraesfood.domain.service;

import br.com.moraesit.moraesfood.domain.entity.Estado;
import br.com.moraesit.moraesfood.domain.exception.EntidadeEmUsoException;
import br.com.moraesit.moraesfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.moraesit.moraesfood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.remover(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com id %id", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de id %d não pode ser removido, pois está em uso.", estadoId));
        }
    }
}
