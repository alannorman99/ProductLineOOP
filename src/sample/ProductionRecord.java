package sample;

import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;
  private String nameID;

  /**
   * Constructor for basic production record.
   * @param productID id for record being created
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  /**
   * Constructor to create record from an existing product.
   * @param product product for record production
   * @param count amount of records to create.
   */
  public ProductionRecord(Product product, int count) {
    this.serialNumber = (product.getManufacturer().substring(0, 3) + product.getType() + String
        .format("%05d", product.increment()));
    this.dateProduced = new Date();
  }

  /**
   * Constructor to create fully custom record.
   * @param productionNumber product number
   * @param productID product id
   * @param serialNumber product serial number
   * @param dateProduced date of production
   */
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date();
  }

  /**
   * Constructor for record with name instead of id.
   * @param productionNumber product number
   * @param nameID product name
   * @param serialNumber product serial number
   * @param dateProduced date of production
   */
  public ProductionRecord(int productionNumber, String nameID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.nameID = nameID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date();
  }

  public String getNameID() {
    return nameID;
  }


  int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date();
  }

  @Override
  public String toString() {
    return "Production Num: " + productionNumber
        + " Product ID:" + productID
        + " Serial Num:" + serialNumber
        + " Date:" + dateProduced + '\n';
  }
}
