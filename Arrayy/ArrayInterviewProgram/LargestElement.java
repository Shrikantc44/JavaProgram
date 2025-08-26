public class LargestElement
{
	public static void main(String[] args)
	{
		int[] arr = {10,34,89,2,56};
		
		int max = arr[0];  //Assume first element is largest
		
		for(int i = 1; i< arr.length; i++)
		{
			if(arr[i] > max)
			{
				max = arr[i]; //Update max
			}
		}
		System.out.println("Largest element in array: " +max);
	}
}
		