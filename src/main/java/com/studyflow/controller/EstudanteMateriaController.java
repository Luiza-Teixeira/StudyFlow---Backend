package com.studyflow.controller;

import com.studyflow.dto.EstudanteMateriaDTO;
import com.studyflow.service.EstudanteMateriaService;
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
@RequestMapping("/api/matriculas")
@Tag(name = "Matriculas", description = "CRUD da associacao entre estudantes e materias")
public class EstudanteMateriaController {

    private final EstudanteMateriaService estudanteMateriaService;

    public EstudanteMateriaController(EstudanteMateriaService estudanteMateriaService) {
        this.estudanteMateriaService = estudanteMateriaService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar matricula")
    public ResponseEntity<EstudanteMateriaDTO> criar(@Valid @RequestBody EstudanteMateriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudanteMateriaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar matriculas")
    public ResponseEntity<List<EstudanteMateriaDTO>> listarTodos() {
        return ResponseEntity.ok(estudanteMateriaService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar matricula por ID")
    public ResponseEntity<EstudanteMateriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estudanteMateriaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar matricula")
    public ResponseEntity<EstudanteMateriaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstudanteMateriaDTO dto) {
        return ResponseEntity.ok(estudanteMateriaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir matricula")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        estudanteMateriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
