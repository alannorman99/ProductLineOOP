package sample;

public abstract class Product implements Item {

  private int id;
  private String type;
  private String manufacturer;
  private String name;

  public Product(String type, String manufacturer, String name) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  //Still working on

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", manufacturer='" + manufacturer + '\'' +
        ", name='" + name + '\'' +
        '}';
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
class Widget extends Product {

  public Widget(String type, String manufacturer, String name) {
    super(type, manufacturer, name);
  }
}
