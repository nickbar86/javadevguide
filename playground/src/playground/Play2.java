package playground;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import CountDownLatch.CountDownLatchExample.Worker;

public class Play2 {

	 // Complete the morganAndString function below.
    static String morganAndString(String a, String b) {

        int counterA = 0;
       int counterB = 0;
       StringBuilder builder = new StringBuilder();
       while((counterA <= a.length() && counterB < b.length())||(counterA < a.length() && counterB <= b.length())){
           if(counterA==a.length()){
               builder=builder.append(b.charAt(counterB));
               counterB++;
           }
           else if(counterB==b.length()){
               builder= builder.append(a.charAt(counterA));
               counterA++; 
           }
           else if(b.charAt(counterB)>a.charAt(counterA)){
               builder=builder.append(a.charAt(counterA));
               counterA++;
           }else if(b.charAt(counterB)<a.charAt(counterA)){
               builder=builder.append(b.charAt(counterB));
               counterB++;
           }else{
               final char sameLetter =  a.charAt(counterA);
               int stepLetters = findCloser(a.substring(counterA), b.substring(counterB), sameLetter);
               System.out.println("StepLetter==>"+stepLetters);
               if(stepLetters>0){
                   builder = builder.append(Stream
                   .generate(() -> String.valueOf((char)sameLetter))
                   .limit(stepLetters).collect(Collectors.joining()));
                   counterA+=stepLetters;
               }else{
                   builder = builder.append(Stream
                            .generate(() -> String.valueOf((char)sameLetter))
                            .limit(stepLetters*-1).collect(Collectors.joining()));
                   counterB+=(stepLetters*-1);
               }
           }
           
       }
       return builder.toString();
   }

   private static final int findCloser(String a, String b, char stepLetter){
       int rankings =0;
       int sameLetters = 0;
        boolean changedSeq =false;
       System.out.println("a:"+a+"  b:"+b);
       if(a.equals(b))return 1;
       while(true){
           System.out.println("rankings:"+rankings);
           if(a.length()==rankings+1){
               return sameLetters;
           }else if(b.length()==rankings+1){
               return sameLetters*-1;
           }else{
               if(b.charAt(rankings)>(a.charAt(rankings))){
                   return sameLetters;
               }else if(b.charAt(rankings)<(a.charAt(rankings))){
                   return sameLetters*-1;
               }else{
                   System.out.println("same found:"+rankings);
                   if(a.charAt(rankings)==stepLetter && !changedSeq){
                       sameLetters++;
                   }else{
                       changedSeq=true;
                   }
                   rankings++;
               }
           }
       }
       
   }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = morganAndString(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
    
    public static int maing(String arg){
    	int counter =0;
    	for(int i=0;i<arg.length();i++){
    		for(int j=i+1;j<arg.length();j++){
    			if(isPalindrome(arg.substring(i, j))){
    				counter++;
    			}
    		}
    	}
    	return counter;
    }
    public static boolean isPalindrome(String str){
    	return new StringBuffer(str).reverse().equals(str);
    }
}
