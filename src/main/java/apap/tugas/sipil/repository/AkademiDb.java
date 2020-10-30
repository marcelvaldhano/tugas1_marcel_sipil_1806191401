package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.AkademiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkademiDb extends JpaRepository<AkademiModel,Long> {

}
