package io.github.sashirestela.openai.demo;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import io.github.sashirestela.openai.domain.audio.AudioResponse;
import io.github.sashirestela.openai.domain.audio.AudioRespFmt;
import io.github.sashirestela.openai.domain.audio.AudioTranscribeRequest;
import io.github.sashirestela.openai.domain.audio.AudioTranslateRequest;

public class AudioServiceDemo extends AbstractDemo {

  private String fileName;

  public AudioServiceDemo() {
    this.fileName = "src/demo/resources/hello_audio.mp3";
  }

  public void demoCallAudioTranscription() {
    AudioTranscribeRequest audioRequest = AudioTranscribeRequest.builder()
        .file(new File(fileName))
        .model("whisper-1")
        .responseFormat(AudioRespFmt.VERBOSE_JSON)
        .build();
    CompletableFuture<AudioResponse> futureAudio = openAI.audios().transcribe(audioRequest);
    AudioResponse audioResponse = futureAudio.join();
    System.out.println(audioResponse.getText());
  }

  public void demoCallAudioTranslation() {
    AudioTranslateRequest audioRequest = AudioTranslateRequest.builder()
        .file(new File(fileName))
        .model("whisper-1")
        .responseFormat(AudioRespFmt.VERBOSE_JSON)
        .build();
    CompletableFuture<AudioResponse> futureAudio = openAI.audios().translate(audioRequest);
    AudioResponse audioResponse = futureAudio.join();
    System.out.println(audioResponse.getText());
  }

  public static void main(String[] args) {
    AudioServiceDemo demo = new AudioServiceDemo();

    demo.addTitleAction("Call Audio Transcription", () -> demo.demoCallAudioTranscription());
    demo.addTitleAction("Call Audio Translation", () -> demo.demoCallAudioTranslation());

    demo.run();
  }
}