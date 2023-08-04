package com.alfath.warnet.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReportDataResponse {

    private String customerName;
    private String ComputerName;
    private Long rentalTime;
    private Long total_price;
    private Timestamp createAt;

}
