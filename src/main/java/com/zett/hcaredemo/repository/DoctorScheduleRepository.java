package com.zett.hcaredemo.repository;

import com.zett.hcaredemo.entity.Doctor;
import com.zett.hcaredemo.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
    List<DoctorSchedule> findByDoctorId(UUID doctorId);

    boolean existsByScheduleDateAndDoctor(LocalDate date, Doctor doctor);

    @Query(value = "SELECT * FROM doctor_schedules ds WHERE ds.is_available = 1 AND " +
                   "(ds.schedule_date < :currentDate OR (ds.schedule_date = :currentDate AND ds.time < :currentTime))",
           nativeQuery = true)
    List<DoctorSchedule> findExpiredSchedules(
        @Param("currentDate") String currentDate,
        @Param("currentTime") String currentTime
    );
}
