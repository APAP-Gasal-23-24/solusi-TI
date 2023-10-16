package apap.ti.silogistik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik.service.BarangService;
import apap.ti.silogistik.service.GudangService;
import apap.ti.silogistik.service.KaryawanService;
import apap.ti.silogistik.service.PermintaanPengirimanService;

@Controller
public class BaseController {

    @Autowired
    BarangService barangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    GudangService gudangService;

    @GetMapping("/")
    private String home(Model model){
        Integer numOfKaryawan = karyawanService.getKaryawanList().size();
        Integer numOfBarang = barangService.getBarangList().size();
        Integer numOfGudang = gudangService.getGudangList().size();
        Integer numOfPermintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanList().size();

        model.addAttribute("numOfKaryawan", numOfKaryawan);
        model.addAttribute("numOfBarang", numOfBarang);
        model.addAttribute("numOfGudang", numOfGudang);
        model.addAttribute("numOfPermintaanPengiriman", numOfPermintaanPengiriman);
        return "home";
    }
}
