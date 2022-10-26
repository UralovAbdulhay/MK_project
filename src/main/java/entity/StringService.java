package entity;

public class StringService {

    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append((a[i].toString()));
            if (i == iMax)
                return b.toString();
            b.append(" ");
        }
    }


}
