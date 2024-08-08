package com.example.binary_tree.services;

import com.example.binary_tree.entities.TreeRecord;
import com.example.binary_tree.repository.TreeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeRecordService {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    public TreeRecord saveTreeRecord(String numbers, String treeJson) {
        TreeRecord record = new TreeRecord();
        record.setNumbers(numbers);
        record.setTreeJson(treeJson);
        return treeRecordRepository.save(record);
    }

    public List<TreeRecord> getAllTreeRecords() {
        return treeRecordRepository.findAll();
    }
}
