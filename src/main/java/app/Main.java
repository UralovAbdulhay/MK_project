package app;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String path = "D:\\MYoD\\MK\\MK_project\\inputFiles";


        File fileOutput = new File(path);

        String[] files = fileOutput.list();

//        for (int j = 0; j < files.length; j++) {

//            String fileName = files[j];
            String fileName = "1_sal.txt";

//            File fileInput = new File(path + "/" + fileName);
            File fileInput = new File(path + "/" + "1_sal.txt");

            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileInput));
                BufferedWriter writer = new BufferedWriter(new FileWriter("output_1/" + fileName));


                StringBuilder s = new StringBuilder();
                String s1 = "";

                String bookName = "";

                while ((s1 = reader.readLine()) != null) {
                    if (s1.contains("#")) {
                        bookName = s1.replace("#", "").trim();
                    } else {
                        s.append(s1).append("\n");
                    }
                }


                String[] lines = s.toString().split("\n");
                String s2 = "";

                for (int i = 0; i < lines.length; i++) {
                    boolean b = false;
                    for (int i1 = 1; i1 <= 9; i1++) {
                        if (lines[i].trim().startsWith(i + "")) {
                            b = true;
                            break;
                        }
                    }

                    if (!b) {
                        s2 += "" + lines[i].trim() + "\n";
//                        lines[i] = "";
                    }
                }


                for (int i = 500; i > 0; i--) {

                    String bet = (bookName + "\n" + i);

                    // matndagi kitob nomlarini olib tashlash uchun
                    s = new StringBuilder(s.toString().replaceAll(bet, "\n"));

                    String s0 = "\n" + i + " ";
                    String s3 = " " + i + " ";
                    String s4 = " " + i + "-" + (i + 1) + " ";

                    s = new StringBuilder(s.toString().replaceAll((s0), "\n" + s0));
                    s = new StringBuilder(s.toString().replaceAll((s3), "\n" + s3.trim() + " "));
                    s = new StringBuilder(s.toString().replaceAll((s4), "\n" + s4.trim() + " "));

                    if (i < 15) {
                        System.out.println(bet);
                        System.out.println(s.toString().contains(bet));
                    }
                }

                System.out.println("'"+bookName + "' -> " + "1_sal.txt" + " = " + (s.length() / 1000) + " Kb");


                writer.write(s.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }

        System.out.println("\ntime = " + ((System.currentTimeMillis() - startTime) / 1000.0));

    }
}
