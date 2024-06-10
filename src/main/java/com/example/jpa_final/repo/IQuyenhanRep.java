package com.example.jpa_final.repo;

import com.example.jpa_final.model.LoaiBaiViet;
import com.example.jpa_final.model.QuyenHan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuyenhanRep extends JpaRepository<QuyenHan,Integer> {
    @Query(value = "select * from quyenhans",nativeQuery = true)
    public List<QuyenHan> findAllBy(Pageable pageable);
}
