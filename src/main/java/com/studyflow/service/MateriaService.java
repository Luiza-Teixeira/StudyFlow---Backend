package com.studyflow.service;

import com.studyflow.dto.MateriaDTO;
import com.studyflow.entity.Materia;
import com.studyflow.exception.EntidadeNaoEncontradaException;
import com.studyflow.exception.RegraNegocioException;
import com.studyflow.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public MateriaDTO criar(MateriaDTO dto) {
        if (materiaRepository.existsByNomeAndPeriodo(dto.getNome(), dto.getPeriodo())) {
            throw new RegraNegocioException("Ja existe materia com este nome para o mesmo periodo.");
        }

        Materia materia = new Materia(null, dto.getNome(), dto.getDescricao(), dto.getPeriodo());
        return converterParaDTO(materiaRepository.save(materia));
    }

    public List<MateriaDTO> listarTodos() {
        return materiaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public MateriaDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidadePorId(id));
    }

    public MateriaDTO atualizar(Long id, MateriaDTO dto) {
        Materia materia = buscarEntidadePorId(id);

        if (materiaRepository.existsByNomeAndPeriodoAndIdNot(dto.getNome(), dto.getPeriodo(), id)) {
            throw new RegraNegocioException("Ja existe outra materia com este nome para o mesmo periodo.");
        }

        materia.setNome(dto.getNome());
        materia.setDescricao(dto.getDescricao());
        materia.setPeriodo(dto.getPeriodo());

        return converterParaDTO(materiaRepository.save(materia));
    }

    public void excluir(Long id) {
        Materia materia = buscarEntidadePorId(id);
        materiaRepository.delete(materia);
    }

    public Materia buscarEntidadePorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Materia nao encontrada."));
    }

    private MateriaDTO converterParaDTO(Materia materia) {
        return new MateriaDTO(
                materia.getId(),
                materia.getNome(),
                materia.getDescricao(),
                materia.getPeriodo());
    }
}
