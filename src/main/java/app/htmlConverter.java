package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Bob;
import entity.Kitob;
import entity.Oyat;

import java.io.*;

public class htmlConverter {
    public static void main(String[] args) {


        String jsonOutPath = "jsonOut";
        String htmlOutPath = "htmlOut";
        File[] files = new File("output_2").listFiles();

        Kitob kitob1 = new Kitob();


        for (int i = 0; i < files.length; i++) {


            try {
                BufferedReader reader = new BufferedReader(new FileReader(files[i]));

                Kitob kitob = new Kitob(files[i].getName().replace(".txt", ""));
                boolean bir = true;
                BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(jsonOutPath + "/" + kitob.getBookName() + ".json"));
                BufferedWriter htmlWriter = new BufferedWriter(new FileWriter(htmlOutPath + "/" + kitob.getBookName() + ".htm"));


                String s1 = "";

                int bobNum = 0;
                int oyatNum = 0;

                Bob bob = null;

//                System.out.println(kitob.toString());


                while ((s1 = reader.readLine()) != null) {

                    s1 = s1.replaceAll("\\*", "").trim();
                    s1 = s1.replaceAll("Қ", "К");
                    s1 = s1.replaceAll("Ғ", "Г");
                    s1 = s1.replaceAll("Ҳ", "Х");
                    s1 = s1.replaceAll("қ", "к");
                    s1 = s1.replaceAll("ғ", "г");
                    s1 = s1.replaceAll("ҳ", "х");



                    if (!s1.isEmpty()) {

                        if (s1.contains("–БОБ")) {

                            //System.out.print("matches = " + num.matches("[0-9]{1,2}") + (" "));

                            String num = s1.replace("–БОБ", "").trim();

                            if (bir) {
                                bir = false;
                                bobNum = Integer.parseInt(String.valueOf(num.charAt(1)));
//                                bobNum = Integer.parseInt(String.valueOf(num).trim());

                            } else {
                                bobNum = Integer.parseInt(String.valueOf(num));
                            }

                            bob = new Bob(bobNum);

//                            System.out.println("kitob = "+kitob.getBookName());

//                            System.out.println("bobNum = " + bobNum);

                            kitob.addChapter(bob);

                        } else {

                            String[] strings = s1.split(" ");


                            String s2 = strings[0].trim();
//                            System.out.println("s1 = " + s1);
//                            System.out.println("s2 = " + s2);
                            oyatNum = Integer.parseInt(s2);
//                            System.out.println("oyatNum = " + oyatNum);
                            bob.addOyat(new Oyat(oyatNum, getSrting(strings)));

                        }

                    }
                }

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                Gson gson = new Gson();

//                System.out.println(gson.toJson(kitob, Kitob.class));

//                String result = gson.fromJson(gson.toJson(kitob, Kitob.class), Kitob.class).toString();
//                System.out.println(result);
//                System.out.println(result.equals(kitob.toString()));

                kitob1 = kitob;

                jsonWriter.write(gson.toJson(kitob, Kitob.class));
                jsonWriter.flush();

                htmlWriter.write(kitob.toString());
                htmlWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(kitob1.toString());

        Gson gson = new Gson();
        System.out.println(gson.toJson(kitob1, Kitob.class));


    }

    static String getSrting(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(strings[i]);

//            if (i != strings.length - 1)
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}
