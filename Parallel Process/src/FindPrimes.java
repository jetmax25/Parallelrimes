import java.util.BitSet;



public class FindPrimes {
	
	static BitSet numbers = new BitSet(100000000);
	public static final int MAX = 100000000;
	
	public static void main(String[] args)
	{
		
		//holds 8 threads
		Thread threads[] = new Thread[8];
			
		//loops through the treads
		int threadCount = 0;
		
		int total = 0;
		int primes = 2;
		
		
		while(primes < Math.sqrt(MAX))
		{
			//if the thread is being used go to the next one
			if( threads[threadCount] != null && !threads[threadCount].isAlive())
			{
				threadCount++;
				threadCount %= 8;
			}
			
			//dont bother checking nonprime numbers
			while(numbers.get(primes) == true)
			{
				primes++;
			}
			
			threads[threadCount] = new Thread(new FindPrimes.Primer(primes));
			threads[threadCount].run();
			primes++;
			
		}
	}
	
	public static class Primer implements Runnable
	{
		int prime;
		
		public Primer(int prime)
		{
			this.prime = prime;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Starting run " + prime);
			for(int i = prime * prime; i < MAX; i += prime)
			{
				numbers.set(i);
			}
			
			System.out.println("Ending run " + prime);
		}
		
	}
}
