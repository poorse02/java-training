package clean.code.chess.requirements;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
//        throw new UnsupportedOperationException("Need to implement Pawn.Move()") ;

        //move, but only if the position is free and if the position is valid\
        if(movementType == MovementType.MOVE) {
            System.out.println("~~~~~~~~~~~~");
            System.out.println("Moving");
            System.out.println("    Wanted positions:" + newX + ", " + newY);
            System.out.println("    is new position free: "+this.checkIfPositionFree(newX, newY));
            System.out.println("    is new position legal: "+this.chessBoard.IsLegalBoardPosition(newX, newY));
            this.checkIfPositionFree(newX,newY);


            //important part
            //we define black pawns being on top (rows 6 & 7) and white on the bottom (rows 0 & 1) initially
            if(this.getPieceColor() == PieceColor.BLACK){
                if (this.checkIfPositionFree(newX, newY) && this.chessBoard.IsLegalBoardPosition(newX, newY)
                    && newY < this.getYCoordinate() && newX == this.getXCoordinate()) {
                    this.setXCoordinate(newX);
                    this.setYCoordinate(newY);
                }
            }

            if(this.getPieceColor() == PieceColor.WHITE){
                if (this.checkIfPositionFree(newX, newY) && this.chessBoard.IsLegalBoardPosition(newX, newY)
                        && newY > this.getYCoordinate() && newX == this.getXCoordinate()) {
                    this.setXCoordinate(newX);
                    this.setYCoordinate(newY);
                }
            }

            //

            if (!this.checkIfPositionFree(newX, newY)) {
                System.out.println("    Checking other occupied positions: ");
                System.out.println("        black pawns");
                for (int i = 0; i < this.chessBoard.getBlackPawnList().size(); i++) {
                    System.out.print("          position for "+ i + " piece: ");
                    System.out.print(this.chessBoard.getBlackPawnList().get(i).getXCoordinate());
                    System.out.println(" " + this.chessBoard.getBlackPawnList().get(i).getYCoordinate());
                }


                System.out.println("        white pawns");
                for (int i = 0; i < this.chessBoard.getWhitePawnList().size(); i++) {
                    System.out.print("          position for "+ i + " piece: ");
                    System.out.print(this.chessBoard.getWhitePawnList().get(i).getXCoordinate());
                    System.out.println(" " + this.chessBoard.getWhitePawnList().get(i).getYCoordinate());
                }

            }
        }

    }

    public boolean checkIfPositionFree(int xCoordinate, int yCoordinate){
        //check white pawns
        int check = 0;
        for (int i = 0; i <this.chessBoard.getWhitePawnList().size();i++){
            if((this.chessBoard.getWhitePawnList().get(i).getXCoordinate() == xCoordinate && this.getXCoordinate() != xCoordinate)
                    || this.chessBoard.getWhitePawnList().get(i).getYCoordinate() == yCoordinate && this.getYCoordinate() != yCoordinate )
                check = 1;
        }

        //check black pawns
        for (int i = 0; i <this.chessBoard.getBlackPawnList().size();i++){
            if(this.chessBoard.getBlackPawnList().get(i).getXCoordinate() == xCoordinate
                    || this.chessBoard.getBlackPawnList().get(i).getYCoordinate() == yCoordinate)
                check = 1;
        }

        if(check == 1)
            return false;
        else return true;
    }





    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
