package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList() {
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id){
        return penerbanganDb.findById(id).get();
    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan){
        return penerbanganDb.save(penerbangan);
    }
}
