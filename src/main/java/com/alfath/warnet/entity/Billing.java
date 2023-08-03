package com.alfath.warnet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "minutes")
    private Long minutes;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(name = "create_at", updatable = false)
    @CreatedDate
    private Date createAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private Date updateAt;

}
