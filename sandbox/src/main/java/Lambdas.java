import java.util.function.*;

public class Lambdas {
    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //using the test method of Predicate
        Predicate<String> stringLen  = (s)-> s.length() < 10;
        System.out.println("true if input string < 10, false if string is > 10");
        System.out.println("input string: 'Apples' Test = " + stringLen.test("Apples"));
        System.out.println("input string: 'Apples are my favorite fruit. I really like them' Test: " +stringLen.test("Apples are my favorite fruit. I really like them "));

        //Consumer example uses accept method
         Consumer<String> consumerStr = (s) -> System.out.println(s.toLowerCase());
         consumerStr.accept("\nTHIS IS AN UPPER STRING");
         
        //Function example        
        Function<Integer,String> converter = (num)-> Integer.toString(num);
        System.out.println("\nlength of 26: " + converter.apply(26).length());

        //Supplier example
        Supplier<String> s  = ()-> "Java is fun";
        System.out.println(s.get());
        
        //Binary Operator example
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("add 10 + 25: " + add.apply(10, 25));
        
        //Unary Operator example
        UnaryOperator<String> str  = (msg)-> msg.toUpperCase();
        System.out.println(str.apply("This is my message in upper case"));
    }
}
