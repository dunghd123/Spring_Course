package com.example.jpa_final.controller;

import com.example.jpa_final.model.LoaiBaiViet;
import com.example.jpa_final.services.LoaibaivietServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/loaibaiviet")
public class LoaibvController {
    @Autowired
    LoaibaivietServices loaibvServices;

    @PostMapping(value = "themloaibaiviet")
    public void themLBV(@RequestBody LoaiBaiViet loaiBaiViet){
        loaibvServices.themLoaiBaiViet(loaiBaiViet);
    }
    @PutMapping(value = "sualoaibaiviet")
    public ResponseEntity<?> suaLBV(@RequestBody LoaiBaiViet loaiBaiViet){
        if (loaibvServices.suaLoaiBaiViet(loaiBaiViet)){
               return ResponseEntity.ok("sua thanh cong loai bai viet co id: "+loaiBaiViet.getLoaibaivietID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai loai bai viet co id: "+loaiBaiViet.getLoaibaivietID());
    }
    @DeleteMapping(value = "xoaloaibaiviet")
    public ResponseEntity<?> xoaLBV(@RequestParam int loaibaivietid){
        if(loaibvServices.xoaLoaiBaiViet(loaibaivietid)){
            return ResponseEntity.ok("xoa thanh cong loai bai viet co ma: "+loaibaivietid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai loai bai viet co ma: "+loaibaivietid);
    }
    @GetMapping(value = "hienthiloaibaiviet")
    public ResponseEntity<?> hienThiLBV(@RequestParam  int pagenumber, @RequestParam int pagesize){
        return ResponseEntity.ok(loaibvServices.hienThiLBV(pagenumber,pagesize));
    }
}
