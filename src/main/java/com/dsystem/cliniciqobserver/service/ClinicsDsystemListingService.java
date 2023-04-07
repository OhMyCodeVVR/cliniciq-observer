package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.Clinic;

import java.util.List;

public interface ClinicsDsystemListingService {

    List<Clinic> findAllClinics(List<String> schemas);

    List<Clinic> getCountOfDoctors();

    List<String> findAllSchemas();

    Integer getNumberEmployeesByDomain(String domain);

}
