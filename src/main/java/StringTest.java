import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringTest {


    public static void main(String[] args) {


        String s = new String("–".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(Arrays.toString("–".getBytes(StandardCharsets.UTF_8)));
        System.out.println(Arrays.toString("–".getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println(Arrays.toString("—".replace("—","-").getBytes()));
        System.out.println(Arrays.toString(s.getBytes()));
//        System.out.println(Arrays.toString("ʻ".getBytes(StandardCharsets.UTF_8)));
//        System.out.println(Arrays.toString("-".getBytes()));
//        System.out.println(Arrays.toString(s.getBytes()));
//        System.out.println(s);

//        System.out.println(Arrays.toString("–".getBytes()));
//        System.out.println(new String(new String(new byte[]{-61, -94, -62, -128, -62, -109}).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//        System.out.println(new String(new String(new byte[]{-61, -118, -62, -69}).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//        System.out.println(" boʻm–boʻsh boʻlib, tubsiz".replace("–", "-").replace("ʻ","'"));


//        System.out.println(((int)'ʻ'));



    }

}
