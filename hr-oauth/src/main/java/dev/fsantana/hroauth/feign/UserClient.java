package dev.fsantana.hroauth.feign;

import dev.fsantana.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hr-user", path = "/users")
public interface UserClient {

    @GetMapping("/search")
    ResponseEntity<User> getByEmail(@RequestParam String email);
}
