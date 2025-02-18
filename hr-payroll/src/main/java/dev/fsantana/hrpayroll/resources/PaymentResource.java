package dev.fsantana.hrpayroll.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dev.fsantana.hrpayroll.entities.Payment;
import dev.fsantana.hrpayroll.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @HystrixCommand(fallbackMethod = "getPaymentAlternatives")
    @GetMapping("/{workerId}/days/{daysValues}")
    public ResponseEntity<Payment> calculePayment(@PathVariable Long workerId, @PathVariable Integer daysValues) {
        Payment payment = paymentService.getPayment(workerId, daysValues);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternatives(Long workerId, Integer daysValues) {
        Payment payment = new Payment("Brann", daysValues, 400.0);
        return ResponseEntity.ok(payment);
    }
}
