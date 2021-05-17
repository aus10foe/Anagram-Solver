/* Austin Faux
   CSE: 143
   TA: Ken Aragon
   4-11-19
   
   This program organizes a string alphabetically and allows a user to change
   the letters in the string by adding, subtracting, and setting values. 
   
*/

public class LetterInventory {
   
   private int[] inventory; // holds the counts of each letter in their own index
   private int size; // number of letters in given string. 
   
   public static final int LETTERS = 26; // array of size 26 for 26 letters in English Alphabet. 
   
   // post: Creates an inventory of the counts of each letter ignoring caps stored in an 
   //       alpha-numeric array that stores the character 'a' at index 0 and so on. Also 
   //       increments size for every character passed thru data. 
   public LetterInventory(String data) {
      inventory = new int[LETTERS];   
      char letter; // Each character of data
      size = 0; // initial size of inventory
      for (int i = 0; i < data.length(); i++) { 
         letter = data.toLowerCase().charAt(i); // changes each character to be lower case
         if (Character.isLetter(letter)) { // check on character type i.e. '4' vs. 'a' 
            inventory[letter - 'a']++; // index of the letter
            size++; // counts the initial size of the array
         }
      }
   }
   
   // pre: letter must be a letter (throws IllegalArgumentException if not)
   // post: returns the count of letter stored in inventory. 
   public int get(char letter) {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
      return inventory[Character.toLowerCase(letter) - 'a']; 
   }
   
   // pre: letter must be a letter  and value must be positive, 
   //      (throws IllegalArgumentException if not).
   // post: changes size to size + the difference between passed value and stored value.
   //       changes inventory count to be value at index letter. 
   public void set(char letter, int value){
      if(!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException();
      }
      letter = Character.toLowerCase(letter);
      size += value - inventory[letter - 'a'];
      inventory[letter - 'a'] = value;
      
   }
   
   // post: returns the size of the data string.  
   public int size() {
      return size; 
   }
   
   // post: returns true if the inventory is empty.  
   public boolean isEmpty() {
      return size == 0; 
   }
   
   // post: returns the String rearranged alphabetically and surrounded in brackets.
   public String toString() {
      String word = "[";
      for(int index = 0; index < LETTERS; index++) { // goes through each letter index
         for(int letterC = 0; letterC < inventory[index]; letterC++) { //counts of each letter          
            word += (char) (index + 'a');  
         }
      }
      word += "]";
      return word;
   }
   
   // post: constructs & returns new LetterInventory object and  
   //       adds this.inventory and other.inventory to the new inventory. 
   //       Changes new inventory size to be this size + other size.     
   public LetterInventory add(LetterInventory other) {
      LetterInventory addOn = new LetterInventory("");
      for (int i = 0; i < LETTERS; i++) {
         addOrSub(1, other, addOn, i); // adds two inventories together
      }
      return addOn;  
   }
   
   // post: constucts and returns new LetterInventory object that is the difference in inventory 
   //       of this.inventory and other.inventory. Returns null if the difference results
   //       in a negative value.   
   public LetterInventory subtract(LetterInventory other){
      LetterInventory takeOff = new LetterInventory("");
      for (int i = 0; i < LETTERS; i++) { 
         if (inventory[i] >= other.inventory[i]) {
            addOrSub(-1, other, takeOff, i); // subtracts the two inventories
         } else {
            return null;
         }
      }
      return takeOff;
   }
   
   // post: Either add or subtracts the two inventories depending on k's value (either 1 or -1)
   //       and also changes the size of temp. 
   private LetterInventory addOrSub ( int k, LetterInventory other, LetterInventory temp, int i) {
      temp.inventory[i] = inventory[i] + k * other.inventory[i]; // changes r.inventory to 
                                                                 // the sum or difference. 
      temp.size += temp.inventory[i]; // changes the size of r. 
      return temp;
   }

}
   

        

   
   
