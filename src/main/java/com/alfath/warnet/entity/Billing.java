package com.alfath.warnet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
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
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

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
