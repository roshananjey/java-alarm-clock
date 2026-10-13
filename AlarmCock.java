
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AlarmCock implements Runnable {

    private final LocalTime alarmTime;

    public AlarmCock(LocalTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    @Override
    public void run() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = now.with(alarmTime);

        // If the time has passed, schedule it for tomorrow
        if (!target.isAfter(now)) {
            target = target.plusDays(1);
        }

        System.out.println("Waiting for alarm...");

        while (true) {
            now = LocalDateTime.now();

            if (!now.isBefore(target)) {
                break;
            }

            try {
                Thread.sleep(1000);

                LocalTime current = LocalTime.now();

                System.out.printf(
                    "\r%02d:%02d:%02d",
                    current.getHour(),
                    current.getMinute(),
                    current.getSecond()
                );

            } catch (InterruptedException e) {
                System.out.println("\nAlarm thread stopped.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("\n⏰ ALARM! Wake up!");
        System.out.println("Alarm noise!");
    }
}
