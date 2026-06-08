package com.studyflow.service;

import com.studyflow.dto.EstudanteMateriaDTO;
import com.studyflow.entity.Estudante;
import com.studyflow.entity.EstudanteMateria;
import com.studyflow.entity.Materia;
import com.studyflow.exception.EntidadeNaoEncontradaException;
import com.studyflow.exception.RegraNegocioException;
import com.studyflow.repository.EstudanteMateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteMateriaService {

    private final EstudanteMateriaRepository estudanteMateriaRepository;
    private final EstudanteService estudanteService;
    private final MateriaService materiaService;

    public EstudanteMateriaService(EstudanteMateriaRepository estudanteMateriaRepository,
                                   EstudanteService estudanteService,
                                   MateriaService materiaService) {
        this.estudanteMateriaRepository = estudanteMateriaRepository;
        this.estudanteService = estudanteService;
        this.materiaService = materiaService;
    }

    public EstudanteMateriaDTO criar(EstudanteMateriaDTO dto) {
        if (estudanteMateriaRepository.existsByEstudanteIdAndMateriaId(dto.getEstudanteId(), dto.getMateriaId())) {
            throw new RegraNegocioException("Estudante ja matriculado nesta materia.");
        }

        Estudante estudante = estudanteService.buscarEntidadePorId(dto.getEstudanteId());
        Materia materia = materiaService.buscarEntidadePorId(dto.getMateriaId());

        EstudanteMateria matricula = new EstudanteMateria(
                null,
                dto.getDataInscricao(),
                dto.getStatus(),
                estudante,
                materia);

        return converterParaDTO(estudanteMateriaRepository.save(matricula));
    }

    public List<EstudanteMateriaDTO> listarTodos() {
        return estudanteMateriaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public EstudanteMateriaDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidadePorId(id));
    }

    public EstudanteMateriaDTO atualizar(Long id, EstudanteMateriaDTO dto) {
        EstudanteMateria matricula = buscarEntidadePorId(id);

        if (estudanteMateriaRepository.existsByEstudanteIdAndMateriaIdAndIdNot(
                dto.getEstudanteId(), dto.getMateriaId(), id)) {
            throw new RegraNegocioException("Ja existe outra matricula para este estudante nesta materia.");
        }

        Estudante estudante = estudanteService.buscarEntidadePorId(dto.getEstudanteId());
        Materia materia = materiaService.buscarEntidadePorId(dto.getMateriaId());

        matricula.setDataInscricao(dto.getDataInscricao());
        matricula.setStatus(dto.getStatus());
        matricula.setEstudante(estudante);
        matricula.setMateria(materia);

        return converterParaDTO(estudanteMateriaRepository.save(matricula));
    }

    public void excluir(Long id) {
        EstudanteMateria matricula = buscarEntidadePorId(id);
        estudanteMateriaRepository.delete(matricula);
    }

    private EstudanteMateria buscarEntidadePorId(Long id) {
        return estudanteMateriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Matricula nao encontrada."));
    }

    private EstudanteMateriaDTO converterParaDTO(EstudanteMateria matricula) {
        return new EstudanteMateriaDTO(
                matricula.getId(),
                matricula.getDataInscricao(),
                matricula.getStatus(),
                matricula.getEstudante().getId(),
                matricula.getMateria().getId());
    }
}
