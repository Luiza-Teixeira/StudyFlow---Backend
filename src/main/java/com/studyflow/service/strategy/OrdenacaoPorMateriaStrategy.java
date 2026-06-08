package com.studyflow.service.strategy;

import com.studyflow.entity.Tarefa;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class OrdenacaoPorMateriaStrategy implements OrdenacaoTarefaStrategy {

    @Override
    public boolean aceita(String criterio) {
        return "materia".equalsIgnoreCase(criterio);
    }

    @Override
    public List<Tarefa> ordenar(List<Tarefa> tarefas) {
        return tarefas.stream()
                .sorted(Comparator.comparing(tarefa -> tarefa.getMateria().getNome()))
                .toList();
    }
}
