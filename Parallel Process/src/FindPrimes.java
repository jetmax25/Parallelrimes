import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

class PrimeFinder extends Thread {
	int thread;
    public static int max;
    public static BitSet numbers;
    int start;
    int end;
    int prime;
    
   PrimeFinder(int i)
   {
	   thread =i;
   }
   
   public static void setMax(int num)
   {
	   max = num / 2;
	   numbers = new BitSet(max);
   }
   
   public void run() {
	
	for(int i = start; i < end; i+= (prime *2) + 1)
	   {
		   numbers.set(i);
	   }
   }
   
   public void setLimits(int prime)
   {
	   
	   this.prime = prime;
	   int temp = (2 * (prime * prime + prime));
	   int dif = ((max - temp) / 8);
	   this.start = temp + (dif * thread);
	 
	   if(thread == 7) end = max;
	   else this.end = Math.min(start + dif, max);
	   
	   while((this.start * 2 + 1) % (prime * 2 +1) != 0){
		   this.start--;
		   
		   
		   
		   
	   
	   } 
	   

   }
   
   public void start(int prime)
   {
	  
	  setLimits(prime);
	   
	   super.start();
   }

}

public class FindPrimes {
	static BitSet numbers2;
   public static void main(String args[]) {
	   PrimeFinder threads[] = new PrimeFinder[8];
	   int number = 100000000;
	   int count = 1;
	   for(int i = 0; i < 8; i++){
		      if (threads[i] == null)
		      {
		         threads[i] = new PrimeFinder(i);
		      }
	      }
	   boolean over = false;
	   PrimeFinder.setMax(number);
	   System.out.println("Starting multi");
	   long startTime = System.currentTimeMillis();
	   for(PrimeFinder thread : threads)
	   {
		   if(!thread.isAlive()) thread.start(1);
		   
	   }
	   count++;
	   for(int i = 2; i < number / 2; i++){
		   if(!PrimeFinder.numbers.get(i))
		   {
			   	count++;
			 if(over) continue;
			   if( i > (Math.sqrt(number) / 2) + 1){
				 over = true;
				 continue;
			   } 
				   for(PrimeFinder thread : threads)
				   {
					   while(thread.isAlive()){
					   }
					   thread.setLimits(i);
					   thread.run();
				   }
			 
		   }  
	   } 
	   long endTime   = System.currentTimeMillis();
	   float first = (endTime - startTime);
	   
	   System.out.println("multithreaded " + (endTime - startTime) + " count = " + count); 
	   
	   count = 1;
	  over = false;
	  System.out.println("Starting single");
	   startTime = System.currentTimeMillis();
	   numbers2 = new BitSet(number / 2);
	   for(int i = 1; i < number / 2; i++)
	   {
		   if(!numbers2.get(i))
		   {
			   
			  count++;
			   if(over) continue;
			   if( i > (Math.sqrt(number) / 2) + 1){
					 over = true;
					 continue;
				   } 
			   seieve(i, number);
			   
		   }
		  
	   }
	   endTime   = System.currentTimeMillis();
	   float second = (endTime - startTime);
	    System.out.println("singlethreaded " + (endTime - startTime) + " count = " + count);
	    
	    System.out.printf("\n a difference of %.2f", first / second);
   }

private static void seieve(int num, int number) {
	// TODO Auto-generated method stub
	for(int i = 2 * (num * num + num); i < number / 2; i+= num * 2 + 1 )
	{
		numbers2.set(i);
	}
}
}