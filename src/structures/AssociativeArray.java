
//got help from Diogo, Elizabeth, jayson, boston, audrey

package structures;                              

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Alyssa Trapp
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs. Creates key and values
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */

   //works
   public AssociativeArray<K, V> clone(){
    AssociativeArray<K, V> array = new AssociativeArray<K, V>();
    try{
    for (int i = 0; i < this.size; i++){
      array.set(pairs[i].key, pairs[i].value); 
     // pairs[this.size] = new KVPair <K,V> (pairs[i].key, pairs[i].value);
    }
  }catch(NullKeyException e){
    System.out.println("Null Key Exception" + e.getMessage());
  }                    
    return array;
  } // clone()

  /**
   * Convert the array to a string.
   */

   //only works when pairs.length == 0
  public String toString() {
    String str = "";
    if (pairs.length == 0){
      return "{}";
    }
    for (int i = 0; i < this.size; i++){
      if (i == 0){
        str = pairs[0].key + ": " + pairs[0].value;
      }
    else {
      str = str + "," + pairs[i].key + ": " + pairs[i].value;
    }
    }
    return "{" + str + "}";  
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
//maybe write method if full

//does every index of the array have a value in it
//check for size

public boolean full(){
  if(this.size == pairs.length){
    return true;
  }
  return false;
}
    //return this.size == this.pairs.length;
  //}




  //works
  public void set(K key, V value) throws NullKeyException {
    if (key == null){
      throw new NullKeyException();
    }
      for (int i = 0; i < this.size; i++) {
        // if (pairs.contains(key)){
        if (pairs[i].key.equals(key)) {
          pairs[i].value = value;
        }
    }
    if (this.full()){ 
      this.expand();
    }
    pairs[size++] = new KVPair<K,V> (key, value);
  }
  
  

  // value = pairs.indexof(key);

  // STUB
  // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */

   //why does it say it returns v, but then also returning null? and v is also not a parameter in the function?
   //do i want to call set in get 
   
   //works
  public V get(K key) throws KeyNotFoundException {
    try {
      for (int i = 0; i < this.size; i++){
        if (pairs[i].key.equals(key)) {
          return pairs[i].value;
        }
    }
   } catch (Exception e){
   }
    return null;
  }
    // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */

   //works
  public boolean hasKey(K key) {
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        return true;
      }
    }
    return false;// STUB
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */

   //works
  public void remove(K key){
    int shift = 0;
    for (int i = 0; i < this.size; i++){
      if (pairs[i].key.equals(key)) {
        pairs[i] = null;
        shift = i;
      for (int j = shift + 1; j < this.size; j++){
        pairs[j - 1] = pairs[j];
      }
      this.size--; 
      }      
    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */


  public int find(K key) throws KeyNotFoundException {
  try {
    for (int i = 0; i < this.size; i++){
      if (pairs[i].key.equals(key)){
        return i;
        }
      }
    } catch(Exception e){
    }
    return -1;
  } // find(K)
} // class AssociativeArray
