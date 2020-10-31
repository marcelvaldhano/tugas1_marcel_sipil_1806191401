package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
    @Autowired
    PilotDb pilotDb;
    @Override
    public void addPilot(PilotModel pilot){
        pilotDb.save(pilot);
    }

    @Override
    public void deletePilot(PilotModel pilot) {
        pilotDb.delete(pilot);
    }

    @Override
    public List<PilotModel>getPilotList(){return pilotDb.findAll();}

    @Override
    public PilotModel getPilotByNip(String nip){
        return pilotDb.findByNip(nip).get();
    }

    @Override
    public PilotModel getPilotById(Long id){
        return pilotDb.findById(id).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot){
        return pilotDb.save(pilot);
    }

    @Override
    public List<PilotModel> getPilotByIdAkademiAndKodeMaskapai(Long kodeMaskapai, Long idSekolah){
        List<PilotModel> hasil=new ArrayList<PilotModel>();
        for(PilotModel pilot:getPilotList()){
            if(pilot.getMaskapaiModel().getKode().equals(kodeMaskapai)||pilot.getAkademiModel().getId().equals(idSekolah)){
                hasil.add(pilot);
            }
        }
        return hasil;
    }

    @Override
    public String generatenip(String nama, Integer jenisKelamin, String tempatlahir, String tanggalLahir) {
        Random random=new Random();
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder random_string=new StringBuilder(13);

        //1 digit
        random_string.append(tanggalLahir.split("/")[0]);
        //4 digit
        random_string.append(nama.substring(nama.length()-2,nama.length()).toUpperCase());
        //5 digit
        random_string.append(jenisKelamin);
        //6 digit
        random_string.append(tanggalLahir.split("/")[1]);
        //7,8 dan 9 digit
        random_string.append(tempatlahir.substring(2,3).toUpperCase());
        char randomword=alphabet.charAt(random.nextInt(alphabet.length()));
        char randomword2=alphabet.charAt(random.nextInt(alphabet.length()));
        char randomword3=alphabet.charAt(random.nextInt(alphabet.length()));
        random_string.append(randomword3);
        //10 dan 11 digit
        random_string.append(randomword2);
        random_string.append(randomword);
        //12 dan 13 digit
        DecimalFormat desimal=new DecimalFormat("##");
        random_string.append(desimal.format(Integer.parseInt(tanggalLahir.split("/")[2])/100));
        //convert SB to String
        String result=random_string.toString();
        return result;
    }

}
