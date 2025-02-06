package dev.fsantana.hrpayroll.services;

import dev.fsantana.hrpayroll.entities.Payment;
import dev.fsantana.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(Long workerId, Integer days) {

        Worker worker = restTemplate.getForObject(
                URI.create(String.format(workerHost + "/workers/%s", workerId)), Worker.class);
        return new Payment(worker.getName(),  days, worker.getDailyIncome());
    }

}
