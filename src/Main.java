import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] walls = new int[n][m];

        int x, y, dir;

        x = sc.nextInt();
        y = sc.nextInt();
        dir = sc.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                walls[i][j] = sc.nextInt();
            }
        } //문제 입력 완료

        while(true) {
            //조건 1
            if (isEmptyRoom(x, y, walls)) {
                cleanRoom(x, y, walls);
                cnt++;
            }

            //조건 2
            if (isCleanedRoom4(x, y, walls)) {
                if (dir == 0) {
                    if (!isWall(x+1, y, walls)) {
                        x += 1;
                    }
                    else {
                        System.out.println(cnt);
                        return;
                    }
                }//북쪽을 향하고, 남쪽이 비었다면 조건 1로 돌아가기
                else if (dir == 1) {
                    if (!isWall(x, y-1, walls)) {
                        y -= 1;
                    }
                    else {
                        System.out.println(cnt);
                        return;
                    }
                }//동쪽을 향하고, 서쪽이 비었다면 조건 1로 돌아가기
                else if (dir == 2) {
                    if (!isWall(x-1, y, walls)) {
                        x -= 1;
                    }
                    else {
                        System.out.println(cnt);
                        return;
                    }
                }//남쪽을 향하고, 북쪽이 비었다면 조건 1로 돌아가기
                else if (dir == 3) {
                    if (!isWall(x, y+1, walls)) {
                        y += 1;
                    }
                    else {
                        System.out.println(cnt);
                        return;
                    }
                }//서쪽을 향하고, 동쪽이 비었다면 조건 1로 돌아가기
            }

            //조건 3
            else if (isEmptyDir4(x, y, walls)){
                dir = (dir + 3) % 4;

                if (dir == 0 & isEmptyRoom(x-1, y, walls)) {
                    x -= 1;
                } else if (dir == 1 & isEmptyRoom(x, y+1, walls)) {
                    y += 1;
                } else if (dir == 2 & isEmptyRoom(x+1, y, walls)) {
                    x += 1;
                } else if (dir == 3 & isEmptyRoom(x, y-1, walls)) {
                    y -= 1;
                }
            }
        }
    }

    /**
     * 2차원 int 배열 출력
     * @param array 2차원 int 배열
     */
    private static void printArray(int[][] array) {
        for (int[] wall : array) {
            for (int i : wall) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 2차원 배열의 array[x][y] 값이 0임을 확인
     *
     * @param x 현재 x값
     * @param y 현재 y 값
     * @param array 2차원 int 배열
     * @return array[x][y] == 0
     */
    private static boolean isEmptyRoom(int x, int y, int[][] array) {
        return array[x][y] == 0;
    }

    private static boolean isWall(int x, int y, int[][] array) {
        return array[x][y] == 1;
    }

    private static void cleanRoom(int x, int y, int[][] array) {
        array[x][y] = 2;
    }

    private static boolean isCleanedRoom4(int x, int y, int[][] array) {
        return array[x-1][y] != 0 && array[x+1][y] != 0 && array[x][y-1] != 0 && array[x][y+1] != 0;
    }

    private static boolean isEmptyDir4(int x, int y, int[][] array) {
        return array[x-1][y] == 0 || array[x+1][y]== 0 || array[x][y-1]==0 || array[x][y+1]==0;
    }
}