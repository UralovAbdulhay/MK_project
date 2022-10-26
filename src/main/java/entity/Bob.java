package entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


@AllArgsConstructor

public class Bob {

    @Setter
    @Getter
    private int chapterNum;


    private ArrayList<Oyat> verses = new ArrayList<Oyat>();




    public Bob(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public ArrayList<Oyat> getVerses() {
        return verses;
    }

    public void addOyat(Oyat oyat) {
        this.verses.add(oyat);
    }


    @Override
    public String toString() {
        return String.format("\n<BR><BR><A NAME=\"%d\"><H1>%d</H1>\n", chapterNum, chapterNum) +
                StringService.toString(verses.toArray());

//        <BR><BR><A NAME="glava1"><H1>1</H1>
    }
}
