package com.noir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ingredient {
    private String id;
    private String name;
    private Integer stock;
    private String unit;
    private Boolean available;
    private String notes;
    private Integer lowStockThreshold;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Integer getLowStockThreshold() { return lowStockThreshold; }
    public void setLowStockThreshold(Integer lowStockThreshold) { this.lowStockThreshold = lowStockThreshold; }

    @JsonIgnore // ✅ IMPORTANT
    public boolean isLowStock() {
        if (stock == null) return false;
        int threshold = lowStockThreshold != null ? lowStockThreshold : 5;
        return stock <= threshold;
    }
}