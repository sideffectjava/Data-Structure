Hashtable Implementation using below ways:

  1) Separate Chaining: 
     Each cell of hash table point to a linked list of records that have same hash function value. 
      

  2) Open Addressing: 
     All elements are stored in the hash table itself. Each table entry contains either a record or NULL. 
     If there is any collision (i.e. two different elements have same hash value) then store the element to next index.
