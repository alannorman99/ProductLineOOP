package sample;

public class AudioPlayer extends Product implements MultimediaControl {

  private String supportedAudioFormats;
  private String supportedPlaylistFormats;

  /**
   * Constructor for basic AudioPlayer product object.
   * @param name name for the product
   * @param manufacturer manufacturer for the product
   * @param supportedAudioFormats supported audio formats for the device
   * @param supportedPlaylistFormats supported playlist formats for the device
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(ItemType.Audio, manufacturer, name);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopped");
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
    return "Name: " + super.getName() + "\n"
        + "Manufacturer: " + super.getManufacturer() + "\n"
        + "Type: " + "AUDIO" + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats + "\n"
        + "Supported Playlist Formats: " + supportedPlaylistFormats;

  }
}
