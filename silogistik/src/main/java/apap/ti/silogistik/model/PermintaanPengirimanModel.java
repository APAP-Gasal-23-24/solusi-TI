package apap.ti.silogistik.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "permintaan_pengiriman")
@SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_cancelled = true WHERE id=?")
@Where(clause = "is_cancelled=false")
public class PermintaanPengirimanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nomorPengiriman;

    @NotNull
    @Column(nullable = false)
    private boolean isCancelled = Boolean.FALSE;

    @NotNull
    @Column(nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(nullable = false)
    private java.sql.Date tanggalPengiriman;

    @NotNull
    @Column(nullable = false)
    private int biayaPengiriman;

    @NotNull
    @Column(nullable = false)
    private int jenisLayanan;

    @NotNull
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime waktuPermintaan;

    @OneToMany(mappedBy = "permintaanPengiriman")
    Set<PermintaanPengirimanBarangModel> permintaanPengirimanBarang;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private KaryawanModel karyawan;
}
