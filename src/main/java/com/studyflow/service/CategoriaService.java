package com.studyflow.service;

import com.studyflow.dto.CategoriaDTO;
import com.studyflow.entity.Categoria;
import com.studyflow.exception.EntidadeNaoEncontradaException;
import com.studyflow.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria(null, dto.getNome(), dto.getDescricao(), dto.getCor());
        return converterParaDTO(categoriaRepository.save(categoria));
    }

    public List<CategoriaDTO> listarTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public CategoriaDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidadePorId(id));
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = buscarEntidadePorId(id);
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        categoria.setCor(dto.getCor());
        return converterParaDTO(categoriaRepository.save(categoria));
    }

    public void excluir(Long id) {
        Categoria categoria = buscarEntidadePorId(id);
        categoriaRepository.delete(categoria);
    }

    public Categoria buscarEntidadePorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria nao encontrada."));
    }

    private CategoriaDTO converterParaDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getCor());
    }
}
