package algorithm10.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//马踏棋
public class HorseChessboard {
    //棋盘列数
    private static int X;
    //棋盘行数
    private static int Y;
    //记录棋盘各个位置是否被访问过
    private static boolean visited[];
    //标记是否棋盘所有位置都被访问过
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        visited = new boolean[X * Y];
        int[][] chessboard = new int[X][Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + " 毫秒");

        //输出棋盘情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //标记该位置已经访问
        visited[row * X + column] = true;
        //当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //排序
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //step和应该走的步数比较，没有完成任务，整个棋盘置0

        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }


    }

    //根据当前位置计算马能走的位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<Point>();
        Point p1 = new Point();
        //0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //5这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //return 0;
                //o1的下一步所有位置个数
                int count1=next(o1).size();
                //o2的下一步所有位置个数
                int count2=next(o2).size();
                if (count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
