package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entities.AppointmentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentPaymentRepository extends JpaRepository<AppointmentPayment,
        AppointmentPayment.AppointmentPaymentId> {
}
