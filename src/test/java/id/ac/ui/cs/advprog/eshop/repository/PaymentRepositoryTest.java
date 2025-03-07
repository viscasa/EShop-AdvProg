package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        String id = "iniuuid1";
        String method = "Payment by Voucher Code";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, paymentData);

        String id2 = "iniuuid2";
        String method2 = "Payment by Bank Transfer";
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "lalala");

        Payment payment2 = new Payment(id2, method2, paymentData2);

        payments.add(payment);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findPayment.getId());
        assertEquals(payment.getMethod(), findPayment.getMethod());
        assertEquals(payment.getPaymentData(), findPayment.getPaymentData());
        assertEquals(payment.getStatus() , findPayment.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData(), PaymentStatus.REJECTED.getValue());
        Payment result = paymentRepository.save(newPayment);

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findPayment.getId());
        assertEquals(payment.getMethod(), findPayment.getMethod());
        assertEquals(payment.getPaymentData(), findPayment.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue() , findPayment.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findPayment.getId());
        assertEquals(payments.get(1).getMethod(), findPayment.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findPayment.getPaymentData());
        assertEquals(payments.get(1).getStatus() , findPayment.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById("zczc");
        assertNull(findPayment);
    }
}
