package AlarmclockProject;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{
    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;

     AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath=filePath;
        this.scanner=scanner;
    }

    @Override
    public void run() {
         if (LocalTime.now().isAfter(alarmTime)){
             System.out.println("Ab kuch nahi ho skta bhai ");
         }

        while (LocalTime.now().isBefore(alarmTime)) {

            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();
                 System.out.printf("\rCurrent Time: %02d:%02d:%02d",now.getHour()
                                                                 ,now.getMinute()
                                                                 ,now.getSecond());
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");

            }
        }
        System.out.println("\n***Alarm Baj Gaya***");
      playSound(filePath);
     }
     private  void playSound(String filePath)  {
         File audioFile = new File(filePath);

         try( AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)){
             Clip clip = AudioSystem.getClip();
             clip.open(audioStream);
             clip.start();
             System.out.println("Press Enter to stop the Alarm");
             scanner.nextLine();
             clip.stop();
             scanner.close();




         }
         catch (UnsupportedAudioFileException e){
             System.out.println("Audio file is not supported");
         }
         catch (IOException e){
             System.out.println("Speaker kaam ni kra tera me  to bja tha ");

         } catch (LineUnavailableException e) {
             System.out.println("Pehle set to kr gana ");
         }

     }
    }

