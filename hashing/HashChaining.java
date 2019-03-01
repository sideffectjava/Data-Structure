package hashing;

import java.util.ArrayList;

public class HashChaining<K,V> {

	int capacity;
	double loadFactor=0.75;
	ArrayList<HashArray<K,V>> hashArray;
	int size;
	 
	HashChaining(){
		capacity=16;
		size=0;
		hashArray = new ArrayList<>();
		for(int i =0;i<capacity;i++)
			hashArray.add(i,null);
	}
	class HashArray<K,V>{
		K key;
		V value;
		
		HashArray<K,V> next=null;
		HashArray(K key,V value)
		{
			this.key=key;
			this.value=value;
		}
	}
	public int myHashCode(K key)
	{
		return key.hashCode() % capacity;
	}
	public int getSize()
	{
		return size;
	}
	public void put(K key,V value)
	{
		int index=myHashCode(key);	
		HashArray<K,V> head=hashArray.get(index);
			while(head!=null)
			{	
				if(head.key.equals(key))
				{
					head.value=value;
					return;
				}
				head=head.next;
			}
			size++;
			head=hashArray.get(index);
			HashArray<K,V> element = new HashArray<>(key,value);
			element.next=head;
			hashArray.set(index, element);
			
			//if size reaches load factor then double the size
			if(size>=(int)capacity*loadFactor)
			{
				capacity=capacity*2;
				ArrayList<HashArray<K, V>> temp = hashArray; 
				
				for(int i=0;i<capacity;i++)
					hashArray.add(i, null);
				
				for(HashArray<K,V> ha:temp)
				{
					while(ha!=null)
					{
						this.put(ha.key, ha.value);
						ha=ha.next;
					}
				}
				
			}
	}
	public V get(K key) {
		int index=myHashCode(key);
		if(hashArray.get(index)==null)
			return null;
		HashArray<K,V> head = hashArray.get(index);
		while(head!=null)
		{
			if(head.key.equals(key))
				return head.value;
			head=head.next;
		}
		return null;
	}
	
	public void traverse(K key)
	{
		int index=myHashCode(key);
		HashArray<K,V> head = hashArray.get(index);
		while(head!=null)
		{
			System.out.println(head.key+" "+head.value);
			head=head.next;
		}
		
	}
	public V remove(K key)
	{
		int index=myHashCode(key);
		HashArray<K,V> head=hashArray.get(index);
		HashArray<K,V> p=head;
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				if(p==head)
				{
					hashArray.add(index, hashArray.get(index).next);
					return head.value;
				}
				
				p.next=head.next;
				hashArray.add(index, hashArray.get(index));
				return head.value;
			}
				p=head;
				head=head.next;
		}
		return null;
	}
	
	public static void main(String[] args) {
				
		HashChaining<Integer,String> hc = new HashChaining<>();
		hc.put(50, "sid");
		hc.put(85, "sachin");
		hc.put(700, "nikhil");
		hc.put(92, "chandan");
		hc.put(76, "sattu");
		
		System.out.println(hc.get(50));
		System.out.println(hc.get(85));
		System.out.println(hc.get(700));
		System.out.println(hc.get(92));
		System.out.println(hc.get(76));
		
		hc.remove(700);
		
		System.out.println("------------------------------------");
		
		System.out.println(hc.get(50));
		System.out.println(hc.get(85));
		System.out.println(hc.get(700));
		System.out.println(hc.get(92));
		System.out.println(hc.get(76));
		
	}
}
