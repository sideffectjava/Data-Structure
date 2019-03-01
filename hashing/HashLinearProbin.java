package hashing;

public class HashLinearProbin<K,V> {

	int capacity;
	HashArray<K,V> hashArray[];
	HashLinearProbin(int capacity)
	{
		this.capacity=capacity;
		hashArray= new HashArray[capacity];
		for(int i=0;i<capacity;i++)
			hashArray[i]=null;			
	}
		
	class HashArray<K,V>
	{
		K key;
		V value;
		HashArray(K key,V value)
		{
			this.key=key;
			this.value=value;
		}
	}
	public int hash1(K key)
	{
		return key.hashCode() % capacity;
	}
	public void put(K key,V Value)
	{
		int index=hash1(key);
		if(hashArray[index]!=null)
		{
			for(int i=1;i<=capacity;i++)
			{
				index=(index+i) % capacity;
				if(hashArray[index] == null)
					break;
			}
		}
		hashArray[index]=new HashArray<>(key, Value);
	}
	
	public V get(K key)
	{
		
		int index=hash1(key);
		if(hashArray[index]==null)
			return null;
		if(hashArray[index].key.equals(key))
		{
			return hashArray[index].value;
		}
		else
		{
			for(int i=1;i<=capacity;i++)
			{
				index=(index+i) % capacity; 
				if(hashArray[index]!=null && hashArray[index].key.equals(key))
					return hashArray[index].value;
			}
			return null;
		}

	}
	
	public void delete(K key)
	{
		int index=hash1(key);
		
		if(hashArray[index]!=null && hashArray[index].key.equals(key))
			 hashArray[index]=null;
		else
		{
			for(int i=1;i<=capacity;i++)
			{
				index=(index+i) % capacity; 
				if(hashArray[index]!=null && hashArray[index].key.equals(key))
					 hashArray[index]=null;
			}
		}
	}
	
	public static void main(String[] args) {
		
		HashLinearProbin<Integer,String> hashLinearProbin = new HashLinearProbin<>(16);
		hashLinearProbin.put(50, "sid");
		hashLinearProbin.put(700, "nikhil");
		hashLinearProbin.put(76, "sattu");
		hashLinearProbin.put(85, "sachin");
		hashLinearProbin.put(92, "chandan");
		
		System.out.println(hashLinearProbin.get(50));
		System.out.println(hashLinearProbin.get(700));
		System.out.println(hashLinearProbin.get(76));
		System.out.println(hashLinearProbin.get(85));
		System.out.println(hashLinearProbin.get(92));
		
		hashLinearProbin.delete(76);
		hashLinearProbin.put(76, "cvdsdv");
		hashLinearProbin.put(76, "dgvdfvgrfg");
		System.out.println("--------------------------");
		
		System.out.println(hashLinearProbin.get(50));
		System.out.println(hashLinearProbin.get(700));
		System.out.println(hashLinearProbin.get(76));
		System.out.println(hashLinearProbin.get(85));
		System.out.println(hashLinearProbin.get(92));
		
		
	}
}
