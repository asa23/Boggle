import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {

	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		// TODO Auto-generated method stub
		List<BoardCell> list = new ArrayList<BoardCell>();
		if(word.length()>(board.size()*board.size())) {
			return list;
		}
		for (int r=0; r<board.size(); r++){
			for (int c=0; c<board.size(); c++){
				if (findHelper(word, 0, r, c, board,list)) {
					return list;
				}
			}
		}
		return list;
	}




	// try each row until all are tried





	public boolean findHelper(String word, int index, int r, int c, BoggleBoard board, List<BoardCell> list){
		word=word.toLowerCase();
		int [] rdelta= {-1, -1, -1, 0, 0, 1, 1, 1};
		int [] cdelta= {-1, 0, 1, -1, 1, -1, 0, 1};
		if (index>= word.length()){
			return true;
		}
		if (r>=board.size() || r<0){
			return false;
		}
		if (c>=board.size() || c<0){
			return false;
		}
		BoardCell obj= new BoardCell(r, c);
		if(list.contains(obj)){
			return false;
		}
		int lastChar=index+1;
		if (word.substring(index, index+1).equals("Q")||word.substring(index, index+1).equals("q")){
			lastChar+=1;
		}
		if (lastChar<=word.length()&&word.substring(index, lastChar).equals(board.getFace(r, c).toLowerCase())){
			list.add(obj);
			for (int k=0; k<rdelta.length; k++){
				if(findHelper(word, lastChar, r+rdelta[k], c+cdelta[k], board, list)){
					return true;

				}
			}
			list.remove(obj);

		}
		
		return false;
	}	





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
