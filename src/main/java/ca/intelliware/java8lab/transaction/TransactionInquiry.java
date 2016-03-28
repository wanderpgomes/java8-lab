package ca.intelliware.java8lab.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class TransactionInquiry {

	  private Trader raoul = new Trader("Raoul", "Cambridge");
      private Trader mario = new Trader("Mario","Milan");
      private Trader alan = new Trader("Alan","Cambridge");
      private Trader brian = new Trader("Brian","Cambridge");
		
      private List<Transaction> transactions = Arrays.asList(
	      new Transaction(brian, 2011, 300), 
	      new Transaction(raoul, 2012, 1000),
	      new Transaction(raoul, 2011, 400),
	      new Transaction(mario, 2012, 710),	
	      new Transaction(mario, 2012, 700),
	      new Transaction(alan, 2012, 950)
	  );

      /**
       *  Find how many transactions are from 2012.
       */
      public long findNumberOfTransactionsFrom2012() {

          long transactionsFrom2012 = 0;

          for (Transaction transaction : transactions) {
              if (transaction.getYear() == 2012) {
                  transactionsFrom2012++;
              }
          }
          return transactionsFrom2012;
      }
      
      /**
       *  Find all transactions from year 2011 and sort them by value (small to high).
       */ 
      public List<Transaction> findTransactionsFrom2011() {
    	  List<Transaction> transactionsFrom2011 = new ArrayList<Transaction>();
    	  
    	  for (Transaction transaction : transactions) {
    		  if (transaction.getYear() == 2011) {
    			  transactionsFrom2011.add(transaction);
    		  }
    	  }
    	  transactionsFrom2011.sort(new Comparator<Transaction>() {
    	     @Override
    		 public int compare(Transaction t1, Transaction t2) {
    			return Integer.compare(t1.getValue(), t2.getValue());
    		 }
		  });
    	  
    	  return transactionsFrom2011;
      }
      
      /**
       *  Find all the unique cities where the traders work.
       */
      public List<String> findAllUniqueCities(){
    	  List<String> cities = new ArrayList<String>();
    	  
    	  for (Transaction transaction : transactions) {
    		  String city = transaction.getTrader().getCity();
    		  if (!cities.contains(city)){
    			  cities.add(city);
    		  }
    	  }
    	  
    	  return cities;
      }
      
      /**
       *  Find all traders from Cambridge and sort them by name.
       */
       public List<Trader> findAllTradersFromCambridge() {
    	   List<Trader> traders = new ArrayList<Trader>();
    	   
    	   for (Transaction transaction : transactions) {
     		  Trader trader = transaction.getTrader();
     		  if ("Cambridge".equals(trader.getCity()) && !traders.contains(trader)){
     			  traders.add(trader);
     		  }
    	   }
    	   traders.sort(new Comparator<Trader>() {
		       @Override
			   public int compare(Trader t1, Trader t2) {
				   return t1.getName().compareTo(t2.getName());
			   }
		   });
    	   
    	  return traders;
       }
      
      /**
       *  Get a string of all traders’ names sorted alphabetically.
       */
      public String getAllTradersNames(){
    	  List<String> names = new ArrayList<String>();
    	  
    	  for (Transaction transaction : transactions) {
     		  String traderName = transaction.getTrader().getName();
     		  if (!names.contains(traderName)){
     			  names.add(traderName);
     		  }
    	   }
    	   names.sort(new Comparator<String>() {
		       @Override
			   public int compare(String n1, String n2) {
                   return n1.compareTo(n2);
			   }
		   });
    	   
    	   String namesStr = "";
    	   for (String name : names){
    		   namesStr += name;
    	   }
    	   return namesStr;
      }
      
      /**
       *  Are there any trader based in Milan?
       */
      public boolean areThereAnyTradeFromMilan(){
    	  
    	  for (Transaction transaction : transactions){
    		  if ("Milan".equals(transaction.getTrader().getCity())){
    			  return true;
    		  }
    	  }
    	  return false;
      }
      
      /**
       *  Update all transactions so that the traders from Milan are set to Cambridge.
       */
      public List<Transaction> updateTradersFromMilanToCambridge(){
    	  
    	  for (Transaction transaction : transactions) {
    		  Trader trader = transaction.getTrader();
    		  if("Milan".equals(trader.getCity())){
    			  trader.setCity("Cambridge");
    		  }
    	  }
    	  
    	  return transactions;
      }
      
      /**
       *  Get the highest value in all the transactions.
       */
      public int getHighestTransactionValue(){
    	  int max = 0;
    	  for (Transaction transaction : transactions) {
    		  max = Math.max(max, transaction.getValue());
    	  }
    	  return max;
      }

      /**
       *  Get the sum of all transaction values.
       */
      public int getTransactionTotal(){
         int sum = 0;
         for (Transaction transaction : transactions) {
            sum += transaction.getValue();
         }
         return sum;
      }
     
}


