package clean.code.chess.requirements;

import java.util.ArrayList;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;
    private ArrayList<Pawn> whitePawns = new ArrayList<>();
    private ArrayList<Pawn> blackPawns = new ArrayList<>();

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public ArrayList<Pawn> getWhitePawnList(){
        return whitePawns;
    }

    public void setWhitePawns(ArrayList<Pawn> array){
        this.whitePawns = array;
    }

    public ArrayList<Pawn> getBlackPawnList(){
        return blackPawns;
    }

    public void setBlackPawns(ArrayList<Pawn> array){
        this.blackPawns = array;
    }


    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
            if (this.IsLegalBoardPosition(xCoordinate,yCoordinate)
                    && this.checkIfPositionFree(xCoordinate,yCoordinate)){
                System.out.println("~~~~~~~~~~~");
                System.out.println("Adding pawn: ");
                System.out.println("Desired position " + xCoordinate + ", " +yCoordinate);

                pawn.setXCoordinate(xCoordinate);
                System.out.println("position x set " + pawn.getXCoordinate());
                pawn.setYCoordinate(yCoordinate);
                System.out.println("position y set " + pawn.getYCoordinate());
                pawn.setChessBoard(this);

                if(pieceColor == PieceColor.BLACK && this.getBlackPawnList().size() < MAX_BOARD_WIDTH) {
                    blackPawns.add(pawn);

                }

                else if(pieceColor == PieceColor.WHITE && this.getBlackPawnList().size() < MAX_BOARD_WIDTH)
                    whitePawns.add(pawn);
                else{
                    pawn.setXCoordinate(-1);
                    pawn.setYCoordinate(-1);

            }

            }
            System.out.println("after adding: ");
            for(int i = 0 ; i < blackPawns.size() ; i++){
                System.out.println("piece at position "+ i + " w/ coordinates: "+
                        blackPawns.get(i).getXCoordinate()+", "+ blackPawns.get(i).getYCoordinate());
        }

    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if ( xCoordinate >= 0 && xCoordinate<=7 && yCoordinate >=0 && yCoordinate <=7)
            return true;
        else return false;
    }

    public boolean checkIfPositionFree(int xCoordinate, int yCoordinate){
        System.out.println("~~~~~~~");
        System.out.println("Check if position free: ");
        //check white pawns
        int check = 0;
        for (int i = 0; i <whitePawns.size();i++){
            if(whitePawns.get(i).getXCoordinate() == xCoordinate || whitePawns.get(i).getYCoordinate() == yCoordinate){
                check = 1;
                System.out.println("white pawn number " + i + "has coordinates: "+ whitePawns.get(i).getXCoordinate()
                        + ", " + whitePawns.get(i).getYCoordinate());
            }

        }

        //check black pawns
        for (int i = 0; i <blackPawns.size();i++){
            if(blackPawns.get(i).getXCoordinate() == xCoordinate || blackPawns.get(i).getYCoordinate() == yCoordinate){
                System.out.println("black pawn number " + i + "has coordinates: "+ blackPawns.get(i).getXCoordinate()
                        + ", " + blackPawns.get(i).getYCoordinate());
                check = 1;
            }

        }

        if(check == 1)
            return false;
        else return true;
    }
}
