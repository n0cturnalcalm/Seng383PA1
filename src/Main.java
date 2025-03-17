import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static char[][] board;
    static int row, col;
    static int knightX, knightY;
    static int goldX, goldY;

    static int[] possibleX = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] possibleY = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        importBoard(sc.next());
        checkBoardImported();
        findPathViaBFS();
    }

    public static void importBoard(String input){
        // Satır ve sütun sayısını düzgün al
        row = Character.getNumericValue(input.charAt(0));
        col = Character.getNumericValue(input.charAt(1));

        board = new char[row][col];

        int x = 2; // İlk iki karakter row ve col olduğu için index 2’den başlıyoruz
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char cell = input.charAt(x++); // a'yı doğrudan artır
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
        System.out.println("Knight: (" + (knightX+1) + "," + (knightY+1) + ")");
        System.out.println("Gold: (" + (goldX+1) + "," + (goldY+1) + ")");
    }

    public static void findPathViaBFS() {
        boolean[][] visited = new boolean[row][col];
        Queue<Node> queue = new LinkedList<>();

        // Başlangıç noktasını kuyruğa ekle
        queue.add(new Node(knightX, knightY, 0, "c" + (knightX + 1) + "," + (knightY + 1)));
        visited[knightX][knightY] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Eğer altına ulaşıldıysa, yolu yazdır ve çık
            if (current.x == goldX && current.y == goldY) {
                System.out.println(current.path);
                return;
            }

            // 8 farklı hareket yönüne gitmeyi dene
            for (int i = 0; i < 8; i++) {
                int newX = current.x + possibleX[i];
                int newY = current.y + possibleY[i];

                if (isPossibleMove(newX, newY, visited)) {
                    visited[newX][newY] = true;
                    queue.add(new Node(newX, newY, current.steps + 1,
                            current.path + " -> c" + (newX + 1) + "," + (newY + 1)));
                }
            }
        }

        // Eğer altına ulaşan bir yol bulunamazsa
        System.out.println("No path to the gold");
    }




    public static boolean isPossibleMove(int x, int y, boolean[][] visited){
        if (0 <= x && x < row && 0 <= y && y < col && !visited[x][y] && board[x][y] != 'T'){
            return true;
        }else {
            return false;
        }
    }

    public static class Node{
        int x, y, steps;
        Node prev, next;
        String path;

        public Node(int x, int y, int steps, String path){
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.path = path;
        }
    }
}