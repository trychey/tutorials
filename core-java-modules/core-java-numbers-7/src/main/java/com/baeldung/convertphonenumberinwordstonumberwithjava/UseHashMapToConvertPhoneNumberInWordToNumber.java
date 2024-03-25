import java.util.*;

public class UseHashMapToConvertPhoneNumberInWordToNumber {

    public static String ConvertPhoneNumberInWordToNumber(String phoneNumberInWord) {

        boolean format = isValidPhoneNumberFormat(phoneNumberInWord);
        Map<String, String> digits = mapIndividualDigits();
        Map<String, Integer> modifiers = mapModifiers();
        String convertedNumber = "";
        int prefixCounter = 1;

        if (format) {
            String[] words = phoneNumberInWord.split("\\s+");
            for (String word : words) {
                if (modifiers.containsKey(word)) {
                    prefixCounter *= modifiers.get(word);
                } else {
                    convertedNumber += digits.get(word).repeat(prefixCounter);
                    prefixCounter = 1;
                }
            }
        }

        if (convertedNumber.length() > 10) {
            System.out.println("Incorrect Phone Number! The correct one is 10-digit long.");
            return null;
        }

        return convertedNumber;
    }

    public static boolean isValidPhoneNumberFormat(String phoneNumberInWord) {

        String regex = "^(double|triple|quadruple|zero|one|two|three|four|five|six|seven|eight|nine)$";

        phoneNumberInWord = phoneNumberInWord.toLowerCase();

        if (phoneNumberInWord.endsWith("double") ||
                phoneNumberInWord.endsWith("triple") ||
                phoneNumberInWord.endsWith("quadruple")) {
            System.out.println("A Phone Number Can't End with a Numerical Prefix");
            return false;
        } else {
            List<String> phoneNumberInWordArray = Arrays.asList(phoneNumberInWord.split(" "));
            for (int i = 0; i < phoneNumberInWordArray.size(); i++) {
                if ((isNumericalPrefix(phoneNumberInWordArray.get(i)) && isNumericalPrefix(phoneNumberInWordArray.get(i + 1)))) {
                    System.out.println("Consecutive Numerical Prefixes Detected.");
                    return false;
                }
                if (!phoneNumberInWordArray.get(i).matches(regex)) {
                    System.out.println("Invalid Numberical Prefix or Digit in Words! Try again");
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isNumericalPrefix(String word) {
        return word.equals("double") || word.equals("triple") || word.equals("quadruple");
    }

    public static Map<String, Integer> mapModifiers() {
        Map<String, Integer> modifiers = new HashMap<>();

        modifiers.put("double", 2);
        modifiers.put("triple", 3);
        modifiers.put("quadruple", 4);

        return modifiers;
    }


    public static Map<String, String> mapIndividualDigits() {
        Map<String, String> digits = new HashMap<>();

        digits.put("zero", "0");
        digits.put("one", "1");
        digits.put("two", "2");
        digits.put("three", "3");
        digits.put("four", "4");
        digits.put("five", "5");
        digits.put("six", "6");
        digits.put("seven", "7");
        digits.put("eight", "8");
        digits.put("nine", "9");

        return digits;
    }

}
