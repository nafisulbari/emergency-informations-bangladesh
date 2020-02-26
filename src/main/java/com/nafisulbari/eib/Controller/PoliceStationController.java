package com.nafisulbari.eib.Controller;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.CriminalRecord;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PoliceStationController {


    @Autowired
    PoliceStationService policeStationService;

    @Autowired
    CitizenService citizenService;

    @Autowired
    UserService userService;

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }


    @GetMapping("/police/{citizenId}/add-criminal-record")
    public ModelAndView addCriminalRecordPage(@PathVariable("citizenId") Long citizenId, Model model) {


        Citizen citizen = citizenService.findCitizenById(citizenId);

        String authUserEmail = userService.getAuthUserEmail();
        model.addAttribute("authUserEmail", authUserEmail);

        model.addAttribute("citizen", citizen);
        return new ModelAndView("police/add-criminal-record");
    }


    @PostMapping("/police/{citizenId}/add-criminal-record-action")
    public ModelAndView addCriminalRecordAction(@PathVariable("citizenId") Long citizenId, CriminalRecord criminalRecord, String citizenPoint, Model model) {


        switch (citizenService.addCitizenPoints(citizenPoint)) {
            case "noCitizenFound":
                model.addAttribute("flagCp", "No citizen found with id " + citizenPoint + " to add citizen points");
                break;
            case "added":
                model.addAttribute("flagCp", "Added citizen points to " + citizenPoint);
            default:
        }

        Citizen citizen = citizenService.findCitizenById(citizenId);
        policeStationService.saveCriminalRecord(criminalRecord,
                policeStationService.findPoliceStationByEmail(userService.getAuthUserEmail()),
                citizen);

        String authUserEmail = userService.getAuthUserEmail();

        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("flag", "Criminal record add requested for review");
        model.addAttribute("citizen", citizen);

        return new ModelAndView("police/add-criminal-record");
    }


    @GetMapping("/police/edit-criminal-record/{id}/{citizenId}")
    public ModelAndView editCriminalRecord(@PathVariable("id") Long id,
                                          @PathVariable("citizenId") Long citizenId,
                                          Model model) {

        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);

        if (criminalRecord == null) {
            model.addAttribute("flag", "You do not have access to view this record");
            return new ModelAndView("redirect:/error");
        }

        Citizen citizen = citizenService.findCitizenById(citizenId);
        String authUserEmail = userService.getAuthUserEmail();

        model.addAttribute("criminalRecord", criminalRecord);
        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("citizen", citizen);
        return new ModelAndView("police/add-criminal-record");
    }


    @PostMapping("/police/edit-criminal-record-action/{citizenId}/{policeStationId}/{recordId}")
    public ModelAndView editCriminalRecordAction(@PathVariable("citizenId") Long citizenId,
                                                @PathVariable("policeStationId") Long policeStationId,
                                                @PathVariable("recordId") Long recordId,
                                                CriminalRecord criminalRecord, Model model) {


        Citizen citizen = citizenService.findCitizenById(citizenId);


        criminalRecord.setId(recordId);
        policeStationService.saveCriminalRecord(criminalRecord,
                policeStationService.findPoliceStationById(policeStationId),
                citizen);

        String authUserEmail = userService.getAuthUserEmail();
        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("flag", "Criminal record update requested for review");
        model.addAttribute("citizen", citizen);

        return new ModelAndView("police/add-criminal-record");
    }


    @GetMapping("/police/police-stations-records")
    public ModelAndView citizensOfPoliceStation(@RequestParam(name = "search", required = false) String key,
                                           Model model) {
        if (key != null && !key.equals("")) {

            model.addAttribute("criminalRecords", policeStationService.searchCriminalRecordsByCitizen(key));
            return new ModelAndView("/police/police-stations-records");
        }
        List<CriminalRecord> criminalRecords = policeStationService.findCriminalRecordsOfPoliceStation();

        model.addAttribute("criminalRecords", criminalRecords);

        return new ModelAndView("/police/police-stations-records");
    }


}
