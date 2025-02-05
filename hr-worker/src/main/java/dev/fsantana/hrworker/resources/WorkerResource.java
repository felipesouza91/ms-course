package dev.fsantana.hrworker.resources;

import dev.fsantana.hrworker.entities.Worker;
import dev.fsantana.hrworker.repositories.WorkerRepository;
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

    private final WorkerRepository workerRepository;

    public WorkerResource(WorkerRepository workerRepository) {
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
        return ResponseEntity.ok(worker );
    }
}
