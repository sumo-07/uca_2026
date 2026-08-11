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

    public static void main(String[] args) {

        System.out.println(enCode("Man"));
        System.out.println(enCode("Ma"));
        System.out.println(enCode("Cat"));
        System.out.println(enCode("Hello World"));
        System.out.println(enCode("A"));
        System.out.println(enCode(""));
    }


}
