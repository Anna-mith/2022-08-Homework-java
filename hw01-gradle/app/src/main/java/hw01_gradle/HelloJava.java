package hw01_gradle;

import com.google.common.base.Splitter;

public class HelloJava {
    static private final String ORIGINAL_STRING = "Using Guava Splitter to split this sentence";

    public static void main(String[] args) {
        Iterable<String> list = Splitter.on(' ').trimResults().split(ORIGINAL_STRING);

        for (String item : list) {
            System.out.println(item);
        }
    }

}
