package apap.tugas.sipil.service;


import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.repository.PilotDb;
import apap.tugas.sipil.repository.PilotPenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements PilotPenerbanganService{
    @Autowired
    PilotPenerbanganDb pilotPenerbanganDb;


    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbanganModel){
        pilotPenerbanganDb.save(pilotPenerbanganModel);
    }

    public PilotPenerbanganModel getPilotPenerbanganByIdAkademi(Long id){
        return pilotPenerbanganDb.findById(id).get();
    }
//    @Override
//    public PilotPenerbanganModel getPilotPenerbanganById(Long id){
//        return pilotPenerbanganDb.f;
//    }
}
