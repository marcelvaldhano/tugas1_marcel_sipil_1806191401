package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull
    @Size(max = 16)
    @Column(name = "kode", nullable = false , unique = true)
    private String kode;


    @NotNull
    @Size(max = 255)
    @Column(name = "kota_asal", nullable = false)
    private String kotaAsal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kota_tujuan", nullable = false)
    private String kotaTujuan;

    @NotNull
    @Column(name = "waktu", nullable = false)
    private String waktu;

    @OneToMany(mappedBy = "penerbanganModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PilotPenerbanganModel> pilotPenerbanganModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public List<PilotPenerbanganModel> getPilotPenerbanganModel() {
        return pilotPenerbanganModel;
    }

    public void setPilotPenerbanganModel(List<PilotPenerbanganModel> pilotPenerbanganModel) {
        this.pilotPenerbanganModel = pilotPenerbanganModel;
    }
}
