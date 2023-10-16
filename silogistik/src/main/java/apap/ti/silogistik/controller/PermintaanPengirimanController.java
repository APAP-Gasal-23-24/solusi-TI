package apap.ti.silogistik.controller;

import apap.ti.silogistik.model.BarangModel;
import apap.ti.silogistik.model.PermintaanPengirimanBarangModel;
import apap.ti.silogistik.model.PermintaanPengirimanModel;
import apap.ti.silogistik.model.KaryawanModel;
import apap.ti.silogistik.service.BarangService;
import apap.ti.silogistik.service.PermintaanPengirimanService;
import apap.ti.silogistik.service.KaryawanService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik.dto.PPBarangDTO;
import apap.ti.silogistik.service.PermintaanPengirimanBarangService;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    BarangService barangService;

    //view-all permintaan pengiriman

    @GetMapping("/permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model){
        List<PermintaanPengirimanModel> listPermintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanList();
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        return "view-all-permintaan-pengiriman";
    }

    //add permintaan pengirman
    @GetMapping("/permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model){
        List<KaryawanModel> listKaryawan = karyawanService.getKaryawanList();
        List<BarangModel> listBarang = barangService.getBarangList();
        PPBarangDTO dto = new PPBarangDTO();

        HashMap<Integer,String> listJenisLayanan = new HashMap<>();
        listJenisLayanan.put(1,"Same Day");
        listJenisLayanan.put(2,"Kilat");
        listJenisLayanan.put(3,"Reguler");
        listJenisLayanan.put(4,"Hemat");
        dto.setListPPBarang(new ArrayList<>());
        model.addAttribute("dto", dto);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listJenisLayanan", listJenisLayanan);
        return "form-add-permintaan-pengiriman";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowTambahPP(Model model, @ModelAttribute PPBarangDTO dto){
        List<KaryawanModel> listKaryawan = karyawanService.getKaryawanList();
        List<BarangModel> listBarang = barangService.getBarangList();
        HashMap<Integer,String> listJenisLayanan = new HashMap<>();
        listJenisLayanan.put(1,"Same Day");
        listJenisLayanan.put(2,"Kilat");
        listJenisLayanan.put(3,"Reguler");
        listJenisLayanan.put(4,"Hemat");
        if(dto.getListPPBarang() == null || dto.getListPPBarang().isEmpty()){
            dto.setListPPBarang(new ArrayList<>());
            dto.getListPPBarang().add(new PermintaanPengirimanBarangModel());
        }else{
            dto.getListPPBarang().add(new PermintaanPengirimanBarangModel());
        }
        model.addAttribute("dto", dto);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listJenisLayanan", listJenisLayanan);

        return "form-add-permintaan-pengiriman";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"deleteRow"})
    public String deleteRowTambahPP(Model model, @ModelAttribute PPBarangDTO dto, @RequestParam("deleteRow") int row){

        List<BarangModel> listBarang = barangService.getBarangList();
        List<KaryawanModel> listKaryawan = karyawanService.getKaryawanList();
        HashMap<Integer,String> listJenisLayanan = new HashMap<>();
        listJenisLayanan.put(1,"Same Day");
        listJenisLayanan.put(2,"Kilat");
        listJenisLayanan.put(3,"Reguler");
        listJenisLayanan.put(4,"Hemat");

        dto.getListPPBarang().remove(row);
        model.addAttribute("dto", dto);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listJenisLayanan", listJenisLayanan);

        return "form-add-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String addPermintaanPengirimanSubmit(
            @ModelAttribute PPBarangDTO ppBarangDTO,
            Model model) {

        StringBuilder noPengiriman = new StringBuilder("REQ");
        
        // implement stok in total pengiriman
        int totalKuantitasPermintaan = 0;
        for (PermintaanPengirimanBarangModel x: ppBarangDTO.getListPPBarang()){
            totalKuantitasPermintaan+=x.getKuantitasPermintaan();
        }

        if(totalKuantitasPermintaan%100>9){
            noPengiriman.append(totalKuantitasPermintaan%100);
        } else {
            noPengiriman.append("0"+totalKuantitasPermintaan%100);
        }

        switch (ppBarangDTO.getPermintaanPengiriman().getJenisLayanan()) {
            case 1 -> noPengiriman.append("SAM");
            case 2 -> noPengiriman.append("KIL");
            case 3 -> noPengiriman.append("REG");
            case 4 -> noPengiriman.append("HEM");
        }
        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Define a custom time format (hour:minute:second)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format and print the current time
        String formattedTime = currentTime.format(formatter);

        noPengiriman.append(formattedTime);
        ppBarangDTO.getPermintaanPengiriman().setNomorPengiriman(noPengiriman.toString());
        ppBarangDTO.getPermintaanPengiriman().setWaktuPermintaan(LocalDateTime.now());
        // create hashmap to avoid duplicate
        HashMap<String, Integer> inputPermintaanPengiriman = new HashMap<>();

        for (PermintaanPengirimanBarangModel x : ppBarangDTO.getListPPBarang()){
            
            if (inputPermintaanPengiriman.containsKey(x.getBarang2().getSku())){
                inputPermintaanPengiriman.put(x.getBarang2().getSku(), inputPermintaanPengiriman.get(x.getBarang2().getSku())+x.getKuantitasPermintaan());
            }
            else{
                inputPermintaanPengiriman.put(x.getBarang2().getSku(), x.getKuantitasPermintaan());
            }
        }

        PermintaanPengirimanModel savedEntity = permintaanPengirimanService.addPermintaanPengiriman(ppBarangDTO.getPermintaanPengiriman());

        // iterate over hashmap then insert to db
        inputPermintaanPengiriman.forEach((key, value) -> {
            BarangModel barang = barangService.getBarangBySku(key);
            PermintaanPengirimanBarangModel baru = new PermintaanPengirimanBarangModel();
            baru.setPermintaanPengiriman(savedEntity);
            baru.setBarang2(barang);
            baru.setKuantitasPermintaan(value);
            permintaanPengirimanBarangService.addPermintaanPengirimanBarang(baru);
        });

        model.addAttribute("nomorPengiriman", ppBarangDTO.getPermintaanPengiriman().getNomorPengiriman());
        return "feedback-add-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/delete/{idPermintaanPengiriman}")
    public String deletePermintaanPengiriman(
            @PathVariable Long idPermintaanPengiriman,
            Model model
    ){
        PermintaanPengirimanModel permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        permintaanPengirimanService.deletePermintaanPengiriman(permintaanPengiriman);
        model.addAttribute("permintaanpengiriman", permintaanPengiriman);
        model.addAttribute("idpermintaanpengiriman", idPermintaanPengiriman);
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        return "feedback-success-delete";
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(
            @PathVariable Long idPermintaanPengiriman,
            Model model
    ){
        PermintaanPengirimanModel permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        Set <PermintaanPengirimanBarangModel> listBarang = permintaanPengiriman.getPermintaanPengirimanBarang();

        // Define a custom time format (hour:minute:second)

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format and print the current time
        String formattedTime = permintaanPengiriman.getWaktuPermintaan().format(formatter);
        model.addAttribute("formattedTime", formattedTime);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "detail-permintaan-pengiriman";
    }

    @GetMapping("/filter-permintaan-pengiriman")
    private String bonus(Model model,
                         @RequestParam(value = "skuBarang", required = false) String skuBarang,
                         @RequestParam(value = "startDate", required = false) String startDate,
                         @RequestParam(value = "endDate", required = false) String endDate){

        List<BarangModel> listBarang= barangService.getBarangList();
        List<PermintaanPengirimanModel> permintaanPengirimanFinal = new ArrayList<PermintaanPengirimanModel>();

        boolean isActive = false;

        if (skuBarang!=null && startDate!=null && endDate!=null) {
            isActive = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDateLD = LocalDate.parse(startDate, formatter);
            LocalDate endDateLD = LocalDate.parse(endDate, formatter);

            LocalDateTime startDateTime = startDateLD.atStartOfDay();
            LocalDateTime endDateTime = endDateLD.atTime(LocalTime.MAX);

            List<PermintaanPengirimanModel> permintaanPengirimanByPeriod = permintaanPengirimanService.getPermintaanPengirimanByPeriod(startDateTime,endDateTime);
            for (PermintaanPengirimanModel pp: permintaanPengirimanByPeriod
            ) {
                for (PermintaanPengirimanBarangModel ppb: pp.getPermintaanPengirimanBarang()
                ) {
                    if (ppb.getBarang2().getSku().equals(skuBarang)){
                        permintaanPengirimanFinal.add(pp);
                    }
                }
            }
        }

        model.addAttribute("isFilterActive", isActive);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanFinal", permintaanPengirimanFinal);
        return "view-filtered-permintaan-pengiriman";
    }
}
