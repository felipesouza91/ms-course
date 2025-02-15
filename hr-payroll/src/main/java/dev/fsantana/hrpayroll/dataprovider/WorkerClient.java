package dev.fsantana.hrpayroll.dataprovider;

import dev.fsantana.hrpayroll.entities.Worker;
import org.apache.coyote.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker",  path = "/workers")
public interface WorkerClient {

    @GetMapping("/{id}")
    ResponseEntity<Worker> findById(@PathVariable  Long id);

}
