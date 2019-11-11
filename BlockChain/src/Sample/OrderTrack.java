package Sample;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.*;

public class OrderTrack {
	
	public static ArrayList<OrderBlock> blockchain = new ArrayList<OrderBlock>();
	public static int difficulty = 5;

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		//Gson gson=new GsonBuilder().create(); 
		System.out.println("enter the number of items to be entered");
		
		Scanner scan = new Scanner(System.in);
		String n = scan.nextLine();
		int j= Integer.parseInt(n);
		for(int items=0; items<j; items++) {
		
			System.out.println("Creating the block for the item entered ");
			blockchain.add(new OrderBlock("Hi This is the order when order is placed", "0"));
		System.out.println("Trying to Mine block of order ... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new OrderBlock("This is the order at packing stage ",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block of order at phase 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new OrderBlock("This is the order at shipping stage",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine order at phase 3... ");
		blockchain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		//String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		
	//	System.out.println(blockchainJson);
	}
	}
	public static Boolean isChainValid() {
		OrderBlock currentBlock; 
		OrderBlock previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			System.out.println("\nThe block chain");
			System.out.println("current block is:" + currentBlock);
			System.out.println("previous block is:"+ previousBlock);
			
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}