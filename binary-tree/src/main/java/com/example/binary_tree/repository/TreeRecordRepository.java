package com.example.binary_tree.repository;

import com.example.binary_tree.entities.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
