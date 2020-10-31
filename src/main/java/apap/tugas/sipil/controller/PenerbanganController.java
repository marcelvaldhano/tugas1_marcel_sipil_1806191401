package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.repository.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@Controller
public class PenerbanganController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Autowired
    public PilotDb pilotDb;

    @Qualifier("maskapaiServiceImpl")
    @Autowired
    MaskapaiService maskapaiService;
    @Autowired
    public MaskapaiDb maskapaiDb;


    @Qualifier("akademiServiceImpl")
    @Autowired
    AkademisService akademisService;
    @Autowired
    public AkademiDb akademiDb;

    @Qualifier("penerbanganServiceImpl")
    @Autowired
    PenerbanganService penerbanganService;
    @Autowired
    public PenerbanganDb penerbanganDb;

    @Qualifier("pilotPenerbanganServiceImpl")
    @Autowired
    PilotPenerbanganService pilotPenerbanganService;
    @Autowired
    public PilotPenerbanganDb pilotPenerbanganDb;

    @GetMapping("/penerbangan")
    public String listPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan=penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "daftar-penerbangan";
    }

    @GetMapping("/penerbangan/detail/{id}")
    public String detailPenerbangan(
            @PathVariable Long id,
            Model model){
        PenerbanganModel listPenerbangan=penerbanganService.getPenerbanganById(id);

        List<PilotPenerbanganModel> listPilotPenerbangan=listPenerbangan.getPilotPenerbanganModel();
        model.addAttribute("listPilotPenerbangan",listPilotPenerbangan);
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listPilot", pilotService.getPilotList());
        return "detail-penerbangan";
    }

    @PostMapping("/penerbangan/{id}/pilot/tambah")
    public String addPilotPenerbangan(
            @PathVariable Long id, @RequestParam(name = "pilotModel", required = false) Long idPilot,
            Model model){
        PilotPenerbanganModel pilotPenerbanganModel=new PilotPenerbanganModel();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);
        pilotPenerbanganModel.setTanggalPenugasan(date);

        pilotPenerbanganModel.setPenerbanganModel(penerbanganService.getPenerbanganById(id));
        pilotPenerbanganModel.setPilotModel(pilotService.getPilotById(idPilot));


        pilotPenerbanganService.addPilotPenerbangan(pilotPenerbanganModel);
        model.addAttribute("pilotpenerbangan",pilotPenerbanganModel);
        return "add-pilot-penerbangan";
    }

    @GetMapping("penerbangan/tambah")
    public String addPenerbangan(Model model
    ){
        PenerbanganModel penerbangan=new PenerbanganModel();
        model.addAttribute("penerbangan",penerbangan);
        return "add-penerbangan";
    }

    @PostMapping("penerbangan/tambah")
    public String addPenerbanganSubmit(@ModelAttribute PenerbanganModel penerbangan,Model model){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kode",penerbangan.getKode());
        return "add-penerbangan-done";
    }
    
    @GetMapping("penerbangan/hapus/{id}")
    public String deletePilotSubmit(
            @PathVariable Long id, Model model){
        PenerbanganModel penerbangan=penerbanganService.getPenerbanganById(id);
        penerbanganService.deletePenerbangan(penerbangan);
        model.addAttribute("kode",penerbangan.getKode());
        return "delete-penerbangan";
    }

    @GetMapping("penerbangan/ubah/{id}")
    public String ubahPenerbangan(
            @PathVariable Long id, Model model
    ){
            PenerbanganModel penerbangan=penerbanganService.getPenerbanganById(id);
            model.addAttribute("penerbangan",penerbangan);
            return "ubah-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String ubahPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan, Model model){
        PenerbanganModel penerbanganUpdated=penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("kodePenerbangan",penerbanganUpdated.getKode());
        return "ubah-penerbangan-done";
    }


    @GetMapping("cari/pilot/")
    public String cari(Model model, @RequestParam(name = "kodeMaskapai", required = false) String kodeMaskapai,
                       @RequestParam(name = "idSekolah", required = false)  Long idSekolah
    ){
        boolean validasi=false;
        if(kodeMaskapai!=null && idSekolah!=null){
            validasi=true;
        }

        model.addAttribute("validasi",validasi);
        model.addAttribute("listResult", pilotService.getPilotByIdAkademiAndKodeMaskapai(kodeMaskapai,idSekolah));
        model.addAttribute("listMaskapai",maskapaiService.getMaskapaiList());
        model.addAttribute("listAkademi",akademisService.getAkademiList());

        return "cari-pilot";
    }

    @GetMapping("cari/pilot/penerbangan-terbanyak")
    public String caripilot(Model model, @RequestParam(name = "kodeMaskapai", required = false) String kodeMaskapai
    ){
        boolean validasi=false;
        if(kodeMaskapai!=null){
            validasi=true;
        }

        model.addAttribute("validasi",validasi);
        model.addAttribute("listMaskapai",maskapaiService.getMaskapaiList());

        return "cari-terbanyak";
    }




}
