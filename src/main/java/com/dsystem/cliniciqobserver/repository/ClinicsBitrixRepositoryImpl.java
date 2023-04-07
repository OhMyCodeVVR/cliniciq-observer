package com.dsystem.cliniciqobserver.repository;

import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/*
Lookup for clients whose accounts are active and have not null ACTIVE_TO field in bitrix db;
 */
@Repository
@Slf4j
public class ClinicsBitrixRepositoryImpl implements ClinicsBitrixRepository {

    private static Logger logger = LoggerFactory.getLogger(ClinicsBitrixRepositoryImpl.class);
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClinicsBitrixRepositoryImpl(@Qualifier("bitrixNamedParameterJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    private final static RowMapper<ClinicFromBitrix> bitrixClinicsMapper = (row, number) -> ClinicFromBitrix.builder()
            .clinicName(row.getString("name"))
            .activeTo(row.getString("active_to"))
            .build();
    private String FIND_CLINIC_ACTIVE_AND_WITH_DATE = "select name, active_to from bitrix_86.b_iblock_element where iblock_id = 10 and ACTIVE = 'Y' and " +
            "ACTIVE_TO IS NOT NULL ";
    @Override
    public List<ClinicFromBitrix> getListOfActiveClinicsWithActiveToDates() {
        List<ClinicFromBitrix> schemas = namedParameterJdbcTemplate.query(FIND_CLINIC_ACTIVE_AND_WITH_DATE, bitrixClinicsMapper);
        return schemas
                .stream()
                .collect(Collectors.toList());
    }

}