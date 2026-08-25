// package week7.programming;

import java.util.Stack;

public class postFix {

    public static int evalPostFix(String str) {
        Stack<Integer> st = new Stack<>();

        String[] arr = str.split(" ");

        for (String part : arr) {
            if (Character.isDigit(part.charAt(0))) {
                st.push(Integer.parseInt(part));
            }

            else {
                int b = st.pop();
                int a = st.pop();

                if (part.equals("+")) {
                    st.push(a + b);
                } else if (part.equals("-")) {
                    st.push(a - b);
                } else if (part.equals("*")) {
                    st.push(a * b);
                } else if (part.equals("/")) {
                    st.push(a / b);
                }
            }
        }

        return st.pop();
    }

    public static void main(String[] args) {

        String expr = "2 3 1 * + 9 -";

        System.out.println(evalPostFix(expr));
    }
}
