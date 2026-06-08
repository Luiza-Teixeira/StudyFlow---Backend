package com.studyflow.service;

import com.studyflow.config.ConfiguracaoGlobalSistema;
import com.studyflow.dto.TarefaDTO;
import com.studyflow.entity.Categoria;
import com.studyflow.entity.Materia;
import com.studyflow.entity.Tarefa;
import com.studyflow.exception.EntidadeNaoEncontradaException;
import com.studyflow.exception.RegraNegocioException;
import com.studyflow.repository.TarefaRepository;
import com.studyflow.service.factory.TarefaFactory;
import com.studyflow.service.strategy.OrdenacaoTarefaStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final MateriaService materiaService;
    private final CategoriaService categoriaService;
    private final TarefaFactory tarefaFactory;
    private final ConfiguracaoGlobalSistema configuracaoGlobalSistema;
    private final List<OrdenacaoTarefaStrategy> estrategiasOrdenacao;

    public TarefaService(TarefaRepository tarefaRepository,
                         MateriaService materiaService,
                         CategoriaService categoriaService,
                         TarefaFactory tarefaFactory,
                         ConfiguracaoGlobalSistema configuracaoGlobalSistema,
                         List<OrdenacaoTarefaStrategy> estrategiasOrdenacao) {
        this.tarefaRepository = tarefaRepository;
        this.materiaService = materiaService;
        this.categoriaService = categoriaService;
        this.tarefaFactory = tarefaFactory;
        this.configuracaoGlobalSistema = configuracaoGlobalSistema;
        this.estrategiasOrdenacao = estrategiasOrdenacao;
    }

    public TarefaDTO criar(TarefaDTO dto) {
        validarPrazo(dto.getPrazo());

        Materia materia = materiaService.buscarEntidadePorId(dto.getMateriaId());
        Categoria categoria = categoriaService.buscarEntidadePorId(dto.getCategoriaId());
        Tarefa tarefa = tarefaFactory.criar(dto, materia, categoria);

        return converterParaDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> listarTodos(String ordenarPor) {
        String criterio = ordenarPor == null || ordenarPor.isBlank()
                ? configuracaoGlobalSistema.getOrdenacaoPadraoTarefa()
                : ordenarPor;

        List<Tarefa> tarefas = tarefaRepository.findAll();
        return selecionarEstrategia(criterio).ordenar(tarefas)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public TarefaDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidadePorId(id));
    }

    public TarefaDTO atualizar(Long id, TarefaDTO dto) {
        validarPrazo(dto.getPrazo());

        Tarefa tarefa = buscarEntidadePorId(id);
        Materia materia = materiaService.buscarEntidadePorId(dto.getMateriaId());
        Categoria categoria = categoriaService.buscarEntidadePorId(dto.getCategoriaId());

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(dto.getPrazo());
        tarefa.setStatus(dto.getStatus() == null || dto.getStatus().isBlank()
                ? configuracaoGlobalSistema.getStatusInicialTarefa()
                : dto.getStatus());
        tarefa.setMateria(materia);
        tarefa.setCategoria(categoria);

        return converterParaDTO(tarefaRepository.save(tarefa));
    }

    public void excluir(Long id) {
        Tarefa tarefa = buscarEntidadePorId(id);
        tarefaRepository.delete(tarefa);
    }

    private Tarefa buscarEntidadePorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tarefa nao encontrada."));
    }

    private void validarPrazo(LocalDate prazo) {
        if (prazo != null && prazo.isBefore(LocalDate.now())) {
            throw new RegraNegocioException("Prazo nao pode ser anterior a data atual.");
        }
    }

    private OrdenacaoTarefaStrategy selecionarEstrategia(String criterio) {
        return estrategiasOrdenacao.stream()
                .filter(estrategia -> estrategia.aceita(criterio))
                .findFirst()
                .orElseThrow(() -> new RegraNegocioException("Criterio de ordenacao de tarefa invalido."));
    }

    private TarefaDTO converterParaDTO(Tarefa tarefa) {
        return new TarefaDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getStatus(),
                tarefa.getMateria().getId(),
                tarefa.getCategoria().getId());
    }
}
