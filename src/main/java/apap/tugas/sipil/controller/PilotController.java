package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.repository.PilotDb;
import apap.tugas.sipil.service.AkademisService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PilotService;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.service.PenerbanganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.Optional;

@Controller
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Qualifier("akademiServiceImpl")
    @Autowired
    private AkademisService akademiService;

    @Qualifier("maskapaiServiceImpl")
    @Autowired
    private MaskapaiService maskapaiService;


    @Autowired
    public PilotDb pilotDb;

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/pilot")
    public String listPilot(Model model){
        List<PilotModel> listPilot=pilotService.getPilotList();
        model.addAttribute("listPilot", listPilot);
        return "daftar-pilot";
    }
    @GetMapping("pilot/tambah")
    public String addPilot(Model model){
        model.addAttribute("pilot",new PilotModel());
        model.addAttribute("listAkademi", akademiService.getAkademiList());
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        return "add-pilot";
    }



    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
            @ModelAttribute PilotModel pilot,
            Model model){
        pilot.setNip(pilotService.generatenip(pilot.getNama(),pilot.getJenisKelamin(),pilot.getTempatLahir(),pilot.getTanggalLahir()));
        pilotService.addPilot(pilot);
        model.addAttribute("nipPilot",pilot.getNip());
        return "add-done";
    }

    @GetMapping("/pilot/{nip}")
    public String detailPilot(
            @PathVariable String nip,
            Model model
    ){
            PilotModel pilot=pilotService.getPilotByNip(nip);
            model.addAttribute("pilot",pilot);
            model.addAttribute("listPilotPenerbanganModel", pilot.getPilotPenerbanganModel());
            return "detail-pilot";
    }

    @GetMapping("pilot/ubah/{nip}")
    public String ubahPilot(
            @PathVariable String nip, Model model
    ){
        PilotModel pilot=pilotService.getPilotByNip(nip);
        model.addAttribute("pilot",pilot);
        model.addAttribute("listAkademi", akademiService.getAkademiList());
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        return "ubah-pilot";
    }

    @PostMapping("/pilot/ubah")
    public String ubahPilotSubmit(
            @ModelAttribute PilotModel pilot, Model model){
                pilot.setNip(pilotService.generatenip(pilot.getNama(),pilot.getJenisKelamin(),pilot.getTempatLahir(),pilot.getTanggalLahir()));
                PilotModel pilotUpdated=pilotService.updatePilot(pilot);
                model.addAttribute("nipPilot",pilotUpdated.getNip());
                return "ubah-done";
    }
    @RequestMapping("pilot/delete/{nip}")
    public String deletePilotSubmit(
            @PathVariable String nip){
                PilotModel pilot=pilotService.getPilotByNip(nip);
                pilotService.deletePilot(pilot);
                return "home";
    }


}
