package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "loaibaiviets")
@Getter
@Setter
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoaibaivietID;
    @Column(name = "tenloai")
    @Size(max = 50,message = "loai bai viet khong qua 50 ky tu")
    private String Tenloai;
    // lien ket voi bang Chude
    @OneToMany(mappedBy = "loaiBaiViet")
    @JsonIgnoreProperties(value = "loaiBaiViet")
    Set<ChuDe> chuDes;
}
