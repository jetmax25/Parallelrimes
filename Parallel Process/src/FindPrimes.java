import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

class PrimeFinder implements Runnable {
   private static Thread[] threads;
   BitSet numbers;
   int size;
   ArrayList<Integer> primes;
   int counter;
   PrimeFinder( int size){
       
     numbers = new BitSet(size / 2);
     this.size = size;
     primes = new ArrayList<Integer>();
     primes.add(2);
     counter = 1;
     int found = 1;
   }
   
   public void run() {
	   test();
     for(int i = 1; i < size/2; i++)
     {
    	
    	 if(!numbers.get(i))
    	 {
    		 sieve(i);
    		 System.out.println(i * 2 + 1);
    		 counter++;
    	 }
     }
     System.out.println("counter " + counter);
   
   }
   
   public void sieve(int num)
   {
	   for(int i = 2 * (num * num + num); i < size / 2; i+= (num *2) + 1)
	   {
		   numbers.set(i);
	   }
   }
   
   public void test(){
	   System.out.print("test");
   }
   
   public void start ()
   {
	  threads = new Thread[8];
	 
      for(int i = 0; i < 8; i++){
	      if (threads[i] == null)
	      {
	    	  
	         threads[i] = new Thread(this);
	      }
      }
     threads[3].start();
   }

}

public class FindPrimes {
   public static void main(String args[]) {
   
      PrimeFinder R1 = new PrimeFinder( 100);
      R1.start();
   }
}