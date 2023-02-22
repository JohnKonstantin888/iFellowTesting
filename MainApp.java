import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String testString = reader.readLine();
            test(testString);
        }
    }

    public static void test(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println(0);
            return;
        }
        String brackets = text;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (text.contains("()")) {
            text = text.replaceFirst("\\(\\)", "");
            count += 2;
            builder.append("()");
        }
        List<String> list = generateBrackets(count / 2);
        for (String bracket : list) {
            if (brackets.contains(bracket)) {
                builder = new StringBuilder(bracket);
            }
        }
        System.out.println(count + (!builder.isEmpty()? " - " : " ") + builder);
    }

    public static List<String> generateBrackets(int count) {
        char[] array = new char[count * 2];
        List<String> list = new ArrayList<>();
        addBracketsToList(list, count, count, array, 0);
        return list;
    }

    public static void addBracketsToList(List<String> list, int leftRem, int rightRem, char[] array, int count) {
        if (leftRem < 0 || rightRem < leftRem) {
            return;
        }
        if (leftRem == 0 && rightRem == 0) {
            String result = String.copyValueOf(array);
            list.add(result);
        } else {
            if (leftRem > 0) {
                array[count] = '(';
                addBracketsToList(list, leftRem - 1, rightRem, array, count + 1);
            }
            if (rightRem > leftRem) {
                array[count] = ')';
                addBracketsToList(list, leftRem, rightRem - 1, array, count + 1);
            }
        }
    }
}