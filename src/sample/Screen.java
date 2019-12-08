package sample;

public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshrate;
  private int responsetime;

  /**
   * Constructor for basic screen.
   * @param resolution screen resolution
   * @param refreshrate screen refresh rate
   * @param responsetime screent response time
   */
  public Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

  @Override
  public String getResolution() {
    return null;
  }

  @Override
  public int getRefreshRate() {
    return 0;
  }

  @Override
  public int getResponseTime() {
    return 0;
  }

  //todo: finish toString to match Product class
  @Override
  public String toString() {
    return "Screen{}";
  }
}
