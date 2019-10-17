package Auth;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import static Until.HttpUntil.*;
import Until.*;

public class Account {
    private static String Register = "https://api.shisanshui.rtxux.xyz/auth/register";
    private static String Login = "https://api.shisanshui.rtxux.xyz/auth/login";
    private static String Logout = "https://api.shisanshui.rtxux.xyz/auth/logout";
    public static boolean register(User user) throws IOException {
        Gson json = new Gson();
        System.out.println(json.toJson(user));
        String respond = postRequest(Register, json.toJson(user));
        System.out.println(respond);
        JsonElement s1 = new JsonParser().parse(respond);
        JsonObject s2 = s1.getAsJsonObject();
        if (s2.get("status").getAsInt() == 0)
            return true;
        else
            return false;
    }
    public static User Login(User user) throws IOException {
        Gson json = new Gson();
        String respond = postRequest(Login, json.toJson(user));
        System.out.println(respond);
        JsonElement s1 = new JsonParser().parse(respond);
        JsonObject s2 = s1.getAsJsonObject();
        if (s2.get("status").getAsInt() == 0) {
            System.out.println(respond);
            user = json.fromJson(s1.getAsJsonObject().get("data"), User.class);
            return user;
        }
        else{
            return null;
        }
    }
    public static void validate(String str) throws IOException {
        Gson json = new Gson();
        String respond = getRequest("https://api.shisanshui.rtxux.xyz/auth/validate", str);
        System.out.println(respond);
    }

    public static void main(String args[]) throws IOException {
        Gson json = new Gson();
        User user = new User();
        user.init("zxcad","zxcda");
  //      System.out.println(register(user));
        user.init("zxcad","zxcda");
        user = Login(user);
        System.out.println(json.toJson(user));
        JsonElement s1 = json.toJsonTree(user);
       // ggg(s1.getAsJsonObject().get("token").getAsString());
       // ttt(s1.getAsJsonObject().get("token").getAsString(), user);

    }
}
