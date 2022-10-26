package entity;


import lombok.*;

@AllArgsConstructor
@Data
public class Oyat {
    private int verseNum;
    private String verseValue;



    @Override
    public String toString() {
        return String.format("<BR><SUP>%d </SUP>%s\n", verseNum, verseValue);

// <BR><SUP>31 </SUP>И увидел Бог все, что Он создал, и вот, хорошо весьма. И был вечер, и было утро: день шестой.

    }
}
