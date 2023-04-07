package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;

import java.util.List;

public interface ClinicsBitrixListingService {
    List<ClinicFromBitrix> getListOfActiveClinicsWithActiveToDates();
}
