package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.AkademiDb;
import apap.tugas.sipil.repository.PilotDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AkademiServiceImpl implements AkademisService{
    @Autowired
    AkademiDb akademiDb;

    @Override
    public List<AkademiModel> getAkademiList(){
        return akademiDb.findAll();
    };
}
