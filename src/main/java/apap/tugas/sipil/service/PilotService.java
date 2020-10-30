package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotModel;
import java.util.List;
import java.util.Optional;

public interface PilotService {
    void addPilot(PilotModel pilot);

    void deletePilot(PilotModel pilot);
    public List<PilotModel> getPilotByIdAkademiAndKodeMaskapai(Long kodeMaskapai, Long idSekolah);

    PilotModel updatePilot(PilotModel pilot);
    PilotModel getPilotByNip(String nip);
    List<PilotModel> getPilotList();
    public String generatenip(String nama, Integer jenisKelamin, String tempatlahir, String tanggalLahir);
}
