// package week7.programming;

import java.util.Stack;

public class balancedParen {
    public static boolean isBalanced(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            }

            else if (ch == ')' || ch == '}' || ch == ']') {
                // no empty bracks
                if (st.isEmpty()) {
                    return false;
                }

                char top = st.pop();

                if ((ch == ')' && top != '(') ||
                        (ch == ']' && top != '[') ||
                        (ch == '}' && top != '{')) {

                    return false;
                }
            }
        }

        return st.isEmpty();
    }

    public static void main(String[] args) {

        String expr1 = "[()]{}{[()()]}";
        String expr2 = "[()";

        System.out.println(isBalanced(expr1));
        System.out.println(isBalanced(expr2));
    }
}
