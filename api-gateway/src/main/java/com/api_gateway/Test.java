package com.api_gateway;

public class Test {

	public static void unique(int[] a) {
		int target = 1114;
		
		for(int i=0; i<a.length; i++) {
			
			if(target >= a[i]) {
				
			int node = target / a[i];
			target = target % a[i];
			
			System.out.println("note " +a[i]+" is "+node);
			
		   }
		}
	}
	
	 public static void main(String [] args) {
		 
		    String s = "surendra"; 
	        for(int i=s.length()-1; i>0; i--){
	        	char ch =s.charAt(i);
	        	System.out.print(ch);
	        }
		
		int[] a = {500, 100, 50, 20, 10, 5, 2, 1};
		unique(a);
	}
}