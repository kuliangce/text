package Until;

public class User {
    private int user_id;
    private String token;
    private String username;
    private String password;
    public void init(String a, String b){
        this.username = a;
        this.password = b;
        this.token = null;
    }
    public void ID(int ID){
        user_id = ID;
    }
    public String getToken(){
        return token;
    }
    public void debug(){
        System.out.println(username);
        System.out.println(password);
    }
    public int getID(){
        return user_id;
    }
}
