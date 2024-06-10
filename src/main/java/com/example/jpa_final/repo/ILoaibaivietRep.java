package com.example.jpa_final.repo;

import com.example.jpa_final.model.LoaiBaiViet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoaibaivietRep extends JpaRepository<LoaiBaiViet,Integer> {
    @Query(value = "select * from loaibaiviets",nativeQuery = true)
    public List<LoaiBaiViet> findAllBy(Pageable pageable);
}
