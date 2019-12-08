package sample;

enum ItemType {
  Audio("AU"),
  Visual("VI"), AudioMobile("AM"), VideoMobile("VM");

  public String itemType;

  // enum constructor
  ItemType(String newType) {
    this.itemType = newType;
  }

  // getter method
  public String getItemType() {
    return itemType;
  }

}

