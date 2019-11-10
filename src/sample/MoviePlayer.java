package sample;

public class MoviePlayer extends Product implements MultimediaControl {

  private String screen;
  private MonitorType monitorType;

  public MoviePlayer(String manufacturer, String name, String screen,
      MonitorType monitorType) {
    super(ItemType.Visual, manufacturer, name);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  @Override
  public String toString() {
    return super.toString() + "\n" +
        "screen: " + screen + "\n" +
        "monitorType: " + monitorType;
  }
}
