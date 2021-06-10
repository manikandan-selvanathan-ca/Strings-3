import java.util.*;

public class BasicCalculator {
    // Sanitization
    // Iterate each character from the string
    // If the char is a digit
    // We can maintain in the local varaible - if it next value is also digit we
    // should append.
    // if the char is operator
    // get the last operator
    // + push +value;
    // - push -value;
    // * push(pop() * value ) //Since the Multiplication in Bodmas has highter
    // priority, calcualte current multiplication and update the stack.
    // / push(pop() / value ) //Since the Divition in Bodmas has highter priority,
    // calcualte the and update the stack.
    // We will have +and - values in the stack
    // Iterate all the values and add to the result;


    //TC: O(N) Since we are iterating all the characters from the string. It will be O(N) where N is the length of the string.
    //SC: O(N) as we are using stack to store the digits
    public int calculate(String s) {

        int number = 0;
        char lastSymbol = '+';
        Stack<Integer> stack = new Stack<Integer>();
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar != ' ') {
                if (Character.isDigit(currentChar)) {
                    int currentNumber = currentChar - '0';
                    number = number * 10 + currentNumber;
                }

                if (!Character.isDigit(currentChar) || i == s.length() - 1) {
                    switch (lastSymbol) {
                        case '+':
                            stack.push(number);
                            break;
                        case '-':
                            stack.push(-number);
                            break;
                        case '*':
                            stack.push(number * stack.pop());
                            break;
                        case '/':
                            stack.push(stack.pop() / number);
                            break;

                    }
                    number = 0;
                    lastSymbol = currentChar;
                }
            }
        }

        while (!stack.isEmpty()) {
            number = number + stack.pop();
        }

        return number;
    }

    public static void main(String[] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        int result = basicCalculator.calculate(" 3+5 / 2 ");
        System.out.println("The result is " + result);
    }
}