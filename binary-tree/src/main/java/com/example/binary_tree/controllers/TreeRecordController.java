package com.example.binary_tree.controllers;

import com.example.binary_tree.additional_classes.BinarySearchTree;
import com.example.binary_tree.entities.TreeRecord;
import com.example.binary_tree.services.TreeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
@RequestMapping("/api")
public class TreeRecordController {

    @Autowired
    private TreeRecordService treeRecordService;

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestBody Map<String, String> payload) {
        String numbers = payload.get("numbers");
        System.out.println("Received numbers: " + numbers); // Логування отриманих даних

        List<Integer> numberList;
        try {
            numberList = Arrays.asList(numbers.split(","))
                    .stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Invalid number format";
        }

        BinarySearchTree bst = new BinarySearchTree();
        for (int number : numberList) {
            bst.insert(number);
        }

        String treeJson;
        try {
            treeJson = bst.toJson();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error converting tree to JSON";
        }

        treeRecordService.saveTreeRecord(numbers, treeJson);
        return treeJson;
    }

    @GetMapping("/previous-trees")
    public ResponseEntity<List<TreeRecord>> getPreviousTrees() {
        List<TreeRecord> treeRecords = treeRecordService.getAllTreeRecords();
        return ResponseEntity.ok(treeRecords);
    }
}
