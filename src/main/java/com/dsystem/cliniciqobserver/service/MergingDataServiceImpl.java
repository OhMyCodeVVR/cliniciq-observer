package com.dsystem.cliniciqobserver.service;

import com.dsystem.cliniciqobserver.model.Clinic;
import com.dsystem.cliniciqobserver.model.ClinicsDataMerged;
import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;
import com.dsystem.cliniciqobserver.repository.ClinicsBitrixRepositoryImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MergingDataServiceImpl implements MergingDataService {

    private static final Logger logger = LoggerFactory.getLogger(MergingDataServiceImpl.class);
    private ClinicsBitrixListingService clinicsBitrixListingService;
    private ClinicsDsystemListingService clinicsDsystemListingService;

    private final static RowMapper<ClinicFromBitrix> bitrixClinicsMapper = (row, number) -> ClinicFromBitrix.builder()
            .clinicName(row.getString("name"))
            .activeTo(row.getString("active_to"))
            .build();

    @Override
    public List<ClinicsDataMerged> mergeDataFromBothServices() {
        ArrayList<ClinicsDataMerged> clinicsDataMerged = new ArrayList<>();
        List<ClinicFromBitrix> clinicsFromBitrix = clinicsBitrixListingService.getListOfActiveClinicsWithActiveToDates();
        List<String> clinicsFromDsystem = clinicsDsystemListingService.findAllSchemas();
        for (String schemaName : clinicsFromDsystem) {
            System.out.println(("Schema name " + schemaName.substring(3)));
            ClinicFromBitrix tmpBtrxClinicData = clinicsFromBitrix.stream()
                    .filter(e -> e.getClinicName().contains(schemaName.substring(3))).findFirst().orElse(null);
            if(tmpBtrxClinicData != null) {
                clinicsDataMerged.add(ClinicsDataMerged.builder()
                        .doctorsCount(clinicsDsystemListingService.getNumberEmployeesByDomain(schemaName))
                        .clinicName(tmpBtrxClinicData.getClinicName())
                        .activeTo(tmpBtrxClinicData.getActiveTo())
                        .build());
            } else {
            }

        }
        return clinicsDataMerged;
    }
}
