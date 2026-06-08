package com.studyflow.controller;

import com.studyflow.dto.TarefaRequestDTO;
import com.studyflow.dto.TarefaResponseDTO;
import com.studyflow.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody TarefaRequestDTO dto) {
        try {
            TarefaResponseDTO novaTarefa = tarefaService.salvarTarefa(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listar() {
        List<TarefaResponseDTO> tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TarefaResponseDTO>> listarPorStatus(@PathVariable String status) {
        List<TarefaResponseDTO> tarefas = tarefaService.listarPorStatus(status);
        return ResponseEntity.ok(tarefas);
    }
}