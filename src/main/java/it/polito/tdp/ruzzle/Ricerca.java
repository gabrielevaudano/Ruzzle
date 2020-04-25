package it.polito.tdp.ruzzle;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ruzzle.model.Board;
import it.polito.tdp.ruzzle.model.Pos;

public class Ricerca {
	public List<Pos> trovaParola(String parola, Board board) {
		for (Pos p: board.getPositions())
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				// iniziare la ricorsione potenzialmente
				List<Pos> percorso = new ArrayList<Pos>();
				percorso.add(p);
				
				if (cerca(parola, 1, percorso, board))
					return percorso;
			}
		
		return null;
	}

	private boolean cerca(String parola, int i, List<Pos> percorso, Board board) {
		if (parola.length()==i)
			return true;
		
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		for (Pos k : adiacenti)
			if (!percorso.contains(k) && parola.charAt(i) == board.getCellValueProperty(k).get().charAt(0))
			{
				percorso.add(k);
				if(cerca(parola, i+1, percorso, board))
					return true;
				
				percorso.remove(k);
			}
		
		return false;
	}


}


