package com.dsystem.cliniciqobserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClinicsDataMerged {
    private String clinicName;
    private String activeTo;

    private int doctorsCount;

}
