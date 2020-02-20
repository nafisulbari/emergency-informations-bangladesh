package com.nafisulbari.eib.Controller;

import com.nafisulbari.eib.Model.CriminalRecord;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PoliceStationController {

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }


    @Autowired
    private PoliceStationService policeStationService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;


    @GetMapping("/police/{citizenId}/add-criminal-record")
    public ModelAndView addCriminalRecordPage(@PathVariable("citizenId") Long citizenId, Model model) {

        model.addAttribute("citizenId", citizenId);
        return new ModelAndView("police/add-criminal-record");
    }


    @PostMapping("/police/{citizenId}/add-criminal-record-action")
    public ModelAndView addCriminalRecordAction(@PathVariable("citizenId") Long citizenId, CriminalRecord criminalRecord, Model model) {

        policeStationService.saveCriminalRecord(criminalRecord,
                policeStationService.findPoliceStationByEmail(userService.getAuthUserEmail()),
                citizenService.findCitizenById(citizenId));

        model.addAttribute("flag", "Criminal Record Saved");

        return new ModelAndView("police/add-criminal-record");
    }

    @GetMapping("/police/edit-criminal-record/{id}/{citizenId}")
    public ModelAndView editCriminalRecord(@PathVariable("id") Long id,
                                           @PathVariable("citizenId") Long citizenId,
                                           Model model) {

        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);
        if (criminalRecord == null) {
            model.addAttribute("flag", "You do not have access to view this record");
            return new ModelAndView("police/add-criminal-record");
        }

        model.addAttribute("citizenId", citizenId);
        model.addAttribute("criminalRecord", criminalRecord);
        return new ModelAndView("police/add-criminal-record");
    }


    @PostMapping("/police/edit-criminal-record-action/{citizenId}/{policeStationId}/{recordId}")
    public ModelAndView editCriminalRecordAction(@PathVariable("citizenId") Long citizenId,
                                                 @PathVariable("policeStationId") Long policeStationId,
                                                 @PathVariable("recordId") Long recordId,
                                                 CriminalRecord criminalRecord, Model model) {

        criminalRecord.setId(recordId);
        policeStationService.saveCriminalRecord(criminalRecord,
                policeStationService.findPoliceStationById(policeStationId),
                citizenService.findCitizenById(citizenId));

        model.addAttribute("citizenId", criminalRecord.getCitizen().getId());
        model.addAttribute("flag", "Criminal Record Updated");
        return new ModelAndView("police/add-criminal-record");
    }


}
