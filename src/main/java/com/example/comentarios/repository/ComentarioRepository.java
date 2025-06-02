package com.example.comentarios.repository;

import com.example.comentarios.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByProdutoId(Integer produtoId);
    List<Comentario> findByProcessadoFalse();
}
