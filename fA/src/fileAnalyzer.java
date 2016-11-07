import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;



public class fileAnalyzer {
	
	public static void main(String [] args){
	
		String [] chars = new String[17];
		chars[0] = "A";
		chars[1] = "B";
		chars[2] = "A";
		chars[3] = "A";
		chars[4] = "/";
		chars[5] = "!";
		chars[6] = "!";
		chars[7] = "!";
		chars[8] = "C";
		chars[9] = "D";
		chars[10] = "B";
		chars[11] = "A";
		chars[12] = "F";
		chars[13] = "G";
		chars[14] = "B";
		chars[15] = "B";
		chars[16] = "A";
		
		cipherString(chars, 5);

	}
	
	
	static String cipherString(String [] symbolsArr, int symbolValuesLength){
		HashMap<String, Integer> symbolsRatio = new HashMap<>();
		String cipher = "";
		int counter = 0;

		// Keep track of number of symbols
		for (int i = 0; i < symbolsArr.length; i++){
			if(symbolsRatio.containsKey(symbolsArr[i])){
				counter = symbolsRatio.get(symbolsArr[i]);
				symbolsRatio.put(symbolsArr[i], ++counter);
			}
			else{
				symbolsRatio.put(symbolsArr[i],1);
			}
		}
		
		// Print whole string
		for(int i = 0; i < symbolsArr.length; i++){
			cipher += symbolsArr[i];
		}
		System.out.println(cipher);
		
		for(String s : symbolsRatio.keySet()){
			System.out.println(s + symbolsRatio.get(s));
		}
		

		
		Set<Integer> ss = new TreeSet<>();
		
		int k = 0;
		// Store number per symbol in each array
		for(String s : symbolsRatio.keySet()){
			ss.add(symbolsRatio.get(s));
		}	
		
		int [] symbolValues = new int[ss.size()];
		for(Integer s : ss){
			symbolValues[k++] = s;
		}
		
		// Sort
		quickSort(symbolValues, 0, symbolValues.length - 1);
		
		// Get key of value in sorted array, print out values
		for(int i = 0; i < symbolValues.length; i++){
			System.out.print(symbolValues[i] + " ");
		}
		// Replace the bottom half values with one letter, get key of the middle + 1 symbol
		// Iterate through whole hashmap to see which symbols match with their values
		
		cipher = "";
		int cutoff = 0;
		Stack uniqueSymbols = new Stack();
		
		for(String key : symbolsRatio.keySet()){
			if(cutoff == symbolValuesLength)
				break;
			else{
			uniqueSymbols.push(key);
			cutoff++;
			}
		}
		System.out.print(uniqueSymbols);
		
		// Get a random symbol from the cutoff [set of uncommon symbols]
		Random rand = new Random();
		int uncommonCounter = 0;
		int randomUncommon = 0;
		String [] uncommons = new String[symbolsRatio.size() - symbolValuesLength];
		int j = 0;
		String chosenUncommon = "";
		System.out.print("\nCounter: ");
			for(String s : symbolsRatio.keySet()){
				uncommonCounter++;
				if(uncommonCounter > symbolValuesLength){
					uncommons[j] = s;
					System.out.print(uncommons[j]);
					j++;
				}
			}
		randomUncommon = rand.nextInt(uncommons.length);
		chosenUncommon = uncommons[randomUncommon];

		System.out.println("Chosen Uncommon: " + chosenUncommon);
		// Iterate through original string array; if most occurring symbol not in array, replace
		String cipheredString = "";
		for(int i = 0; i < symbolsArr.length; i++){
			if(!uniqueSymbols.contains(symbolsArr[i])){
				symbolsArr[i] = chosenUncommon;
				cipher+=symbolsArr[i];
			}
			else{
				cipher+= symbolsArr[i];
			}
		}
		
		System.out.println(cipher);
		return cipher;
	}
	
	static void quickSort(int [] arr, int low, int high){
		if(arr == null || arr.length == 0)
			return;
		if(low >= high)
			return;
		
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];
		
		int i = low;
		int j = high;
		
		while (i <= j){
			while(arr[i] > pivot){
				i++;
			}
			while(arr[j] < pivot){
				j--;
			}
			
			if(i <= j){
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		// Sort from greatest to least
		if(low < j)
			quickSort(arr, low, j);
		if(high > i)
			quickSort(arr, i, high);
	}
}
