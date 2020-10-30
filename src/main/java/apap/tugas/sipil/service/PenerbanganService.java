package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PenerbanganModel;

import java.util.List;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
    void deletePenerbangan(PenerbanganModel penerbangan);
    List<PenerbanganModel> getPenerbanganList();

}
