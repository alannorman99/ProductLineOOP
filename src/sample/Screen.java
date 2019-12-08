package sample;

public class Screen implements ScreenSpec {

  //commenting out to avoid bug
  /*private String resolution;
  private int refreshrate;
  private int responsetime;*/

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
