package com.dsystem.cliniciqobserver.repository;

import com.dsystem.cliniciqobserver.model.Clinic;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 *@author Rumyancev Vladimir
 *@version 0.1
 *@since 2023-04-07
 */
@Repository
@Slf4j
public class ClinicsRepositoryImpl implements ClinicsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static List<String> schemas;

    public ClinicsRepositoryImpl(@Qualifier("dsystemNamedParameterJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        schemas = namedParameterJdbcTemplate.query(FIND_ALL_SQL_SCHEMAS, schemasMapper);
    }


    private final static RowMapper<String> schemasMapper = (row, number) -> row.getString("Database");
//    private static final RowMapper<Clinic> clinicsMapper = (row, rowNumber) -> {
//        return Clinic.builder()
//                .clinicName(row.getString(""))
//                .build();
//    };


    private final String FIND_ALL_SQL_SCHEMAS = "show schemas;";

    //search all ds_ prefix schemas on server. these schemas are really working clients on server
    @Override
    public List<String> findAllSchemasOfReallyWorkingClinics() {
        return schemas
                .stream()
                .filter(e -> e.contains("ds_"))
                .collect(Collectors.toList());
    }


    // common method for applying in listing ALL schemas on server
    @Override
    public List<Clinic> getCountOfDoctorsInSchemas() {
        List<Clinic> clinicListWithCountedDoctors = new ArrayList<>();
        List<String> schemas = this.findAllSchemasOfReallyWorkingClinics();
        for (String schemaName : schemas) {
            clinicListWithCountedDoctors.add(new Clinic(schemaName,
                    getNumberEmployeesByDomain(schemaName)));
        }
        return clinicListWithCountedDoctors;
    }

/*
* comes with prefix ds_ when applying contains() method on string list.
*  Used in cycle on list of schemas being applied for each domainName. Also used in MergedDataService
* @param domain -
* @return Integer.
 */
    @Override
    public Integer getNumberEmployeesByDomain(String domain) {
        if (schemas.contains(domain)) {
            int countOfDoctors = 0;
            StringBuilder sb = new StringBuilder("select count(*) count from ")
                    .append(domain)
                    .append(".`employee` where is_doctor is true and fire_date is null and status != 3");
            SqlParameterSource namedParameters = new MapSqlParameterSource("count", countOfDoctors);
            countOfDoctors = namedParameterJdbcTemplate.queryForObject(sb.toString(), namedParameters, Integer.class);

            return countOfDoctors;
        } else {
            return 0;
        }
    }
}
