//Noa Levi-214506396, Hila Ivgi- 326138377
package race;

public class Racer implements Runnable {

	private static int globalId = 1;

    private int id;
    private int speed;
    private Track track;

    public Racer(int speed, Track track) {
        if (speed < 1 || speed > 10) {
            System.out.println("Invalid speed");
            return;
        }

        this.speed = speed;
        this.track = track;

        this.id = globalId;
        globalId++;
    }

    public void go() {
        Thread.currentThread().setPriority(speed);

        for (int i = 1; i <= 100; i++) {
            if (i < 100) {
                System.out.println("Runner " + id + " ran " + i + " meters");
            }

            if (i == 100) {
                synchronized(track) {
                    System.out.println("Runner " + id + " ran 100 meters");
                    track.finishedRacers++;
                    int place = track.finishedRacers;
                    System.out.println("Runner " + id + " finished " + place + getSuffix(place));
                }
            }
        }
    }

    private String getSuffix(int place) {
        if (place == 1) return "st";
        if (place == 2) return "nd";
        if (place == 3) return "rd";
        return "th";
    }

    @Override
    public void run() {
        go();
    }
}