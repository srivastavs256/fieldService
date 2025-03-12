package com.mini.fieldServices;

public class stockHelperClass {

String productName,weight,harvestDate,expiryDate,slotNo;

    public stockHelperClass(String productName, String weight, String harvestDate, String expiryDate, String slotNo) {
        this.productName = productName;
        this.weight = weight;
        this.harvestDate = harvestDate;
        this.expiryDate = expiryDate;
        this.slotNo = slotNo;
    }

    public stockHelperClass() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }
}
