package ru.snih.yandex.fasttrack;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Robot {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(new File("input.txt"))) {
            int n = scan.nextInt();
            if (n < 0 || n > 1_000_000) throw new IllegalArgumentException("incorrect N value");
            int x, y, factor;
            int checkPoint = 0, toNxtCP = 2, remainder = n;
            while (remainder >= toNxtCP) {
                remainder -= toNxtCP;
                checkPoint++;
                toNxtCP += 2;
            }
            if (checkPoint % 2 == 0) {
                x = y = checkPoint / 2;
                factor = -1;
            } else {
                x = y = -(checkPoint / 2 + 1);
                factor = 1;
            }
            int lineSize = checkPoint + 1;
            x += factor * min(lineSize, remainder);
            remainder = max(0, remainder - lineSize);
            y += factor * remainder;
            Files.write(Path.of("output.txt"), new String(x + " " + y).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
