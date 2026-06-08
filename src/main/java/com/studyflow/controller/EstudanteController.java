package com.studyflow.controller;

import com.studyflow.dto.EstudanteDTO;
import com.studyflow.service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
@Tag(name = "Estudantes", description = "CRUD de estudantes")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar estudante")
    public ResponseEntity<EstudanteDTO> criar(@Valid @RequestBody EstudanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudanteService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar estudantes")
    public ResponseEntity<List<EstudanteDTO>> listarTodos() {
        return ResponseEntity.ok(estudanteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estudante por ID")
    public ResponseEntity<EstudanteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estudanteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estudante")
    public ResponseEntity<EstudanteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EstudanteDTO dto) {
        return ResponseEntity.ok(estudanteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir estudante")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        estudanteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
