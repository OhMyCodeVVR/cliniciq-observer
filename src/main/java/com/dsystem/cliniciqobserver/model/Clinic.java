package com.dsystem.cliniciqobserver.model;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
public class Clinic {
    private String clinicName;
    private int count;


}
