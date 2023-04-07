package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;
import com.dsystem.cliniciqobserver.repository.ClinicsBitrixRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClinicsBitrixListingServiceImpl implements ClinicsBitrixListingService {

    private final ClinicsBitrixRepository clinicsBitrixRepository;


    @Override
    public List<ClinicFromBitrix> getListOfActiveClinicsWithActiveToDates() {
        return clinicsBitrixRepository.getListOfActiveClinicsWithActiveToDates();
    }
}
