package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;

public interface PilotPenerbanganService {
    void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbanganModel);

    PilotPenerbanganModel getPilotPenerbanganByIdAkademi(Long id);
//    PilotPenerbanganModel getPilotPenerbanganById(Long id);
}
