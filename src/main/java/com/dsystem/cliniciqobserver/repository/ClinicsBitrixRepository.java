package com.dsystem.cliniciqobserver.repository;

import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;

import java.util.List;

public interface ClinicsBitrixRepository {


    List<ClinicFromBitrix> getListOfActiveClinicsWithActiveToDates();
}
