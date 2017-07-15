import java.util.Scanner;

/**
 * Created by zzzxxx on 15.07.2017.
 */
public class Tic {
    public static final int USER = 1;
    public static final int COMPUTER = 2;
    public static final int EMPTY_1 = 0;
    public static final int SIZE = 3;
    public static int[][] board;
    public static final int COLUMN = 3;
    public static final int ROW = 3;

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
      playGame();
    }

    static void clearBoard() {
        int col, row;
        board = new int[SIZE][SIZE];
        for (row = 0; row < SIZE; row++)
            for (col = 0; col < SIZE; col++)
                board[row][col] = EMPTY_1;
    }

    //компьютер ходит
    public static void computerMove() {

        int col;
        int row;
        int count;
        int square;
        count = 0;
        for (row = 0; row < SIZE; row++)
            for (col = 0; col < SIZE; col++)
                if (board[row][col] == EMPTY_1)
                    count++;
        if (count == 0) {
            System.out.println("mistake in computerMove()method");
            return;
        }
        square = (int) (Math.random() * count);
        count = 0;
        for (row = 0; row < SIZE; row++)
            for (col = 0; col < SIZE; col++)
                if (board[row][col] == EMPTY_1) {
                    if (count == square) {
                        board[row][col] = COMPUTER;
                        System.out.println("Мох ход  ряд" + (row + 1) + "столбец" + (col + 1) + ".");
                    }
                    count++;
                }

    }



        public static void outputField() {

            for (int row = 0; row < ROW; row++) {
                for (int column = 0; column < COLUMN; column++) {
                    System.out.print(board[row][column]);
                    if (column != COLUMN - 1) {
                        System.out.print("|");
                    }
                }

                System.out.println();
                if (row != ROW - 1) {
                    System.out.println("-----------");

                }}
        }

        static boolean endgame() {
            int col;
            int row;
            int count;
            int winner;
            winner = EMPTY_1;
            for (row = 0; row < SIZE; row++) {
                count = 0;
                if (board[row][0] != EMPTY_1)
                    for (col = 0; col < SIZE; col++)
                        if (board[row][0] == board[row][col])
                            count++;
                if (count == SIZE)
                    winner = board[row][0];
            }
            for (col = 0; col < SIZE; col++) {
                count = 0;
                if (board[0][col] != EMPTY_1)
                    for (row = 0; row < SIZE; row++)
                        if (board[0][col] == board[row][col])
                            count++;
                if (count == SIZE)
                    winner = board[0][col];
            }
            count = 0;
            if (board[0][SIZE - 1] != EMPTY_1)
                for (row = 0; row < SIZE; row++)
                    if (board[0][SIZE - 1] == board[row][SIZE - row - 1])
                        count++;
            if (count == SIZE)
                winner = board[0][SIZE - 1];
            if (winner != EMPTY_1) {

                if (winner == USER)
                    System.out.println("Ты победил");
                else if (winner == COMPUTER)
                    System.out.println("Компьютер выиграл");
                else
                    System.out.println("Ошибка");
                return true;
            }
            count = 0;
            for (row = 0; row < SIZE; row++)
                for (col = 0; col < SIZE; col++)
                    if (board[row][col] == EMPTY_1)
                        count++;
            if (count == 0) {
                System.out.println("");
                return true;
            }
        return false;
      }

    static void userMoves()
    {
        boolean asking;               // true until we get valid input
        int col, row;
        asking = true;
        while (asking) {
            System.out.println("введите ряд"
                    + "от 1 до " + SIZE + " и столбец от 1 до " + SIZE
                    + ".");
            row = Integer.parseInt(in.next());
            col = Integer.parseInt(in.next());
            if ((row < 1) || (row > SIZE) || (col < 1) || (col > SIZE)) {
                System.out.println("Извините , ряд " + row + " или столбец " + col
                        + " должні быть от 1 " + SIZE + ".");
            } else {
                row--;                   // first Java row is numbered zero
                col--;                   // first Java column is numbered zero
                if (board[row][col] != EMPTY_1) {
                    System.out.println("Извините ячейка занята");
                    outputField();         // show user the game board (again)
                } else {
                    board[row][col] = USER;
                    asking = false;         // we are finished the user's input
                }
            }
        }}
        public static void playGame(){
            boolean end;
        clearBoard();
        end=false;
         while (!end)
         {
          outputField();
          userMoves();
          outputField();
          end=endgame();
          if(!end){
              computerMove();
              end=endgame();
              if (end)
                  outputField();
          }
         }

    }
    }