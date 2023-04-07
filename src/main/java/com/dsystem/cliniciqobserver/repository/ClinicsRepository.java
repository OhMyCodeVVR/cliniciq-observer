package com.dsystem.cliniciqobserver.repository;

import com.dsystem.cliniciqobserver.model.Clinic;

import java.util.List;

public interface ClinicsRepository {


    List<String> findAllSchemasOfReallyWorkingClinics();

    List<Clinic> getCountOfDoctorsInSchemas();


    Integer getNumberEmployeesByDomain(String domain);
}
