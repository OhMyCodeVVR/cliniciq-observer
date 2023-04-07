package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.Clinic;
import com.dsystem.cliniciqobserver.repository.ClinicsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicsDsystemListingImplService implements ClinicsDsystemListingService {

    private ClinicsRepository clinicsRepository;

    @Override
    public List<Clinic> findAllClinics(List<String> schemas) {
        return null;
    }

    @Override
    public List<Clinic> getCountOfDoctors() {
        return clinicsRepository.getCountOfDoctorsInSchemas();
    }

    @Override
    public List<String> findAllSchemas() {
        return clinicsRepository.findAllSchemasOfReallyWorkingClinics();
    }

    @Override
    public Integer getNumberEmployeesByDomain(String domain) {
            return clinicsRepository.getNumberEmployeesByDomain(domain);

        }
    }
