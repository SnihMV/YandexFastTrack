package ru.snih.yandex.fasttrack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MeetingTime {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(new File("input.txt"))) {
            int duration = scan.nextInt();
            Interval[] a = readIntervals(scan);
            Interval[] b = readIntervals(scan);
            Interval meetingTime = new Interval(-1, -1);
            A:
            for (int i = 0; i < a.length; i++) {
                if (a[i].getDuration() < duration) continue;
                B:
                for (int j = 0; j < b.length; j++) {
                    if (b[j].getDuration() < duration) continue;
                    if (b[j].start >= a[i].end) break B;
                    if (b[j].start + duration <= a[i].end && a[i].start + duration <= b[j].end) {
                        int meetingStart = Math.max(a[i].start, b[j].start);
                        meetingTime = new Interval(meetingStart, meetingStart + duration);
                        break A;
                    }
                }
            }
            Files.write(Path.of("output.txt"), meetingTime.toString().getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static Interval[] readIntervals(Scanner scan) {
        int count = scan.nextInt();
        Interval[] intervals = new Interval[count];
        for (int i = 0; i < count; i++) {
            intervals[i] = new Interval(scan.nextInt(), scan.nextInt());
        }
        return intervals;
    }

    private static class Interval {
        private final int start;
        private final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getDuration() {
            return end - start;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }
}


