package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "dangkyhocs")
@Getter
@Setter
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "ngaydangky")
    private Date Ngaydangky;
    @Column(name = "ngaybatdau")
    private Date Ngaybatdau;
    @Column(name = "ngayketthuc")
    private Date Ngayketthuc;

    //khoa ngoai den bang khoahoc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "khoahocid",foreignKey = @ForeignKey(name = "FK_Dangky_Khoahoc"))
    @JsonIgnoreProperties(value = "dangKyHocs")
    KhoaHoc khoaHoc;
    //khoa ngoai den bang khoahoc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hocvienid",foreignKey = @ForeignKey(name = "FK_Dangky_Hocvien"))
    @JsonIgnoreProperties(value = "dangKyHocs")
    HocVien hocVien;
    //khoa ngoai den bang Tinhtranghoc
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tinhtranghocid",foreignKey = @ForeignKey(name = "FK_Dangky_Tinhtranghoc"))
    @JsonIgnoreProperties(value = "dangKyHocs")
    TinhTrangHoc tinhTrangHoc;
    //khoa ngoai den bang Taikhoan
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taikhoanid",foreignKey = @ForeignKey(name = "FK_Dangky_Taikhoan"))
    @JsonIgnoreProperties(value = "dangKyHocs")
    TaiKhoan taiKhoan;

}
