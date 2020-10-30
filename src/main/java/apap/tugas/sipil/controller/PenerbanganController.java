package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.AkademiDb;
import apap.tugas.sipil.repository.MaskapaiDb;
import apap.tugas.sipil.repository.PenerbanganDb;
import apap.tugas.sipil.repository.PilotDb;
import apap.tugas.sipil.service.AkademisService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PenerbanganService;
import apap.tugas.sipil.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

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

    @GetMapping("/penerbangan")
    public String listPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan=penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "daftar-penerbangan";
    }

    @GetMapping("/penerbangan/detail/{id}")
    public String detailPenerbangan(
            @PathVariable String nip,
            Model model){
        List<PenerbanganModel> listPenerbangan=penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "daftar-penerbangan";
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

    @GetMapping("cari/pilot/")
    public String cari(Model model, @RequestParam(name = "kodeMaskapai", required = false) Long kodeMaskapai,
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


}
