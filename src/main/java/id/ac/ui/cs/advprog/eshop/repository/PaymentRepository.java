package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    private Map<Order,Payment> orderPaymentsMap = new HashMap<>();

    public Payment save(Payment payment) {
        int i=0;
        for (Payment savedPayment : paymentData) {
            if(savedPayment.getId().equals(payment.getId())) {
                paymentData.remove(i);
                paymentData.add(i,payment);
                return payment;
            }
            i+=1;
        }
        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment payment : paymentData) {
            if(payment.getId().equals(id)) {
                return payment;
            }
        }
        return null;
    }

    public boolean addOrderPaymentMap(Order order, Payment payment) {
        if (orderPaymentsMap.containsKey(order)) {
            return false;
        }
        orderPaymentsMap.put(order,payment);
        return true;
    }
}
