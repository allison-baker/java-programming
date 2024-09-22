import java.util.Arrays;

public class SingleArray
{
    public static void main(String[] args) {
        double[] prices = {5.25, 6.50, 2.30, 10.75};
        //prices array has a length of 4
        double total = prices[0] +
                prices[1] +
                prices[2] +
                prices[3];
        //responses array has a length of 5
        boolean[] responses = {true, false, false, true, true};
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        String[] names = new String[10];

        System.out.print("\nPrices: " + total);

        // can print the entire array using toString method in Arrays
        System.out.println("\nresponses: " + Arrays.toString(responses));
        System.out.println("vowels: " + Arrays.toString(vowels));

        // print each value on separate line
        for (int cnt = 0; cnt < responses.length; cnt++)
        {
            System.out.print("\nResponses: : " + responses[cnt]);
        }

        // can use cnt again because it was defined in for loop scope
        for (int cnt = 0; cnt < vowels.length; cnt++)
        {
            System.out.print("\nvowels: " + vowels[cnt]);
        }
    }
}