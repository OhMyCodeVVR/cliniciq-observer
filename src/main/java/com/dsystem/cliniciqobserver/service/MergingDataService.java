package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.ClinicsDataMerged;

import java.util.List;

public interface MergingDataService {

    List<ClinicsDataMerged> mergeDataFromBothServices();

}
