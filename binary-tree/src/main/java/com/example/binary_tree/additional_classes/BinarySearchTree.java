package com.example.binary_tree.additional_classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinarySearchTree {
    private TreeNode root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }

        if (value < node.getValue()) {
            node.setLeft(insertRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRecursive(node.getRight(), value));
        }

        return node;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
