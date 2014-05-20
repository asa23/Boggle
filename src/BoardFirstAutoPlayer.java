
import java.util.ArrayList;
import java.util.List;


public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
    	clear();
    	StringBuilder sb = new StringBuilder();
		List<BoardCell> list = new ArrayList<BoardCell>();
    	for (int r=0; r<board.size(); r++){
    		for (int c=0; c<board.size(); c++){
    			findValidWordsHelper(board, list, minLength, lex, r,c, sb);
    		}
    	}
    }
    
    public void findValidWordsHelper(BoggleBoard board, List<BoardCell> list, int minLength, ILexicon lex, int r, int c, StringBuilder sb){
    	int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
    	int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
    	if (r>=board.size() || r<0){
    		return;
    	}
		if(c>=board.size() || c<0) {
			return;
		}
    	BoardCell obj = new BoardCell(r,c);
    	if (list.contains(obj)){
    		return;
    	}
    	list.add(obj);
    	sb.append(board.getFace(r, c));
    	if((lex.wordStatus(sb).equals(LexStatus.WORD)) || (lex.wordStatus(sb).equals(LexStatus.PREFIX))){
    		if ((lex.wordStatus(sb)).equals(LexStatus.WORD)&&sb.length()>=minLength){
    			add(sb.toString());
    		}
    		for(int k=0; k < rdelta.length; k++){
    			findValidWordsHelper(board, list, minLength, lex, r+rdelta[k], c+cdelta[k], sb);
    		}
    		list.remove(obj);
    		if (checkIfQ(r, c, board)){
    			sb.delete(sb.length()-2, sb.length());
    		}
    		if (!checkIfQ(r, c, board)){
    			sb.delete(sb.length()-1, sb.length());
    		}
    	}
    	if((lex.wordStatus(sb).equals(LexStatus.NOT_WORD))){
    		list.remove(obj);
    		if (checkIfQ(r, c, board)){
    			sb.delete(sb.length()-2, sb.length());
    		}
    		if (!checkIfQ(r, c, board)){
    			sb.delete(sb.length()-1, sb.length());
    		}
    	}
    	
    }
   
    public boolean checkIfQ(int r, int c, BoggleBoard board){
    	if (board.getFace(r, c).length()>1){
    		return true;
    	}
    	return false;
    }
    
}


    

