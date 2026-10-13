
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class alarmclock {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;

    while(alarmTime == null){
       try {
        System.out.print("Enter an Alarm time (HH:mm:ss): ");
        String inputTime = sc.nextLine();

        alarmTime = LocalTime.parse(inputTime, formatter);
        System.out.println("Alarm Set: " + alarmTime);

           
       } catch (DateTimeParseException e) {
        System.out.println("Invalid , use HH:MM:SS");
       }
    }

        AlarmCock alarmCock = new AlarmCock(alarmTime);
        Thread alarmThread = new Thread(alarmCock);
        alarmThread.start();
        sc.close();
    }
}
