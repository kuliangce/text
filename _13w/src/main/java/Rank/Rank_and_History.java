package Rank;

import com.google.gson.*;

import java.io.IOException;
import static Until.HttpUntil.*;
import Until.*;
import Auth.*;

import javax.print.DocFlavor;

public class Rank_and_History {
    public static String[] Get_total_Rank() throws IOException {
        Gson json = new Gson();
        String[] ans = new String[100];
        String respond = getRequest("https://api.shisanshui.rtxux.xyz/rank", null);
        JsonArray s = new JsonParser().parse(respond).getAsJsonArray();
        for (int i = 0; i < 50 && i < s.size(); i++){
            ans[i] = "";
            JsonElement str = s.get(i);
            JsonObject text = str.getAsJsonObject();
            ans[i] += "用户名: ";
            str = text.get("name");
            ans[i] += str.getAsString();
            ans[i] += " ID: ";
            str = text.get("player_id");
            ans[i] += str.toString();
            ans[i] += " 得分: ";
            str = text.get("score");
            ans[i] += str.toString();
//            System.out.println(ans[i]);
        }
        return ans;
    }
    public static String Get_Detail_History(User user, int id) throws IOException {
        Gson json = new Gson();
        String ans = "";
        JsonElement s1 = json.toJsonTree(user);
        String token = s1.getAsJsonObject().get("token").getAsString();
        String Url = "https://api.shisanshui.rtxux.xyz/history/" + Integer.toString(id);
        String respond = getRequest(Url, token);
//        System.out.println(respond);
        s1 = new JsonParser().parse(respond);
        if (s1.getAsJsonObject().get("status").getAsInt() != 0){
            return null;
        }
        JsonObject s2 = s1.getAsJsonObject().get("data").getAsJsonObject();
        ans += "战局ID: " + s2.get("id").toString() + "\n";
        ans += "战局结算时间: " + s2.get("timestamp").toString() + "\n";
        JsonArray s = new JsonParser().parse(s2.get("detail").toString()).getAsJsonArray();
        ans += "\n\n";
        for (int i = 0; i < 4; i++){
            s2 = s.get(i).getAsJsonObject();
            ans += "用户ID: " + s2.get("player_id").toString() + "\n";
            ans += "用户名: " + s2.get("name").getAsString() + "\n";
            ans += "得分: " + s2.get("score").toString() + "\n";
            JsonArray card = new JsonParser().parse(s2.get("card").toString()).getAsJsonArray();
            ans += "前墩: " + card.get(0).getAsString() + "\n";
            ans += "中墩: " + card.get(1).getAsString() + "\n";
            ans += "后墩: " + card.get(2).getAsString() + "\n";
            ans += "\n\n";
        }
        System.out.println(ans);
        return ans;
    }
    public static String Get_Personal_History(User user, int page) throws IOException {
        String ans = new String();
        Gson json = new Gson();
        JsonElement s1 = json.toJsonTree(user);
        String token = s1.getAsJsonObject().get("token").getAsString();
        String Url = "https://api.shisanshui.rtxux.xyz/history?" + "player_id=" + String.valueOf(user.getID());
        Url += "&limit=25&page=" + String.valueOf(page);
        String respond = getRequest(Url, token);
        s1 = new JsonParser().parse(respond);
        JsonArray s = new JsonParser().parse(s1.getAsJsonObject().get("data").toString()).getAsJsonArray();
        if (s.size() == 0){
            return null;
        }
        for (int i = 0; i < 25 && i < s.size(); i++){
            System.out.println(i);
            JsonElement str = s.get(i);
            JsonObject text = str.getAsJsonObject();
            ans += "战局ID: " + text.get("id").toString() + "\n";
            ans += "得分: " + text.get("score").toString() + "\n";
            JsonArray card = new JsonParser().parse(text.get("card").toString()).getAsJsonArray();
            if (card.size() == 1){
                ans += "特殊牌型" + card.get(0).getAsString() + "\n\n\n";
                continue;
            }
            ans += "前墩: " + card.get(0).getAsString() + "\n";
            ans += "中墩: " + card.get(1).getAsString() + "\n";
            ans += "后墩: " + card.get(2).getAsString() + "\n\n\n";
        }
        return ans;
    }
}
