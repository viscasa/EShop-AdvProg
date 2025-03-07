package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @Test
    void testPaymentCreationMethodInvalid() {
        String id = "iniuuid1";
        String method = "HAHA";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testPaymentCreationMethodVoucherSuccess() {
        String id = "iniuuid1";
        String method = "Payment by Voucher Code";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentCreationMethodVoucherRejected() {
        String id = "iniuuid1";
        String method = "Payment by Voucher Code";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "NGASALISILOL");

        Payment payment = new Payment(id, method, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentCreationMethodBankSuccess() {
        String id = "iniuuid1";
        String method = "Payment by Bank Transfer";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "lalala");

        Payment payment = new Payment(id, method, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentCreationMethodBankRejected() {
        String id = "iniuuid1";
        String method = "Payment by Bank Transfer";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "lalala");

        Payment payment = new Payment(id, method, paymentData);

        String id2 = "iniuuid2";
        String method2 = "Payment by Bank Transfer";
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", null);

        Payment payment2 = new Payment(id2, method2, paymentData2);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());

        assertEquals(id2, payment2.getId());
        assertEquals(method2, payment2.getMethod());
        assertEquals(paymentData2, payment2.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
    }
}
