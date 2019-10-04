package sample;

public abstract class Product implements Item {

  private int id;
  private String type;
  private String manufacturer;
  private String name;

  public Product(String name) {
    this.name = name;
  }

  //Still working on
  public void productToString() {
    System.out.println("Name: " + this.name);
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }
}
