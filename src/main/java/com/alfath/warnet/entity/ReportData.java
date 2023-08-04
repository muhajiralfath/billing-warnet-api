package com.alfath.warnet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_report")
public class ReportData {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    @Column(name = "customer")
    private String customerName;
    @Column(name = "computer")
    private String ComputerName;
    @Column(name = "rental_time")
    private Long rentalTime;
    @Column(name = "total_price")
    private Long total_price;
    @Column(name = "create_at")
    private Timestamp createAt;
}
