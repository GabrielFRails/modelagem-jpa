package br.ufg.inf.tutorial;

import java.time.LocalDate;

import br.ufg.inf.tutorial.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("=== Iniciando Tutorial de Modelagem ORM com JPA/Hibernate ===");

        try {
            testarConexao();
            testarPersistenciaEstudante();
            logger.info("=== Tutorial executado com sucesso! ===");
        } catch (Exception e) {
            logger.error("Erro durante execução do tutorial: {}", e.getMessage(), e);
        } finally {
            JPAUtil.closeEntityManagerFactory();
            logger.info("=== Recursos liberados. Fim do tutorial. ===");
        }
    }

    private static void testarConexao() {
        logger.info("Testando conexão com o banco de dados...");
        EntityManager em = JPAUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "SELECT 1 as teste";
            Object resultado = em.createNativeQuery(sql).getSingleResult();
            logger.info("Conexão testada com sucesso! Resultado: {}", resultado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager(em);
        }
    }

    private static void testarPersistenciaEstudante() {
        logger.info("Testando persistência de um Estudante... Gabriel Freitas");
        JPAUtil.executeInTransaction(em -> {
            Estudante estudante = new Estudante();
            estudante.setLogin("estudante1");
            estudante.setSenha("123");
            estudante.setNomeCompleto("Gabriel Freitas");
            estudante.setDataDeNascimento(LocalDate.now());
            estudante.setMatricula("202501");
            em.persist(estudante);
            logger.info("Estudante salvo com ID: {}", estudante.getId());
        });

        JPAUtil.executeInTransaction(em -> {
            Estudante estudante = new Estudante();
            estudante.setLogin("estudante2");
            estudante.setSenha("123");
            estudante.setNomeCompleto("Tallya Jesus");
            estudante.setDataDeNascimento(LocalDate.now());
            estudante.setMatricula("202502");
            em.persist(estudante);
            logger.info("Estudante salvo com ID: {}", estudante.getId());
        });
    }
}