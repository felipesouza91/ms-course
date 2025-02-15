package dev.fsantana.hrworker.resources;

import dev.fsantana.hrworker.entities.Worker;
import dev.fsantana.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class.getName());

    private final Environment env;

    private final WorkerRepository workerRepository;

    public WorkerResource(Environment env, WorkerRepository workerRepository) {
        this.env = env;
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok( workerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        Worker worker = workerRepository.findById(id).orElse(null);
        if(Objects.isNull(worker)) {
            return ResponseEntity.badRequest().build();
        }
        LOGGER.info("Request in Port:" + env.getProperty("server.port"));
        return ResponseEntity.ok(worker );
    }
}
