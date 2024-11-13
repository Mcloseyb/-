package org.example.login.entity;

public class OperatorTokenDTO {
    private String operatorName;
    private int quantity;
    private int rarity;

    public OperatorTokenDTO(String operatorName, int quantity, int rarity) {
        this.operatorName = operatorName;
        this.quantity = quantity;
        this.rarity = rarity;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
}
