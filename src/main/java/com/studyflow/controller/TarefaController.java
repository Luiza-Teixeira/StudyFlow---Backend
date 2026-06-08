package com.studyflow.controller;

import com.studyflow.dto.TarefaDTO;
import com.studyflow.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@Tag(name = "Tarefas", description = "CRUD de tarefas academicas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar tarefa")
    public ResponseEntity<TarefaDTO> criar(@Valid @RequestBody TarefaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar tarefas")
    public ResponseEntity<List<TarefaDTO>> listarTodos(
            @Parameter(description = "Criterios aceitos: prazo, status ou materia")
            @RequestParam(required = false) String ordenarPor) {
        return ResponseEntity.ok(tarefaService.listarTodos(ordenarPor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaDTO dto) {
        return ResponseEntity.ok(tarefaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
