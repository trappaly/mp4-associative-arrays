
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
   * Create a clone of this AssociativeArray.
   */

  public AssociativeArray<K, V> clone() {
    // Build an array
    AssociativeArray<K, V> arr = new AssociativeArray<K, V>();
    try {
      for (int i = 0; i < this.size; i++) {
        // Clones the array
        arr.set(pairs[i].key, pairs[i].value);
      } // for
    } catch (NullKeyException e) {
    } // catch
    // Returns cloned array
    return arr; 
  } // clone()

  /**
   * Convert the array to a string.
   */

  public String toString() {
    // Initializes string
    String str = "";
    // If the associative array is empty, return "{}"
    if (pairs.length == 0) {
      return "{}";
    } // if
    for (int i = 0; i < this.size; i++) {
      // If we are on the first iteration of the associative array
      if (i == 0) {
        str = pairs[0].key + ": " + pairs[0].value;
      } // if
      // If we are on anything, but the first iteration of the associative array
      else {
        str = str + "," + pairs[i].key + ": " + pairs[i].value;
      } // else
    } // for
    // Returns the str inside of { }
    return "{" + str + "}";
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Checks to see if the associative array is full
   */

  public boolean full() {
    // If the size of the array is equal to the number of pairs in the array, return true
    if (this.size == pairs.length) {
      return true;
    } // if
    // Otherwise return false
    return false;
  } // full()

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */


   // need null key exception and key not found exception 
  public void set(K key, V value) throws NullKeyException {
    // If key is equal to null, throw NullKeyException
    try {
    if (key == null) {
      throw new NullKeyException();
    } // if 
      // If the key exists, set the index of the pairs of the value equal to the new
      // value
      System.out.println ("key");
      if (hasKey(key)) {
        System.out.println ("after if:");
        pairs[find(key)].value = value;
      } // if
      else {
        throw new KeyNotFoundException();
      } // else
    // If the array is full, expand the array
    if (this.full()) {
      this.expand();
    } // if
    // Increments the size of the array of pairs and creates a new KVPair at that
    // array
    pairs[size++] = new KVPair<K, V>(key, value);
  } catch (NullKeyException e){

  } // catch 
  catch (KeyNotFoundException e){
    
  } // catch 
  } // set(K, V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not
   *                              appear in the associative array.
   */


   // this is right because rebelskey helped 
  public V get(K key) throws KeyNotFoundException {
      int i = find(key);
      return pairs[i].value;
    // If the key doesn't exist, return null
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */


   // need to call find key method 
   // find key returns int 
  //public boolean hasKey(K key) throws KeyNotFoundException{
    //return ((pairs[find(key)]) == key);
  //} // hasKey(K)

// think hasKey is broken somehow 
   public boolean hasKey(K key) {
    try {
      if ((pairs[find(key)]).equals(key)){
        return true;
      } // if
  } // try
  catch (KeyNotFoundException e){
  } // catch  
  return false;
} // hasKey (key)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */

  public void remove(K key) {
    try {
    // Initializes the shift to 0
    int shift = 0;
    // If the key exists, set the key equal to null
      //for (int i = 0; i < this.size; i++) {
        if (hasKey(key)) {
        pairs[find(key)] = null;
        // Sets the shift equal to the index which the key is located at
        shift = find(key);
        // Shifts the associative array
        for (int j = shift + 1; j < this.size; j++) {
          pairs[j - 1] = pairs[j];
        } // for
        // Decreases the size of the array
        this.size--;
      } // if
    } catch (KeyNotFoundException e){
      
    } // catch
    }// remove(K)

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
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      } // if
    } // for
    throw new KeyNotFoundException();
    //return -1;
    //throw new KeyNotFoundException();  
  } // find(K)
} // class AssociativeArray
