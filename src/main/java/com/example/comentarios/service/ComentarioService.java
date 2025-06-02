package com.example.comentarios.service;

import com.example.comentarios.entity.Comentario;
import com.example.comentarios.repository.ComentarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ComentarioService {
    private static final Logger log = LoggerFactory.getLogger(ComentarioService.class);
    private final ComentarioRepository repo;
    private final Random rand = new Random();

    public ComentarioService(ComentarioRepository repo) {
        this.repo = repo;
    }

    public Comentario salvar(Comentario c) {
        return repo.save(c);
    }

    public List<Comentario> listarPorProduto(Integer produtoId) {
        return repo.findByProdutoId(produtoId);
    }

    public long contarPorSentimento(String sentimento) {
        return repo.findAll().stream().filter(c -> sentimento.equals(c.getSentimento())).count();
    }

    @Scheduled(fixedDelay = 5000)
    public void processarComentarios() {

        List<Comentario> pendentes = repo.findByProcessadoFalse();
        log.info("Iniciando processamento de comentários pendentes... {}", pendentes.size());
        for (Comentario c : pendentes) {
            c.setSentimento(rand.nextBoolean() ? "POSITIVO" : "NEGATIVO");
            c.setProcessado(true);
            repo.save(c);
        }
    }
}
