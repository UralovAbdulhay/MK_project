import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_1 {

    public static void main(String[] args) throws IOException {


        System.out.println("file yo'lini kiriting:");


        String path = new Scanner(System.in).nextLine();

        File file = new File(path);

        int[] ints = {2, 10, 10000000};

        if (file.isFile()) {

            List<String> lines = Files.readAllLines(file.toPath());
            List<String> changes = new ArrayList<>(ints.length);

            for (int i = 0; i < ints.length; i++) {
                int numLine = ints[i] - 1;
                if (numLine < 0 || numLine >= lines.size()) {
                    continue;
                }
                String line = lines.get(numLine);
                if (!line.trim().startsWith("//") && !line.trim().isEmpty()) {
                    line = "//" + line;
                    lines.set(numLine, line);
                    changes.add(line);
                }
            }

            for (int i = 0; i < changes.size(); i++) {
                System.out.println(i + 1 + ".  " + changes.get(i));
            }
            System.out.println();
            System.out.println(new String("Чтобы отменит изменееие нажмите N: ".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            String con = new Scanner(System.in).next();

            if (!con.equalsIgnoreCase("n")) {
                Files.write(file.toPath(), lines);
                System.out.println(new String("Изменееие успешно совершился :)".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            } else {
                System.out.println(new String("Изменееие отменен :(".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

            }


        }

    }


}
