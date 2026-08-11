import java.util.Base64;

public class base64Encoding {
    static final String BASE64;

    static { // runs once when class is loaded in memory
        StringBuilder sb = new StringBuilder();

        for (char c = 'A'; c <= 'Z'; c++)
            sb.append(c);

        for (char c = 'a'; c <= 'z'; c++)
            sb.append(c);

        for (char c = '0'; c <= '9'; c++)
            sb.append(c);

        sb.append('+');
        sb.append('/');

        BASE64 = sb.toString();
    }

    public static String enCode(String input) {
        StringBuilder ans= new StringBuilder();

        // 3 chars at a time
        for(int i=0; i < input.length(); i+=3) {
            int b1= input.charAt(i);

            int b2= 0;
            int b3= 0;

            if(i + 1 < input.length()) {
                b2= input.charAt(i + 1);
            }

            if(i + 2 < input.length()) {
                b3= input.charAt(i + 2);
            }

            int combined= (b1 << 16) | (b2 << 8) | b3; // we want b1 to be at first 8 bits, b2 at 2nd and then b3

            // extracting 6 bits
            int index1= (combined >> 18) & 63; // 6 bits ke group  hai and we need 6 bits from first 8 so we do that and we use & to extract those 6 bits (63= all 1)
            int index2= (combined >> 12) & 63; 
            int index3= (combined >> 6) & 63; 
            int index4= (combined) & 63; 

            ans.append(BASE64.charAt(index1));
            ans.append(BASE64.charAt(index2));

            if (i + 1 >= input.length()) {
                // Only 1 byte was available
                ans.append("==");
            }
            else if (i + 2 >= input.length()) {
                // Only 2 bytes were available
                ans.append(BASE64.charAt(index3));
                ans.append('=');
            }
            else {
                // All 3 bytes were available
                ans.append(BASE64.charAt(index3));
                ans.append(BASE64.charAt(index4));
            }

        }
        return ans.toString();

    }

    public static String deCode(String input) {
        StringBuilder ans= new StringBuilder();

        for(int i=0; i < input.length(); i+=4) {
            int index1= BASE64.indexOf(input.charAt(i));
            int index2= BASE64.indexOf(input.charAt(i + 1));

            int index3= 0;
            int index4= 0;

            if(i + 2 < input.length() && input.charAt(i + 2) != '=') {
                index3= BASE64.indexOf(input.charAt(i + 2));
            }

            if(i + 3 < input.length() && input.charAt(i + 3) != '=') {
                index4= BASE64.indexOf(input.charAt(i + 3));
            }

            int combined= (index1 << 18) | (index2 << 12) | (index3 << 6) | (index4);

            //extracting original 8 bits
            int b1= (combined >> 16) & 255; // 255 has 8 ones
            int b2= (combined >> 8) & 255;
            int b3= (combined) & 255;

            ans.append((char) b1);

            // Don't add characters for padding
            if (i + 2 < input.length() &&
                input.charAt(i + 2) != '=') {

                ans.append((char) b2);
            }

            if (i + 3 < input.length() &&
                input.charAt(i + 3) != '=') {

                ans.append((char) b3);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {

        System.out.println(enCode("Man"));
        System.out.println(enCode("Ma"));
        System.out.println(enCode("Cat"));
        System.out.println(enCode("Hello World"));
        System.out.println(enCode("A"));
        System.out.println(enCode(""));

        System.out.println("<------------------------------->");

        System.out.println(deCode("TWFu"));
        System.out.println(deCode("TWE="));
        System.out.println(deCode("Q2F0"));
        System.out.println(deCode("SGVsbG8gV29ybGQ="));
        System.out.println(deCode("QQ=="));
        System.out.println(deCode(""));
    }


}
