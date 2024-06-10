package com.example.jpa_final.controller;

import com.example.jpa_final.model.LoaiKhoaHoc;
import com.example.jpa_final.services.LoaiKhoahocServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/loaikhoahoc")
public class LoaiKhController {
    @Autowired
    LoaiKhoahocServices lkhServices;
    @PostMapping(value = "themloaikhoahoc")
    public void themLKH(@RequestBody LoaiKhoaHoc loaiKhoaHoc){
        lkhServices.themLoaikhoahoc(loaiKhoaHoc);
    }
    @PutMapping(value = "sualoaikhoahoc")
    public void suaLKH(@RequestBody LoaiKhoaHoc loaiKhoaHoc){
        lkhServices.suaLoaiKhoaHoc(loaiKhoaHoc);
    }
    @DeleteMapping(value = "xoaloaikhoahoc")
    public ResponseEntity<?> xoaLKH(@RequestParam int loaikhID){
        if(lkhServices.xoaLoaiKhoaHoc(loaikhID)){
            return ResponseEntity.ok("xoa thanh cong loai khoa hoc co ma: "+loaikhID);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai loai khoa hoc co ma: "+loaikhID);
    }
}
