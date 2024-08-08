package com.example.binary_tree.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tree_records")
public class TreeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long record_id;

    @Column(name = "numbers", nullable = false)
    private String numbers;

    @Column(name = "tree_json", nullable = false)
    private String tree_json;

    // Getters and Setters
    public Long getId() {
        return record_id;
    }

    public void setId(Long record_id) {
        this.record_id = record_id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getTreeJson() {
        return tree_json;
    }

    public void setTreeJson(String tree_json) {
        this.tree_json = tree_json;
    }
}
