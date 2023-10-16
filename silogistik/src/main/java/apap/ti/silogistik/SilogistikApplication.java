package apap.ti.silogistik;
import apap.ti.silogistik.model.GudangModel;
import apap.ti.silogistik.model.KaryawanModel;
import apap.ti.silogistik.service.GudangService;
import apap.ti.silogistik.service.KaryawanService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SilogistikApplication {

	public static void main(String[] args) {
		SpringApplication.run(SilogistikApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// create fake karyawan
			KaryawanModel karyawan = new KaryawanModel();
			String nama = faker.name().fullName();
			int jenisKelamin = faker.random().nextInt(0, 2);
			java.sql.Date startDate = java.sql.Date.valueOf("2001-01-01");
			java.sql.Date endDate = java.sql.Date.valueOf("2003-12-31");
			java.util.Date tanggalLahir = faker.date().between(startDate, endDate);
			java.sql.Date sqlDate = new java.sql.Date(tanggalLahir.getTime());
			karyawan.setNama(nama);
			karyawan.setJenisKelamin(jenisKelamin);
			karyawan.setTanggalLahir(sqlDate);
			karyawanService.addKaryawan(karyawan);

			// create fake gudang
			GudangModel gudang = new GudangModel();
			String namaGudang = faker.company().name();
			String alamatGudang = faker.address().city();
			gudang.setNama(namaGudang);
			gudang.setAlamatGudang(alamatGudang);
			gudangService.addGudang(gudang);
		};
	}
}
