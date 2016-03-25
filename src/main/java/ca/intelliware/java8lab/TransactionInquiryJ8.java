package ca.intelliware.java8lab;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;


public class TransactionInquiryJ8 {

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
       *  Find all transactions from year 2011 and sort them by value (small to high).
       */ 
      public List<Transaction> findTransactionsFrom2011() {
    	  return transactions.stream()
                             .filter(transaction -> transaction.getYear() == 2011)
                             .sorted(comparing(Transaction::getValue))
                             .collect(toList());
      }
      
      /**
       *  Find all the unique cities where the traders work.
       */
      public List<String> findAllUniqueCities(){
    	  return transactions.stream()
    	                     .map(transaction -> transaction.getTrader().getCity())
    	                     .distinct()
    	                     .collect(toList());
      }
      
      /**
       *  Find all traders from Cambridge and sort them by name.
       */
       public List<Trader> findAllTradersFromCambridge() {
          return transactions.stream()
                             .map(Transaction::getTrader)
                             .filter(trader -> trader.getCity().equals("Cambridge"))
                             .distinct()
                             .sorted(comparing(Trader::getName))
                             .collect(toList());
       }
      
      /**
       *  Get a string of all traders’ names sorted alphabetically.
       */
      public String getAllTradersNames(){
          return transactions.stream()
                      .map(transaction -> transaction.getTrader().getName())
                      .distinct()
                      .sorted()
                      .reduce("", (n1, n2) -> n1 + n2);
      
      }
      
      /**
       *  Are there any trader based in Milan?
       */
      public boolean areThereAnyTradeFromMilan(){
    	  return transactions.stream()
                             .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
      }
      
      /**
       * Update all transactions so that the traders from Milan are set to Cambridge.
       */
      public List<Transaction> updateTradersFromMilanToCambridge(){
    	  transactions.stream()
                      .map(Transaction::getTrader)
                      .filter(trader -> trader.getCity().equals("Milan"))
                      .forEach(trader -> trader.setCity("Cambridge"));
    	  
    	  return transactions;
      }
      
      /**
       *  Get the highest value in all the transactions.
       */
      public int getHighestTransactionValue(){
    	  return transactions.stream()
	                         .map(Transaction::getValue)
	                         .reduce(0, Integer::max);
      }
     
}


