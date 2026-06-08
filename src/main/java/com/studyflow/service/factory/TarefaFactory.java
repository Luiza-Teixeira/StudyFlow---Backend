package com.studyflow.service.factory;

import com.studyflow.config.ConfiguracaoGlobalSistema;
import com.studyflow.dto.TarefaDTO;
import com.studyflow.entity.Categoria;
import com.studyflow.entity.Materia;
import com.studyflow.entity.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaFactory {

    private final ConfiguracaoGlobalSistema configuracaoGlobalSistema;

    public TarefaFactory(ConfiguracaoGlobalSistema configuracaoGlobalSistema) {
        this.configuracaoGlobalSistema = configuracaoGlobalSistema;
    }

    public Tarefa criar(TarefaDTO dto, Materia materia, Categoria categoria) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(dto.getPrazo());
        tarefa.setStatus(configuracaoGlobalSistema.getStatusInicialTarefa());
        tarefa.setMateria(materia);
        tarefa.setCategoria(categoria);
        return tarefa;
    }
}
