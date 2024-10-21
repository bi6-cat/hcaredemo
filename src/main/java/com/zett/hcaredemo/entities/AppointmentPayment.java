package com.zett.hcaredemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment_payment")
public class AppointmentPayment {

    @EmbeddedId
    private AppointmentPaymentId id;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AppointmentPaymentId implements Serializable {
        private String appointmentId;
        private String paymentId;
    }
}
