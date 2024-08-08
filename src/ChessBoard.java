/*
 * 이성수
 * 2020-11496
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private boolean check, checkmate, end;

	private int playedW, playedK;
	private int xBound, xDir;

	WhereArray mainArr;

	private boolean selected;

	private boolean[][] movedStatus;
	private int enPassantCol;
	
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {	// Only modify here
			// (x, y) is where the click event occured

			if(end) return;

			Piece clickButton = getIcon(x, y);
			PlayerColor clickColor = clickButton.color;
			PieceType clickType = clickButton.type;

			if(selected && clickColor != currPlayer()){
				if(!mainArr.whoGoesWhere[selY][selX][y][x]) return;

				if(clickType == PieceType.king && (selY - y == 2 || y - selY == 2)) return;

				Piece[][] nextBoard = modifyBoard(chessBoardStatus, selX, selY, x, y);
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(nextBoard[j][i].color != chessBoardStatus[j][i].color) setIcon(i, j, nextBoard[j][i]);
					}
				}
				deHighlight();

				if(selX == xBound && (selY == 0 || selY == 4 || selY == 7)) movedStatus[selY][selX] = true; // castling check
				if(getIcon(x, y).type == PieceType.pawn && selX + 2*xDir == x){
					movedStatus[selY][selX] = true;
					enPassantCol = y;
				} else enPassantCol = -100;

				mainArr.sync();
				checking(oppoPlayer());
				switchTurn();
			}
			else if(selected){
				if(clickType == PieceType.rook && getIcon(selX, selY).type == PieceType.king){
					int newY = selY + (y > selY ? 2 : -2);
					if(mainArr.whoGoesWhere[selY][selX][newY][x]){
						Piece[][] nextBoard = modifyBoard(chessBoardStatus, selX, selY, x, newY);
						for(int i = 0; i < 8; i++){
							for(int j = 0; j < 8; j++){
								if(nextBoard[j][i].color != chessBoardStatus[j][i].color) setIcon(i, j, nextBoard[j][i]);
							}
						}
						deHighlight();
		
						if(selX == xBound && (selY == 0 || selY == 4 || selY == 7)) movedStatus[selY][selX] = true; // castling check
						if(getIcon(x, newY).type == PieceType.pawn && selX + 2*xDir == x){
							movedStatus[selY][selX] = true;
							enPassantCol = newY;
						} else enPassantCol = -100;
		
						mainArr.sync();
						checking(oppoPlayer());
						switchTurn();
						return;
					}
				}

				deHighlight();
				highlight(x, y);
			}
			else{
				if(clickColor != currPlayer()) return;
				highlight(x, y);

				// castling hiding
				if(getIcon(x, y).type == PieceType.king){
					if(y+2 < 8) unmarkPosition(x, y+2);
					if(y-2 >= 0) unmarkPosition(x, y-2);
				}
			}
		}
	}

	// should be verified before putting in; no verification in the function.
	Piece[][] modifyBoard(Piece[][] currBoard, int selX, int selY, int clickX, int clickY){
		Piece selPiece = currBoard[selY][selX];
		Piece clickPiece = currBoard[clickY][clickX];

		Piece[][] newBoard = new Piece[8][8];
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				newBoard[j][i] = new Piece(currBoard[j][i].color, currBoard[j][i].type);

		int xBound_ = (selPiece.color == PlayerColor.white ? 7 : 0);
		int xDir_ = (selPiece.color == PlayerColor.white ? -1 : 1);

		newBoard[clickY][clickX] = new Piece(selPiece.color, selPiece.type);
		newBoard[selY][selX] = new Piece(PlayerColor.none, PieceType.none);

		// promotion
		if(selPiece.type == PieceType.pawn && clickX == 7 - xBound_)
			newBoard[clickY][clickX] = new Piece(selPiece.color, PieceType.queen);
		
		// en passant
		if(selPiece.type == PieceType.pawn && clickY != selY && clickPiece.type == PieceType.none)
			newBoard[clickY][clickX - xDir_] = new Piece(PlayerColor.none, PieceType.none);

		// castling
		if(selPiece.type == PieceType.king && (selY - clickY == 2 || clickY - selY == 2)){
			newBoard[(selY+clickY)/2][clickX] = new Piece(selPiece.color, PieceType.rook);
			newBoard[selY - clickY == 2 ? 0 : 7][clickX] = new Piece(PlayerColor.none, PieceType.none);
		}

		return newBoard;
	}

	void deHighlight(){
		selected = false;
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++) unmarkPosition(i, j);
	}
	void highlight(int x, int y){
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if(mainArr.whoGoesWhere[y][x][j][i]) markPosition(i, j);
		selected = true;
		selX = x;
		selY = y;
	}

	void checking(PlayerColor kingColor){
		check = false;
		checkmate = false;

		int xKing = 0, yKing = 0;
		itr: for(yKing = 0; yKing < 8; yKing++){
			for(xKing = 0; xKing < 8; xKing++){
				Piece status = chessBoardStatus[yKing][xKing];
				if(status.type == PieceType.king && status.color == kingColor){
					break itr;
				}
			}
		}
		if(yKing == 8){
			end = true;
			return;
		}
		checkingCheck(xKing, yKing);
		if(check) checkingCheckmate(xKing, yKing);
	}
	void checkingCheck(int xKing, int yKing){
		check = checkingChecks(mainArr, xKing, yKing);
	}
	boolean checkingChecks(WhereArray arr, int xKing, int yKing){
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				if(arr.whoGoesWhere[y][x][yKing][xKing]) return true;
		return false;
	}
	void checkingCheckmate(int xKing, int yKing){
		PlayerColor kingColor = chessBoardStatus[yKing][xKing].color;
		for(int sY = 0; sY < 8; sY++){
			for(int sX = 0; sX < 8; sX++){
				if(chessBoardStatus[sY][sX].color == kingColor){

					for(int cY = 0; cY < 8; cY++){
						for(int cX = 0; cX < 8; cX++){
							if(mainArr.whoGoesWhere[sY][sX][cY][cX]){
								Piece[][] vStatus = modifyBoard(chessBoardStatus, sX, sY, cX, cY);

								int xBound_ = (kingColor == PlayerColor.white ? 7 : 0);
								int xDir_ = (kingColor == PlayerColor.white ? -1 : 1);
								
								boolean[][] vmStatus = new boolean[8][8]; // virtual moved status
								int vEnP = enPassantCol; // virtual en passant status
								for(int i = 0; i < 8; i++)
									for(int j = 0; j < 8; j++)
										vmStatus[j][i] = movedStatus[j][i];
								if(sX == xBound_ && (sY == 0 || sY == 4 || sY == 7)) vmStatus[sY][sX] = true;
								if(vStatus[cY][cX].type == PieceType.pawn && sX + 2*xDir_ == cX){
									vmStatus[sY][sX] = true;
									vEnP = cY;
								} else vEnP = -100;

								int xKing2 = 0, yKing2 = 0;
								itr: for(yKing2 = 0; yKing2 < 8; yKing2++){
									for(xKing2 = 0; xKing2 < 8; xKing2++){
										Piece status = vStatus[yKing2][xKing2];
										if(status.type == PieceType.king && status.color == kingColor)
											break itr;
									}
								}

								WhereArray vWhere = new WhereArray(vStatus, vmStatus, vEnP);
								if(!checkingChecks(vWhere, xKing2, yKing2)){
									checkmate = false;
									return;
								}

							}
						}
					}
				}
			}
		}
		checkmate = true;
	}

	class WhereArray{
		public boolean[][][][] whoGoesWhere;

		public Piece[][] boardStatus;
		public boolean[][] moveStatus;
		public int enPassantColumn;

		WhereArray(){
			sync();
		}

		WhereArray(Piece[][] virtualBoardStatus, boolean[][] virtualMoveStatus, int virtualEnPassantCol){
			boardStatus = virtualBoardStatus;
			moveStatus = virtualMoveStatus;
			enPassantColumn = virtualEnPassantCol;
			setWhere(boardStatus, moveStatus, enPassantColumn);
		}

		void sync(){
			boardStatus = chessBoardStatus;
			moveStatus = movedStatus;
			enPassantColumn = enPassantCol;
			setWhere(boardStatus, moveStatus, enPassantColumn);
		}

		void setWhere(Piece[][] bStatus, boolean[][] mStatus, int enPasCol){
			whoGoesWhere = new boolean[8][8][8][8];
			for(int y = 0; y < 8; y++){
				for(int x = 0; x < 8; x++){
					Piece who = bStatus[y][x];
					PlayerColor whoColor = who.color;
					PieceType whoType = who.type;

					int xBound_ = (whoColor == PlayerColor.white ? 7 : 0);
					int xDir_ = (whoColor == PlayerColor.white ? -1 : 1);

					if(whoType == PieceType.king){
						for(int dy = -1; dy < 2; dy++)
							for(int dx = -1; dx < 2; dx++)
								setTrue(x, y, x+dx, y+dy);
					}
					if(whoType == PieceType.rook || whoType == PieceType.queen){ // Linear________________________________
						for(int prog = 1; setTrue(x, y, x+prog, y) && (bStatus[y][x+prog].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x-prog, y) && (bStatus[y][x-prog].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x, y+prog) && (bStatus[y+prog][x].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x, y-prog) && (bStatus[y-prog][x].type == PieceType.none); prog++){}
					}
					if(whoType == PieceType.bishop || whoType == PieceType.queen){ // Diagonal__________________________
						for(int prog = 1; setTrue(x, y, x+prog, y+prog) && (bStatus[y+prog][x+prog].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x+prog, y-prog) && (bStatus[y-prog][x+prog].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x-prog, y+prog) && (bStatus[y+prog][x-prog].type == PieceType.none); prog++){}
						for(int prog = 1; setTrue(x, y, x-prog, y-prog) && (bStatus[y-prog][x-prog].type == PieceType.none); prog++){}
					}
					if(whoType == PieceType.knight){ // Knight________________________________________________________
						setTrue(x, y, x+2, y+1);
						setTrue(x, y, x+2, y-1);
						setTrue(x, y, x+1, y+2);
						setTrue(x, y, x+1, y-2);
						setTrue(x, y, x-1, y+2);
						setTrue(x, y, x-1, y-2);
						setTrue(x, y, x-2, y+1);
						setTrue(x, y, x-2, y-1);
					}
					if(whoType == PieceType.pawn){ // Pawn________________________________________________________________
						boolean attackable = false;
						if(setTrue(x, y, x+xDir_, y-1)){
							if(bStatus[y-1][x+xDir_].color == whoColor)
								setFalse(x, y, x+xDir_, y-1);
							else if(bStatus[y-1][x+xDir_].color == PlayerColor.none)
								setFalse(x, y, x+xDir_, y-1);
							else attackable = true;
						}
						if(setTrue(x, y, x+xDir_, y+1)){
							if(bStatus[y+1][x+xDir_].color == whoColor)
								setFalse(x, y, x+xDir_, y+1);
							else if(bStatus[y+1][x+xDir_].color == PlayerColor.none)
								setFalse(x, y, x+xDir_, y+1);
							else attackable = true;
						} 
						if((enPasCol+1 == y || enPasCol-1 == y) && (x == xBound_+4*xDir_))
							setTrue(x, y, x+xDir_, enPasCol);
						
						if(!attackable && bStatus[y][x+xDir_].type == PieceType.none){
							setTrue(x, y, x+xDir_, y);
							if(x - xDir_ == xBound_ && bStatus[y][x+2*xDir_].type == PieceType.none)
								setTrue(x, y, x+2*xDir_, y);
						}
					}

					for(int i=0; i<8; i++)
						for(int j=0; j<8; j++)
							if(bStatus[j][i].color == whoColor) setFalse(x, y, i, j);

					if(whoType == PieceType.king){
						if(rightCastlingOK(x, y, bStatus, mStatus)) setTrue(x, y, x, y+2);
						if(leftCastlingOK(x, y, bStatus, mStatus)) setTrue(x, y, x, y-2);
					}
				}
			}
		}

		boolean setTrue(int whoX, int whoY, int whereX, int whereY){
			if(0 <= whereX && whereX < 8 && 0 <= whereY && whereY < 8){
				whoGoesWhere[whoY][whoX][whereY][whereX] = true;
				return true;
			}
			return false;
		}
		boolean setFalse(int whoX, int whoY, int whereX, int whereY){
			if(0 <= whereX && whereX < 8 && 0 <= whereY && whereY < 8){
				whoGoesWhere[whoY][whoX][whereY][whereX] = false;
				return true;
			}
			return false;
		}

		boolean rightCastlingOK(int xKing, int yKing, Piece[][] bStatus, boolean[][] mStatus){
			PlayerColor kingColor = bStatus[yKing][xKing].color;
			int xBound_ = (kingColor == PlayerColor.white ? 7 : 0);
			int xDir_ = (kingColor == PlayerColor.white ? -1 : 1);

			if(checkingChecks(this, xKing, yKing)) return false;
			if(mStatus[4][xBound_] || mStatus[7][xBound_]) return false;
			if(bStatus[5][xBound_].type != PieceType.none || bStatus[6][xBound_].type != PieceType.none) return false;

			Piece[][] afterCastling = modifyBoard(bStatus, xKing, yKing, xKing, yKing+2);
			WhereArray whereAfterCastling = new WhereArray(afterCastling, new boolean[8][8], -100);
			if(checkingChecks(whereAfterCastling, xKing, yKing+2)) return false;

			return true;
		}
		boolean leftCastlingOK(int xKing, int yKing, Piece[][] bStatus, boolean[][] mStatus){
			PlayerColor kingColor = bStatus[yKing][xKing].color;
			int xBound_ = (kingColor == PlayerColor.white ? 7 : 0);
			int xDir_ = (kingColor == PlayerColor.white ? -1 : 1);

			if(checkingChecks(this, xKing, yKing)) return false;
			if(mStatus[4][xBound_] || mStatus[0][xBound_]) return false;
			for(int i = 1; i < 4; i++)
				if(bStatus[i][xBound_].type != PieceType.none) return false;

			Piece[][] afterCastling = modifyBoard(bStatus, xKing, yKing, xKing, yKing-2);
			WhereArray whereAfterCastling = new WhereArray(afterCastling, new boolean[8][8], -100);
			if(checkingChecks(whereAfterCastling, xKing, yKing-2)) return false;

			return true;
		}
	}



	boolean isWhiteTurn(){
		return playedW <= playedK;
	}
	PlayerColor currPlayer(){
		return isWhiteTurn() ? PlayerColor.white : PlayerColor.black;
	}
	PlayerColor oppoPlayer(){
		return !isWhiteTurn() ? PlayerColor.white : PlayerColor.black;
	}
	void switchTurn(){
		if(isWhiteTurn())
			playedW++;
		else
			playedK++;

		xBound = isWhiteTurn() ? 7 : 0;
		xDir = isWhiteTurn() ? -1 : 1;

		showTurn();
	}
	void showTurn(){
		String status = isWhiteTurn() ? "WHITE's TURN" : "BLACK's TURN";
		if(check) status += " / CHECK";
		if(checkmate) status += "MATE";
		if(end) status = "END";
		setStatus(status);
	}

	
	void onInitiateBoard(){
		playedW = 0;
		playedK = 0;

		selected = false;
		xBound = 7;
		xDir = -1;

		check = false;
		checkmate = false;
		end = false;

		movedStatus = new boolean[8][8];
		enPassantCol = -100;

		mainArr = new WhereArray();

		deHighlight();
		showTurn();
	}
}