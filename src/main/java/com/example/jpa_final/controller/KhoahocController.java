package com.example.jpa_final.controller;

import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.services.KhoahocServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/khoahoc")
public class KhoahocController {
    @Autowired
    KhoahocServices khServices;

    @PostMapping(value = "themkhoahoc")
    public ResponseEntity<?> themKH(@RequestBody KhoaHoc khoaHoc){
        if(khServices.themKhoahoc(khoaHoc)){
            return ResponseEntity.ok("them thanh cong!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Them that bai!!!");
    }
    @PutMapping(value = "suakhoahoc")
    public ResponseEntity<?> suaKH(@RequestBody KhoaHoc khoaHoc){
        if(khServices.suaKhoaHoc(khoaHoc)){
            return ResponseEntity.ok("sua thanh cong khoa hoc co id: "+khoaHoc.getKhoahocID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua that bai khoa hoc co id: "+khoaHoc.getKhoahocID());
    }
    @DeleteMapping(value = "xoakhoahoc")
    public ResponseEntity<?> xoaKH(@RequestParam int khid){
        if(khServices.xoaKhoaHoc(khid)){
            return ResponseEntity.ok("xoa thanh cong khoa hoc co id: "+khid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai khoa hoc co id: "+khid);
    }
    @GetMapping(value = "hienthidanhsachkhoahoc")
    public List<KhoaHoc> hienThiDS(@RequestParam int pagenum, @RequestParam int pagesize){
        return khServices.hienthiDS(pagenum,pagesize);
    }
    @GetMapping(value = "timkiemtheotenkhoahoc")
        public List<KhoaHoc> timKiemTheoTenKH(@RequestParam String tenkh, @RequestParam int pagenum,@RequestParam int pagesize){
        return khServices.timkiemtheoTenKH(tenkh,pagenum,pagesize);
    }


}
