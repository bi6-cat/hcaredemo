package com.zett.hcaredemo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "action")
    private String action;
    @Column(name = "adminName")// Nội dung hoạt động (VD: "Thêm bác sĩ mới")
    private String adminName;// Tên admin thực hiện
    @Column(name = "timestamp")
    private LocalDateTime timestamp; // Thời gian hoạt động
    @Column(name = "status")
    private String status; // Trạng thái: Thành công, Thất bại

}
