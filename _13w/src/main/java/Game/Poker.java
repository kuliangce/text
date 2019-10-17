package Game;

import java.util.Scanner;

 public class Poker {
     public static int id = 0;
     public static String totp = new String();
    public static boolean[][] card = new boolean[4][14];
    public static int row[] = new int[4];
    public static int col[] = new int[14];
    public static char suit[] = {'*', '#', '&', '$'};
    static void begin(char[] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] != ' ') {
                int t = 0, num = 0;
                for (int j = 0; j < 4; j++) {
                    if (s[i] == suit[j]) {
                        t = j;
                        break;
                    }
                }
                num = change(s[i+1]);
                if (num == 9)
                    i++;
                i++;
                card[t][num] = true;
                row[t]++;
                col[num]++;
            }
        }
    }
    static void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= 13; j++) {
                System.out.print((card[i][j] == true));
                if (j == 13)
                    System.out.println();
                else
                    System.out.print(' ');
            }
        }
        for (int i = 0; i < 4; i++)
            System.out.println(row[i]);
        for (int i = 0; i < 14; i++)
            System.out.println(col[i]);
    }
    static int change(char c) {
        if (c >= '2' && c <= '9')
            return (int)(c - '1');
        else if (c == '1')
            return 9;
        else if (c == 'J')
            return 10;
        else if (c == 'Q')
            return 11;
        else if (c == 'K')
            return 12;
        else if (c == 'A')
            return 13;
        else
            return 0;
    }
    static void init(String card_json, int num){
        int len = card_json.length();
        id = num;
        char[] s = new char[len + 2];
        for (int i = 0; i < len; i++)s[i] = card_json.charAt(i);
        s[len + 1] = s[len] = ' ';
        for (int i = 0; i < 4; i++)row[i] = 0;
        for (int i = 0; i < 14; i++)col[i] = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 14; j++){
                card[i][j] = false;
            }
        }
        begin(s);
    }
}


