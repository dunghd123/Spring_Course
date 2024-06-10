package com.example.jpa_final.controller;

import com.example.jpa_final.model.HocVien;
import com.example.jpa_final.services.HocvienServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/hocvien")
public class HocvienController {
    @Autowired
    HocvienServices hvServices;

    @PostMapping(value = "themhocvien")
    public ResponseEntity<?>themHV(@RequestBody HocVien hocVien){
        if(hvServices.themHocVien(hocVien)){
            return ResponseEntity.ok("them thanh cong!!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("them khong thanh cong!!!");
    }
    @PutMapping(value = "suahocvien")
    public ResponseEntity<?>suaHV(@RequestBody HocVien hocVien){
        if(hvServices.suaHocVien(hocVien)){
            return ResponseEntity.ok("sua thanh cong hoc vien co id: "+hocVien.getHocvienID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua khong thanh cong hoc vien co id: "+hocVien.getHocvienID());
    }

    @DeleteMapping(value = "xoahocvien")
    public ResponseEntity<?> xoaHV(@RequestParam int hocvienid){
        if(hvServices.xoaHocVien(hocvienid)){
            return ResponseEntity.ok("xoa thanh cong hoc vien co id: "+hocvienid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai hoc vien co id: "+hocvienid);
    }
    @GetMapping(value = "hienthidanhsachhocvien")
    public List<HocVien> hienThiDsHv( @RequestParam int pagenum,@RequestParam int pagesize){
        return hvServices.hienThiDS(pagenum,pagesize);
    }
    @GetMapping(value = "timkiemtheoten")
    public List<HocVien> timKiemTheoTen(@RequestParam String ten, @RequestParam int pagenum,@RequestParam int pagesize){
        return hvServices.timKiemtheoTen(ten,pagenum,pagesize);
    }
    @GetMapping(value = "timkiemtheoemail")
    public HocVien timKiemTheoEmail(@RequestParam String email){
        return hvServices.timKiemTheoEmail(email);
    }
}
