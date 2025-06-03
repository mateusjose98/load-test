package com.example.comentarios.controller;

import com.example.comentarios.entity.Comentario;
import com.example.comentarios.service.ComentarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ComentarioController {
    private final ComentarioService service;
    private static final Logger log = LoggerFactory.getLogger(ComentarioController.class);

    @Value("${instance.id}")
    private String instanceId;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @PostMapping("/comentarios")
    public Comentario criar(@RequestBody Comentario c) {
        log.info("🚀 [INSTÂNCIA {}] Recebido POST /comentarios para produtoId {}", instanceId, c.getProdutoId());

        return service.salvar(c);
    }

    @GetMapping("/estatisticas")
    public Map<String, Long> estatisticas() {
        log.info("📊 [INSTÂNCIA {}] Requisição GET /estatisticas", instanceId);
        Map<String, Long> stats = new HashMap<>();
        stats.put("positivos", service.contarPorSentimento("POSITIVO"));
        stats.put("negativos", service.contarPorSentimento("NEGATIVO"));
        return stats;
    }
}
