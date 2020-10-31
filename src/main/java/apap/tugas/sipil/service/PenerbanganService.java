package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;

import java.util.List;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
    void deletePenerbangan(PenerbanganModel penerbangan);
    List<PenerbanganModel> getPenerbanganList();

    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel getPenerbanganById(Long id);



}
