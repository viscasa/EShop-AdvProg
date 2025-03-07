package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment newPayment = new Payment(UUID.randomUUID().toString(), method, paymentData);

        if (!paymentRepository.addOrderPaymentMap(order, newPayment)) {
            return null;
        }

        paymentRepository.save(newPayment);
        return newPayment;
    };

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment findPayment = paymentRepository.findById(payment.getId());
        Map<Payment,Order> paymentOrderMap = paymentRepository.getOrderPaymentsMap();

        if (findPayment != null) {
            if (status.equals("SUCCESS")) {
                if (paymentOrderMap.containsKey(findPayment)) {
                    paymentOrderMap.get(findPayment).setStatus("SUCCESS");
                }
                Payment newPayment = new Payment(findPayment.getId(), findPayment.getMethod(), findPayment.getPaymentData(), status);
                paymentRepository.save(newPayment);
                return newPayment;
            } else if (status.equals("REJECTED")) {
                if (paymentOrderMap.containsKey(findPayment)) {
                        paymentOrderMap.get(findPayment).setStatus("FAILED");
                }
                Payment newPayment = new Payment(findPayment.getId(), findPayment.getMethod(), findPayment.getPaymentData(), status);
                paymentRepository.save(newPayment);
                return newPayment;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    };

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    };

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getPaymentData();
    };
}
