package br.com.moraesit.moraesfood.domain.service;

import br.com.moraesit.moraesfood.domain.entity.Estado;
import br.com.moraesit.moraesfood.domain.exception.EntidadeEmUsoException;
import br.com.moraesit.moraesfood.domain.exception.EstadoNaoEncontradoException;
import br.com.moraesit.moraesfood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de id %d não pode ser removido, pois está em uso.";

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }
}
