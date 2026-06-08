package com.studyflow.controller;

import com.studyflow.dto.MateriaDTO;
import com.studyflow.service.MateriaService;
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
@RequestMapping("/api/materias")
@Tag(name = "Materias", description = "CRUD de materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar materia")
    public ResponseEntity<MateriaDTO> criar(@Valid @RequestBody MateriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar materias")
    public ResponseEntity<List<MateriaDTO>> listarTodos() {
        return ResponseEntity.ok(materiaService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar materia por ID")
    public ResponseEntity<MateriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar materia")
    public ResponseEntity<MateriaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MateriaDTO dto) {
        return ResponseEntity.ok(materiaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir materia")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materiaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
