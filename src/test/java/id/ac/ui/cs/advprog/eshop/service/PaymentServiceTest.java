package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;
    Payment payment;
    Map<String, String> validPaymentData;
    Map<String, String> invalidPaymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");

        validPaymentData = new HashMap<>();
        validPaymentData.put("voucherCode", "ESHOP1234ABC5678"); // Valid code

        invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "INVALIDCODE1234"); // Invalid code

        payment = new Payment("idgacor123", "Payment by Voucher Code", validPaymentData);
    }

    @Test
    void testAddPayment_Success() {
        when(paymentRepository.addOrderPaymentMap(any(Order.class), any(Payment.class))).thenReturn(true);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, "Payment by Voucher Code", validPaymentData);

        assertNotNull(result);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPayment_Failure() {
        when(paymentRepository.addOrderPaymentMap(any(Order.class), any(Payment.class))).thenReturn(false);

        Payment result = paymentService.addPayment(order, "Payment by Voucher Code", validPaymentData);

        assertNull(result);
        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testSetStatus_Success() {
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatus_Rejected() {
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatus_InvalidPayment() {
        when(paymentRepository.findById(payment.getId())).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(payment, "SUCCESS"));
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        Payment result = paymentService.getPayment(payment.getId());

        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(payment, new Payment("idgacor456", "Payment by Bank Transfer", validPaymentData));
        when(paymentRepository.getPaymentData()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
    }
}
