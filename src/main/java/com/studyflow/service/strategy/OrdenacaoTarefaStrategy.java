package com.studyflow.service.strategy;

import com.studyflow.entity.Tarefa;

import java.util.List;

public interface OrdenacaoTarefaStrategy {

    boolean aceita(String criterio);

    List<Tarefa> ordenar(List<Tarefa> tarefas);
}
