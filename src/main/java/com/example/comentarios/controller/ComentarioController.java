package com.example.comentarios.controller;

import com.example.comentarios.entity.Comentario;
import com.example.comentarios.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ComentarioController {
    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @PostMapping("/comentarios")
    public Comentario criar(@RequestBody Comentario c) {
        return service.salvar(c);
    }

    @GetMapping("/comentarios/{produtoId}")
    public List<Comentario> listar(@PathVariable Integer produtoId) {
        return service.listarPorProduto(produtoId);
    }

    @GetMapping("/estatisticas")
    public Map<String, Long> estatisticas() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("positivos", service.contarPorSentimento("POSITIVO"));
        stats.put("negativos", service.contarPorSentimento("NEGATIVO"));
        return stats;
    }
}
