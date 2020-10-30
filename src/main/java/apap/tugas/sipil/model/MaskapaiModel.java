package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="maskapai")
public class MaskapaiModel implements Serializable{
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;


    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false)
    private String kode;


    @OneToMany(mappedBy = "maskapaiModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PilotModel> pilotModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setPilotModel(List<PilotModel> pilotModel) {
        this.pilotModel = pilotModel;
    }

    public List<PilotModel> getPilotModel() {
        return pilotModel;
    }

}
