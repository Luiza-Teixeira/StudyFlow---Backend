package com.studyflow.service;

import com.studyflow.dto.TarefaRequestDTO;
import com.studyflow.dto.TarefaResponseDTO;
import com.studyflow.entity.Tarefa;
import com.studyflow.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaResponseDTO salvarTarefa(TarefaRequestDTO dto) {
        if (dto.getPrazo() != null && dto.getPrazo().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O prazo da tarefa nao pode ser uma data retroativa.");
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(dto.getPrazo());
        tarefa.setStatus(dto.getStatus());

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        return new TarefaResponseDTO(
                tarefaSalva.getId(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.getPrazo(),
                tarefaSalva.getStatus()
        );
    }

    public List<TarefaResponseDTO> listarTodas() {
        return tarefaRepository.findAll().stream()
                .map(t -> new TarefaResponseDTO(t.getId(), t.getTitulo(), t.getDescricao(), t.getPrazo(), t.getStatus()))
                .collect(Collectors.toList());
    }

    public List<TarefaResponseDTO> listarPorStatus(String status) {
        return tarefaRepository.findByStatus(status).stream()
                .map(t -> new TarefaResponseDTO(t.getId(), t.getTitulo(), t.getDescricao(), t.getPrazo(), t.getStatus()))
                .collect(Collectors.toList());
    }
}