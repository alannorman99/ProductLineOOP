package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  private String audioSpecification;
  private String mediaType;

  public AudioPlayer(String name, String manufacturer, String audioSpecification, String mediaType) {
    super(name, manufacturer, mediaType);
    this.audioSpecification = audioSpecification;
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
    return "AudioPlayer{" + super.toString() +
        "audioSpecification='" + audioSpecification + '\'' +
        ", mediaType='" + mediaType + '\'' +
        '}';
  }
}
