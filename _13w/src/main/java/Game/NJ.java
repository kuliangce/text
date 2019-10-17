package Game;

public class NJ{
    static int cnt = 0;
    static int[] t = new int[10];
    public static char suit[] = {'*', '#', '&', '$'};
    static boolean flush(Poker poker){ //num == 5
        for (int i = 0; i < 4; i++){
            if (poker.row[i] >= 5){
                for (int j = 9; j >= 1; j--){
                    if (poker.card[i][j]){
                        boolean flag = true;
                        for (int k = 0; k < 5; k++){
                            if (poker.card[i][j+k])t[k] = i * 14 + j + k;
                            else flag = false;
                        }
                        if (flag)
                            return true;
                    }
                }
            }
        }
        return false;
    }
    static boolean boom(Poker poker){
        for (int i = 13; i >= 1; i--){
            if (poker.col[i] == 4) {
                for (int j = 1; j <= 4; j++) {
                    t[j] = (j - 1) * 14 + i;
                }
                for (int j = 13; j >= 1; j--){
                    for (int k = 0; k < 4; k++){
                        if (j == i)continue;
                        else if (poker.card[k][j]){
                            t[0] = k * 14 + j;
                            return true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    static boolean cucurbit(Poker poker){ //葫芦
        int L = 0, R = 0;
        for (int i = 13; i >= 1; i--){
            if (poker.col[i] == 3){
                L = i;
                break;
            }
        }
        for (int i = 13; i >= 1; i--){
            if (poker.col[i] == 2){
                R = i;
            }
        }
        if (R == 0){
            for (int i = 13; i >= 1; i--){
                if (poker.col[i] == 3 && i != L){
                    R = i;
                    break;
                }
            }
        }
        if (R == 0 || L == 0)
            return false;
        else{
            int co = 0;
            for (int i = 0; i < 4; i++){
                if (poker.card[i][L])
                    t[co++] = i * 14 + L;
            }
            for (int i = 0; i < 4; i++){
                if (poker.card[i][R] && co < 5)
                    t[co++] = i * 14 + R;
            }
            return true;
        }
    }
    static boolean same_suit(Poker poker){
        int L = 0, R = 0, x = 0;
        for (int i = 0; i < 4; i++){
            if (poker.row[i] >= 5 && L == 0)
                L = i;
            else if (poker.row[i] >= 5 && L != 0)
                R = i;
        }
        for (int i = 13; i >= 1; i--){
            if (poker.card[L][i])
                x = L;
            else if (poker.card[R][i])
                x = R;
            if (x != 0)
                break;
        }
        for (int i = x; i < 4; i++){
            if (poker.row[i] >= 5){
                int co = 0;
                for (int j = 13; j >= 1; j--){
                    if (poker.card[i][j]){
                        t[co++] = i * 14 + j;
                    }
                    if (co >= 5)
                        return true;
                }
            }
        }
        return false;
    }
    static boolean straight(Poker poker){
        for (int i = 9; i >= 1; i--){
            boolean flag = true;
            for (int j = 0; j < 5;j++){
                if (poker.col[i+j] == 0)flag = false;
            }
            if (flag){
                int co = 0;
                for (int j = 0; j < 5; j++){
                    for (int k = 0; k < 4; k++){
                        if (poker.card[k][i+j]){
                            t[co++] = k * 14 + i + j;
                            break;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    static boolean three(Poker poker){
        for (int i = 13; i >= 1; i--){
            if (poker.col[i] == 3){
                int co = 0;
                for (int j = 0; j < 4; j++){
                    for (int k = 13; k >= 1; k--){
                        if (k == i)continue;
                        if (poker.card[j][k] && co < 2){
                            t[co++] = j * 14 + k;
                        }
                    }
                }
                for (int j = 0; j < 4; j++){
                    if (poker.card[j][i])
                        t[co++] = j * 14 + i;
                }
                return true;
            }
        }
        return false;
    }
    static boolean two_pair(Poker poker){
        for (int i = 12; i >= 1; i--){
            if (poker.col[i] == poker.col[i + 1] && poker.col[i] == 2){
                int co = 0;
                for (int j = 0; j < 4 && co < 1; j++){
                    for (int k = 13; k >= 1 && co < 1; k--){
                        if (k == i || k == i + 1)continue;
                        if (poker.card[j][k]){
                            t[co++] = j * 14 + k;
                        }
                    }
                }
                for (int j = 0; j < 4; j++)
                    if (poker.card[j][i])t[co++] = j * 14 + i;
                for (int j = 0; j < 4; j++)
                    if (poker.card[j][i+1])t[co++] = j * 14 + i + 1;
                return true;
            }
        }
        int L = 0, R = 0;
        for (int i = 13; i >= 1; i--){
            if (L == 0 && poker.col[i] == 2)
                L = i;
            else if (poker.col[i] == 2 && R == 0 && L != 0)
                R = i;
        }
        if (L != 0 && R != 0){
            int co = 0;
            for (int j = 0; j < 4 && co < 1; j++){
                for (int k = 13; k >= 1 && co < 1; k--){
                    if (k == L || k == R)continue;
                    if (poker.card[j][k]){
                        t[co++] = j * 14 + k;
                    }
                }
            }
            for (int j = 0; j < 4; j++)
                if (poker.card[j][L])t[co++] = j * 14 + L;
            for (int j = 0; j < 4; j++)
                if (poker.card[j][R])t[co++] = j * 14 + R;
            return true;
        }
        return false;
    }
    static boolean pair(Poker poker){

        for (int i = 13; i >= 1; i--){
            if (poker.col[i] == 2){
                int co = 0;
                for (int j = 0; j < 4; j++){
                    for (int k = 13; k >= 1; k--){
                        if (k == i)continue;
                        if (poker.card[j][k] && co < 3){
                            t[co++] = j * 14 + k;
                        }
                    }
                }
                for (int j = 0; j < 4; j++){
                    if (poker.card[j][i]){
                        t[co++] = j * 14 + i;
                    }
                }
                return true;
            }
        }
        return false;
    }
    static boolean zapai(Poker poker){
        int co = 0;
        for (int j = 13; j >= 1; j--){
            for (int i = 0; i < 4; i++){
                if (poker.card[i][j] && co < cnt){
                    t[co++] = i * 14 + j;
                }
            }
        }
        return true;
    }
    public static void Nomal_Judge(Poker poker, hand_card ex, int x){
        if (x == 1)cnt = 3;
        else cnt = 5;
        if (x != 1){
            if (flush(poker)){
                analyse(ex, x);
                System.out.println(1);
                clr(poker);
                return;
            }
            if (boom(poker)){
                analyse(ex, x);
                System.out.println(2);
                clr(poker);
                return;
            }
            if (cucurbit(poker)){
                analyse(ex, x);
                System.out.println(3);
                clr(poker);
                return;
            }
            if (same_suit(poker)){
                analyse(ex, x);
                System.out.println(4);
                clr(poker);
                return;
            }
            if (straight(poker)){
                analyse(ex, x);
                System.out.println(5);
                clr(poker);
                return;
            }
        }
        if (three(poker)){
            analyse(ex, x);
            System.out.println(6);
            clr(poker);
            return;
        }
        if (x != 1){
            if (two_pair(poker)){
                analyse(ex, x);
                System.out.println(7);
                clr(poker);
                return;
            }
        }
        if (pair(poker)){
            analyse(ex, x);
            System.out.println(8);
            clr(poker);
            return;
        }
        if (zapai(poker)){
            analyse(ex, x);
            System.out.println(9);
            clr(poker);
            return;
        }
    }
    static void analyse(hand_card ex, int x){
        int s, num;
        String c, test;
        for (int i = 0; i < cnt; i++){
            s = t[i] / 14;
            num = t[i] - s * 14;
            if (i != 0){
                ex.pushString(" ", x);
            }
            c = String.valueOf(suit[s]);
            test = c;
            ex.pushString(c, x);
            c = change(num);
            test = test + c;
            ex.pushString(c, x);
//            System.out.println(test);
        }
//        for (int i = 0; i < cnt; i++){
//            System.out.println(t[i]);
//        }
    }
    static void clr(Poker poker){
        int s, num;
        for (int i = 0; i < cnt; i++){
            s = t[i] / 14;
            num = t[i] - s * 14;
            poker.card[s][num] = false;
            poker.col[num]--;
            poker.row[s]--;
        }
    }
    static String change(int x) {
        String s;
        if (x >= 1 && x <= 8){
            char c = (char)('1' + x);
            s = String.valueOf(c);
        }
        else if (x == 9)s = "10";
        else if (x == 10)s = "J";
        else if (x == 11)s = "Q";
        else if (x == 12)s = "K";
        else if (x == 13)s = "A";
        else s = "!";
        return s;
    }
}