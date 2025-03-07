package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        checkStatus();
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
    }

    void checkStatus() {
        if (method.equals("Payment by Bank Transfer")) {
            if (paymentData.getOrDefault("bankName", null) == null) {
                this.status = PaymentStatus.REJECTED.getValue();
            } else if (paymentData.getOrDefault("referenceCode", null) == null) {
                this.status = PaymentStatus.REJECTED.getValue();
            } else {
                this.status = PaymentStatus.SUCCESS.getValue();
            }
        } else if (method.equals("Payment by Voucher Code")) {
            if (paymentData.getOrDefault("voucherCode", null) == null) {
                this.status = PaymentStatus.REJECTED.getValue();
            } else if (isValidVoucherCode(paymentData.get("voucherCode"))) {
                this.status = PaymentStatus.SUCCESS.getValue();
            } else {
                this.status = PaymentStatus.REJECTED.getValue();
            }
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return digitCount == 8;
    }
}
