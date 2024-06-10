package com.example.jpa_final.repo;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.HocVien;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHocvienRep extends JpaRepository<HocVien,Integer> {
    @Query(value = "select * from hocviens",nativeQuery = true)
    public List<HocVien> findAllBy(Pageable pageable);
    @Query(value = "select * from hocviens hv where hv.hoten like %?1",nativeQuery = true)
    public List<HocVien> findAllByHotenIsContainingIgnoreCase(String tenbv,Pageable pageable);
    @Query(value = "select * from hocviens hv where hv.email like ?1",nativeQuery = true)
    public HocVien findByEmailEquals(String email);
}
