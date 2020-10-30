package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="pilot")
public class PilotModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;


    @NotNull
    @Size(max = 13)
    @Column(name = "nip", nullable = false , unique = true)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nik", nullable = false , unique = false)
    private String nik;

    @NotNull
    @Size(max= 20)
    @Column(name = "tanggal_lahir", nullable = false)
    private String tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false , targetEntity = MaskapaiModel.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_maskapai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MaskapaiModel maskapaiModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false , targetEntity = AkademiModel.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_akademi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AkademiModel akademiModel;

    @OneToMany(mappedBy = "pilotModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PilotPenerbanganModel> pilotPenerbanganModel;

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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setNip(String nip){
        this.nip=nip;
    }

    public String getNip(){return this.nip=nip;}

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public List<PilotPenerbanganModel> getPilotPenerbanganModel() {
        return pilotPenerbanganModel;
    }

    public void setPilotPenerbanganModel(List<PilotPenerbanganModel> pilotPenerbanganModel) {
        this.pilotPenerbanganModel = pilotPenerbanganModel;
    }

    public MaskapaiModel getMaskapaiModel() {
        return maskapaiModel;
    }

    public void setMaskapaiModel(MaskapaiModel maskapaiModel) {
        this.maskapaiModel = maskapaiModel;
    }

    public AkademiModel getAkademiModel() {
        return akademiModel;
    }

    public void setAkademiModel(AkademiModel akademiModel) {
        this.akademiModel = akademiModel;
    }
}
