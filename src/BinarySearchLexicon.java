import java.util.*;



public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    public LexStatus wordStatus(String s) {
    	s=s.toLowerCase();
    	int index=Collections.binarySearch(myWords, s);
    	if (index>=0){
            return LexStatus.WORD;
        }
        if(((index*-1)-1)<myWords.size()){
        	String insertHere = myWords.get((index*-1)-1);
        	if (insertHere.startsWith(s)){
            	return LexStatus.PREFIX;
        	}
        }
        return LexStatus.NOT_WORD;

        	
      	}
        // You need to make this code use Binary Search
        
     

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
