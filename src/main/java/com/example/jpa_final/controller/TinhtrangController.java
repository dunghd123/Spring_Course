package com.example.jpa_final.controller;

import com.example.jpa_final.model.TinhTrangHoc;
import com.example.jpa_final.services.TinhtranghocServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/tinhtranghoc")
public class TinhtrangController {
    @Autowired
    TinhtranghocServices tthServices;
    @PostMapping(value = "themtinhtranghoc")
    public ResponseEntity<?> themTTH(@RequestBody TinhTrangHoc tinhTrangHoc){
        if(tthServices.themTinhTrangHoc(tinhTrangHoc)){
            return ResponseEntity.ok("them thanh cong!!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("them khong thanh cong!!!");
    }
    @PutMapping(value = "suatinhtranghoc")
    public ResponseEntity<?> suaTTH(@RequestBody TinhTrangHoc tinhTrangHoc){
        if(tthServices.suaTinhTrangHoc(tinhTrangHoc)){
            return ResponseEntity.ok("sua thanh cong tinh trang co id: "+tinhTrangHoc.getTinhtranghocID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua khong thanh cong tinh trang co id: "+tinhTrangHoc.getTinhtranghocID());
    }
    @DeleteMapping(value = "xoatinhtranghoc")
    public ResponseEntity<?> xoaTTH(@RequestParam int tthid){
        if(tthServices.xoaTinhTrangHoc(tthid)){
            return ResponseEntity.ok("xoa thanh cong tinh trang co id: "+ tthid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai tinh trang co id: "+tthid);
    }
    @GetMapping(value = "hienthidanhsachtinhtranghoc")
    public List<TinhTrangHoc> hienThiDSTTH(@RequestParam int pagenum,@RequestParam int pagesize){
        return tthServices.hienThiDS(pagenum,pagesize);
    }
}
