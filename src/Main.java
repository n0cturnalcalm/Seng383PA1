import java.util.Scanner;

public class Main {
    static char[][] board;
    static int row, col;
    static int knightX, knightY;
    static int goldX, goldY;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        importBoard(sc.next());
        checkBoardImported();
    }

    public static void importBoard(String input){
        // Satır ve sütun sayısını düzgün al
        row = Character.getNumericValue(input.charAt(0));
        col = Character.getNumericValue(input.charAt(1));

        board = new char[row][col];

        int a = 2; // İlk iki karakter row ve col olduğu için index 2’den başlıyoruz
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char cell = input.charAt(a++); // a'yı doğrudan artır
                board[i][j] = cell;

                if (cell == 'K') {
                    knightX = i;
                    knightY = j;
                } else if (cell == 'G') {
                    goldX = i;
                    goldY = j;
                }
            }
        }
    }

    public static void checkBoardImported(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Knight: (" + knightX + "," + knightY + ")");
        System.out.println("Gold: (" + goldX + "," + goldY + ")");
    }
}
