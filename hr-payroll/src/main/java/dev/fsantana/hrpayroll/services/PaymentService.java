package dev.fsantana.hrpayroll.services;

import dev.fsantana.hrpayroll.dataprovider.WorkerClient;
import dev.fsantana.hrpayroll.entities.Payment;
import dev.fsantana.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class PaymentService {

    private final WorkerClient workerClient;

    public PaymentService(WorkerClient workerClient) {
        this.workerClient = workerClient;
    }

    public Payment getPayment(Long workerId, Integer days) {

        Worker worker = workerClient.findById(workerId).getBody();
        return new Payment(worker.getName(),  days, worker.getDailyIncome());
    }

}
