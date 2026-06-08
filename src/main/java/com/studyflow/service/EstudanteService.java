package com.studyflow.service;

import com.studyflow.dto.EstudanteDTO;
import com.studyflow.entity.Estudante;
import com.studyflow.exception.EntidadeNaoEncontradaException;
import com.studyflow.exception.RegraNegocioException;
import com.studyflow.repository.EstudanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public EstudanteDTO criar(EstudanteDTO dto) {
        if (estudanteRepository.existsByEmail(dto.getEmail())) {
            throw new RegraNegocioException("Ja existe estudante cadastrado com este email.");
        }

        Estudante estudante = new Estudante(null, dto.getNome(), dto.getEmail(), dto.getSenha());
        return converterParaDTO(estudanteRepository.save(estudante));
    }

    public List<EstudanteDTO> listarTodos() {
        return estudanteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public EstudanteDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidadePorId(id));
    }

    public EstudanteDTO atualizar(Long id, EstudanteDTO dto) {
        Estudante estudante = buscarEntidadePorId(id);

        if (estudanteRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new RegraNegocioException("Ja existe outro estudante cadastrado com este email.");
        }

        estudante.setNome(dto.getNome());
        estudante.setEmail(dto.getEmail());
        estudante.setSenha(dto.getSenha());

        return converterParaDTO(estudanteRepository.save(estudante));
    }

    public void excluir(Long id) {
        Estudante estudante = buscarEntidadePorId(id);
        estudanteRepository.delete(estudante);
    }

    public Estudante buscarEntidadePorId(Long id) {
        return estudanteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Estudante nao encontrado."));
    }

    private EstudanteDTO converterParaDTO(Estudante estudante) {
        return new EstudanteDTO(
                estudante.getId(),
                estudante.getNome(),
                estudante.getEmail(),
                estudante.getSenha());
    }
}
