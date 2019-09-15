package software.kalender.soruuygulamasi.Helpers;

public class TextHelper {
    public String encode(String data) {
        //todo

        String result = data;

        String[] olds = new String[]{
                "'",
                "\""
        };

        String[] news = new String[]{
                "[[SINGLE_QUOTE]]",
                "[[DOUBLE_QUOTE]]"
        };

        for (int i = 0; i < olds.length; i++) {
            result = result.replace(olds[i], news[i]);
        }

        return result;
    }

    public String decode(String data) {
        //todo

        String result = data;

        String[] olds = new String[]{
                "[[SINGLE_QUOTE]]",
                "[[DOUBLE_QUOTE]]"
        };

        String[] news = new String[]{
                "'",
                "\""
        };

        for (int i = 0; i < olds.length; i++) {
            result = result.replace(olds[i], news[i]);
        }

        return result;
    }
}
