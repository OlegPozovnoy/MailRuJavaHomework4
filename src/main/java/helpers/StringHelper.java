package helpers;

public class StringHelper {
    public static StringBuilder repeat(String a, int rep) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rep; i++) {
            result.append(a);
        }
        return result;
    }
}
