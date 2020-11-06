
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        System.out.println("Hello "+args[0]);

        int number = 10;
        int a = ++number;
        int b = --number;
//        System.out.println("number:"+number+" number++:"+(number++)+" number--:"+(number--));
        System.out.println(a+" "+b);

        a=5;
        b=3;


        //swapping a and b using arithmetic opertors
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println("a: "+ a +" b: "+b);

        //swapping a and b using arithmetic opertors
        a=a*b;
        b=a/b;
        a=a/b;
        System.out.println("a: "+a+" b: "+b);

        //swapping a and b using Bitwise XOR opertors
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a: "+a+" b: "+b);



        //Primitive Data Types
        int maxint=Integer.MAX_VALUE;
        int minint=Integer.MIN_VALUE;
        System.out.println("Maxint: "+maxint+"\nMinint: "+minint);

        int maxbyte=Byte.MAX_VALUE;
        int minbyte=Byte.MIN_VALUE;
        System.out.println("Maxbyte: "+maxbyte+"\nMinbyte: "+minbyte);
        System.out.println("Maxshort: "+Short.MAX_VALUE+"\nMinshort: "+Short.MIN_VALUE);
        System.out.println("Maxlong: "+Long.MAX_VALUE+"\nMinlong: "+Long.MIN_VALUE);
        System.out.println('\u0041'+"to"+'\u005A');
        System.out.println(45+"number");
        a=60;
        b=13;
//        if(a & b) System.out.printf(a&b);
    }
}
