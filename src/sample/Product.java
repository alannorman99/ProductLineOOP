package sample;

public abstract class Product implements Item {

  private int id;
  private ItemType type;

  private String manufacturer;
  private String name;


  /**
   * Product constructor used by database.
   *
   * @param type         products type
   * @param manufacturer products manufacturer
   * @param name         products name
   */
  public Product(ItemType type, String manufacturer, String name) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
    globalProducts++;
  }

  //Still working on

  @Override
  public String toString() {
    return "Product ID: " + id + "\n"
        + "Product Name: " + name + "\n"
        + "Manufacturer: " + manufacturer
        + "\n" + "Type: " + type + "\n" + "\n";
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

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  private int globalProducts = -1; // Defined outside the scope of the method

  /**
   * method to increment global products.
   *
   * @return adds one to global products
   */

  int increment() {
    globalProducts++; // Increment the value by 1
    return globalProducts; // Return the value of which you currently hold
    // return persistedValue++;
  }
}
