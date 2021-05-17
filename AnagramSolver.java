/* Austin Faux
   CSE: 143
   TA: Ken Aragon
   5-24-19
   
   Exhaustive search through a dictionary to find every possible anagram of given size
   for a given word. 
   Max > 0 produces anagrams of such size. Max = 0 produces every possible anagram. 
   
*/

import java.util.*;

public class AnagramSolver {
   private Map<String, LetterInventory> dict; // large dictionary
   private List<String> list1; // to  maintain order
   
   // Post: Constructs a dictionary of words from the given file. 
   public AnagramSolver(List<String> list) {
      dict = new HashMap<String, LetterInventory>();   
      list1 = new ArrayList<String>();
      list1 = list; // sets field
      for(String word: list) {
         dict.put(word, new LetterInventory(word));        
      }   
   }
   
   // Pre: max > 0 (throw Illegal Argument Exception if not)
   // Post: Prints each anagram of the given size for the user given phrase
   //       and every possible anagram if max = 0. 
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      List<String> smallDict = new ArrayList<String>();
      LetterInventory word = new LetterInventory(s);
      for (String key: list1) { // optimize dictionary. 
         if(word.subtract(dict.get(key)) != null) {
            smallDict.add(key);
         }
      }    
      Stack<String> stack = new Stack<String>();
      explore(word, max, stack, smallDict);      
   }
   
   
   // Post: finds every possible anagram and prints it. 
   private void explore(LetterInventory other, int max, Stack<String> stack, 
                                       List<String> smallDict) {    
      if(other.isEmpty()) { // base case
         System.out.println(stack);
      } else { // recursion
         for(String key: smallDict) {
            if (other.subtract(dict.get(key)) != null) {
               if (stack.size() < max || max == 0 ) {
                  stack.push(key);
                  explore(other.subtract(dict.get(key)), max, stack, smallDict);
                  other.add(dict.get(key));
                  if (!stack.empty()) {
                     stack.pop();
                  }
               }              
            }
         }         
      }  
   } 
}

