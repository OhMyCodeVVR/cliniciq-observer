package com.dsystem.cliniciqobserver.controller;


import com.dsystem.cliniciqobserver.model.Clinic;
import com.dsystem.cliniciqobserver.model.ClinicFromBitrix;
import com.dsystem.cliniciqobserver.model.ClinicsDataMerged;
import com.dsystem.cliniciqobserver.service.ClinicsBitrixListingService;
import com.dsystem.cliniciqobserver.service.ClinicsDsystemListingService;
import com.dsystem.cliniciqobserver.service.MergingDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ClinicsListController {


    private ClinicsDsystemListingService clinicsDsystemLister;
    private ClinicsBitrixListingService clinicsBitrixListingService;

    private MergingDataService mergingDataService;

    @GetMapping("/clinics")
    public String clinicsList(Model model) {
        List<Clinic> clinicsList = new ArrayList<>();

        clinicsList = clinicsDsystemLister.getCountOfDoctors();
        log.info("fetching clinics list, size: " + clinicsList.size());
        model.addAttribute("clinicsList", clinicsList);
        return "clinicsList";
    }


    @GetMapping("/clinicsBitrix")
    public String clinicsBitrixList(Model model) {
        List<ClinicFromBitrix> clinicsFromBitrixList = new ArrayList<>();
        clinicsFromBitrixList = clinicsBitrixListingService.getListOfActiveClinicsWithActiveToDates();
        log.info("fetching clinics from Bitrix " + clinicsFromBitrixList.size());
        model.addAttribute("clinicsFromBitrixList", clinicsFromBitrixList);
        return "clinicsBitrixList";
    }

    @GetMapping("/clinicsMergedInfo")
    public String clinicsMergedInfo(Model model) {
        List<ClinicsDataMerged> clinicsDataMerged = new ArrayList<>();
        clinicsDataMerged = mergingDataService.mergeDataFromBothServices();
        log.info("merging data " + clinicsDataMerged.size());
        model.addAttribute("clinicsDataMerged", clinicsDataMerged);
        return "clinicsMergedDataList";
    }


}

