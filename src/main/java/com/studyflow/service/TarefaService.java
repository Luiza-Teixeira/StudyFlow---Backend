package com.studyflow.service;

import com.studyflow.entity.Tarefa;
import com.studyflow.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        if (tarefa.getPrazo() != null && tarefa.getPrazo().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O prazo da tarefa nao pode ser uma data retroativa.");
        }
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listarPorStatus(String status) {
        return tarefaRepository.findByStatus(status);
    }
}