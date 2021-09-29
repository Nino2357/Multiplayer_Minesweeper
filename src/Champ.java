import java.util.*;

public class Champ {

	private int [] levelSizes = {10,20,30};
	private int [] levelNbMines = {10,40,90};
	private boolean[][] tabChamp;
	private int[][] tabChampNb;
	private int score;
	
	Random alea = new Random() ;
	
	public Champ(Level level) {
		tabChamp = new boolean [levelSizes[level.ordinal()]] [levelSizes[level.ordinal()]];
		tabChampNb= new int [levelSizes[level.ordinal()]] [levelSizes[level.ordinal()]];
	}
	
	public boolean[][] getTabChamp() {	
		return tabChamp;
	}
	public int[][] getTabChampNb() {
		this.affNb();
		return tabChampNb;
	}
	
	public int getDimX() {
		return (int) tabChamp.length;
	}
	public int getDimY( ) {
		return (int) tabChamp[0].length;
	}
	
	public void videMines() {
		for (int i=0 ; i < tabChamp.length ; i++) {
			for (int j=0; j < tabChamp[0].length; j++) {
				tabChamp[i][j]=false;
			}
		}
	}
	
	public void placeMines(Level level ) {
		for (int i= levelNbMines[level.ordinal()]; i != 0;) {   
			int x = alea.nextInt(tabChamp.length) ; // tirage au sort nb ∈ [0,DIM-1]  
			int y = alea.nextInt(tabChamp[0].length) ; // tirage au sort nb ∈ [0,DIM-1]
			if (!tabChamp[x][y]) {
				tabChamp[x][y]=true;
				i-- ;
			}	
		 }
	}
	public void affMines() {
		String ligne = new String();
		for (int i=0 ; i < tabChamp.length ; i++) {
			ligne = "";
			for (int j=0; j < tabChamp[0].length; j++) {
				if (tabChamp[i][j]==true) {
					ligne += "X";
				}
				else {
					ligne += "0";
				}		
			}
			System.out.println(ligne);
		}
	}
	public void affNb() {
		calculNb();
		String ligne = new String();
		for (int i=0 ; i < tabChampNb.length ; i++) {
			ligne = "";
			for (int j=0; j < tabChampNb[0].length; j++) {
				ligne += String.valueOf(tabChampNb[i][j]);
			
				}
			//System.out.println(ligne);
			}
			
	}
	private int testM(int x,int y) {
		/*System.out.println(x);
		System.out.println(y);*/
		if(x<0 || x>tabChamp.length-1 || y<0 || y>tabChamp.length-1 || tabChamp[x][y]==false) {
			return 0;
		}
		else {
			return 1;
		}
	}
	public void calculNb() {
		for (int i=0 ; i < tabChamp.length ; i++) {
			for (int j=0; j < tabChamp[0].length; j++) {
				this.tabChampNb[i][j]=testM(i-1,j-1)+testM(i-1,j)+testM(i-1,j+1)+testM(i,j-1)+testM(i,j+1)+testM(i+1,j-1)+testM(i+1,j)+testM(i+1,j+1);	
			}
		}
	}
	
	
	public void changeLvl(Level level) {
		System.out.println("test");
		this.tabChamp = new boolean [levelSizes[level.ordinal()]] [levelSizes[level.ordinal()]];
		this.tabChampNb= new int [levelSizes[level.ordinal()]] [levelSizes[level.ordinal()]];
		System.out.println("test0.1");
		videMines();
		placeMines(level);
		System.out.println("test0.2");
		affMines();
		System.out.println("test0.3");
		affNb();
		System.out.println("test0.4");
	}
	
	public void add1Flag() {
		this.score ++;
	}
	public void rem1Flag() {
		this.score --;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return this.score;
	}
}
