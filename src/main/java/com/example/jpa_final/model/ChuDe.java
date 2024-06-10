package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "chudes")
@Getter
@Setter
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ChudeID;
    @Column(name = "tenchude")
    @Size(max = 50,message = "ten chu de khong qua 50 ky tu!")
    private String Tenchude;
    @Column(name = "noidung")
    private String Noidung;

    //khoa ngoai den bang Loaibaiviet
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaibaivietid",foreignKey = @ForeignKey(name = "FK_Chude_Loaibaiviet"))
    @JsonIgnoreProperties(value = "chuDes")
    LoaiBaiViet loaiBaiViet;

    //lien ket den bang bai viet
    @OneToMany(mappedBy = "chuDe")
    @JsonIgnoreProperties(value = "chuDe")
    Set<BaiViet> baiViets;

}
