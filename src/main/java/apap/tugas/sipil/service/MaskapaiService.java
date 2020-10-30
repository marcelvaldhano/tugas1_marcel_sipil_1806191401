package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.repository.MaskapaiDb;
import apap.tugas.sipil.model.PilotModel;
import java.util.List;
import java.util.Optional;

public interface MaskapaiService {
    public List<MaskapaiModel> getMaskapaiList();
}
