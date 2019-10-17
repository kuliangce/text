package test;

import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioFileFormat;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.io.*;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.border.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import static Until.HttpUntil.*;
import Until.*;
import Game.*;
import Rank.*;

import Auth.Account;
import Until.User;
public class Thirteen extends JFrame{
    String rpass = "",rnam = "",snam,spass,lpass,lnam;
    JFrame s = new JFrame("十三氵");
    JFrame ls = new JFrame("主菜单");
    int id,page;
    Account account = new Account();
    Round round = new Round();

    public void sta() throws IOException {

        JFrame l = new JFrame("开始");
        Container c = l.getContentPane();
        c.setLayout(null);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys2.png")).getImage());
        bg.setBounds(0,0,800,600);
        c.add(bg,-1);

        l.setBounds(400,100,800,600);
        l.setVisible(true);
        l.setResizable(false);

        JButton sen = new JButton("重开"),ex = new JButton("返回");
        JTextField got = new JTextField();
        JTextField sha = new JTextField(),zho = new JTextField(),xia = new JTextField(),jtid = new JTextField();
        JLabel g = new JLabel("牌面"),s = new JLabel("上墩"),x = new JLabel("下墩"),z = new JLabel("中墩");
        JLabel jid = new JLabel("牌局ID");
        sen.setFont(new Font("华文行楷",Font.BOLD,21));
        ex.setFont(new Font("华文行楷",Font.BOLD,21));
        g.setFont(new Font("华文行楷",Font.BOLD,21));
        s.setFont(new Font("华文行楷",Font.BOLD,21));
        x.setFont(new Font("华文行楷",Font.BOLD,21));
        z.setFont(new Font("华文行楷",Font.BOLD,21));
        jid.setFont(new Font("华文行楷",Font.BOLD,21));
        got.setFont(new Font("微软雅黑",Font.PLAIN,15));
        sha.setFont(new Font("微软雅黑",Font.PLAIN,15));
        zho.setFont(new Font("微软雅黑",Font.PLAIN,15));
        xia.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtid.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jtid.setBounds(200,340,100,30);
        jid.setBounds(125,340,100,30);
        got.setBounds(200,100,330,30);
        g.setBounds(150,100,100,30);
        s.setBounds(150,160,100,30);
        x.setBounds(150,280,100,30);
        z.setBounds(150,220,100,30);
        sha.setBounds(200,160,200,30);
        zho.setBounds(200,220,200,30);
        xia.setBounds(200,280,200,30);
        sen.setBounds(460,350,80,40);
        ex.setBounds(550, 350, 80, 40);
        c.add(got,0);
        c.add(jtid,0);
        c.add(jid,0);
        c.add(g,0);
        c.add(s,0);
        c.add(x,0);
        c.add(z,0);
        c.add(sha,0);
        c.add(zho,0);
        c.add(xia,0);
        c.add(sen,0);
        c.add(ex,0);
        round.Open(round.user,round.poker);
        got.setText(round.poker.totp);
        hand_card h = new hand_card();
        EX e = new EX();
        h.init(round.poker);
        NJ nj = new NJ();
        nj.Nomal_Judge(round.poker,h,3);
        nj.Nomal_Judge(round.poker,h,2);
        nj.Nomal_Judge(round.poker,h,1);
        e = e.init(h);
        Gson json = new Gson();
        JsonElement jj = json.toJsonTree(e);
        sha.setText(h.getss(0));
        zho.setText(h.getss(1));
        xia.setText(h.getss(2));
        String st = String.valueOf(round.poker.id);
        jtid.setText(st);
        round.submit(round.user, jj.toString());
        sen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    round.Open(round.user,round.poker);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
                got.setText(round.poker.totp);
                hand_card h = new hand_card();
                EX e = new EX();
                h.init(round.poker);
                NJ nj = new NJ();
                nj.Nomal_Judge(round.poker,h,3);
                nj.Nomal_Judge(round.poker,h,2);
                nj.Nomal_Judge(round.poker,h,1);
                e = e.init(h);
                Gson json = new Gson();
                JsonElement jj = json.toJsonTree(e);
                sha.setText(h.getss(0));
                zho.setText(h.getss(1));
                xia.setText(h.getss(2));
                String st = String.valueOf(round.poker.id);
                jtid.setText(st);
                try {
                    round.submit(round.user, jj.toString());
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ls.setVisible(true);
                l.dispose();
            }
        });
    }
    public void ra() throws IOException {
        String[] s = Rank_and_History.Get_total_Rank();
        JFrame l = new JFrame("排行榜");
        Container c = l.getContentPane();
        l.setBounds(410, 110, 830, 580);
        l.setVisible(true);
        l.setResizable(false);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys8.png")).getImage());
        bg.setBounds(0,0,830,580);
        c.add(bg,-1);

        JTextArea ar = new JTextArea();
        ar.setFont(new Font("微软雅黑",Font.PLAIN,21));
        int i = 0;
//        System.out.println(s[i]);
        while (s[i]!= null) {
            //System.out.println(i);
            ar.append(s[i]);
            ar.append("\n\n");
            //System.out.println(s[i]);
            i ++;
        }
        JScrollPane js = new JScrollPane(ar);
        ar.setOpaque(false);
        js.setOpaque(false);
        js.getViewport().setOpaque(false);
        c.add(js,0);
    }
    public void hlj(){
        JFrame l = new JFrame("查询历史记录的页数");
        Container c = l.getContentPane();

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys3.png")).getImage());
        bg.setBounds(0,0,400,300);
        c.add(bg,-1);

        l.setLayout(null);
        l.setBounds(460,160,400,300);
        l.setVisible(true);
        l.setResizable(false);

        JButton jb = new JButton("查询");
        jb.setBounds(86,100,80,30);
        c.add(jb,0);
        JLabel lb = new JLabel("查找记录的页数");
        lb.setBounds(0,44,200,15);
        c.add(lb,0);
        JTextField jt = new JTextField();
        jt.setBounds(100,44,220,21);
        c.add(jt,0);
        lb.setFont(new Font("华文行楷",Font.PLAIN,17));
        jb.setFont(new Font("华文行楷",Font.PLAIN,17));

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String s = jt.getText();
                page = Integer.valueOf(s).intValue();
                try {
                    hl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //               l.dispose();
            }
        });
    }
    public void hl() throws IOException {
        String s = Rank_and_History.Get_Personal_History(round.user,page-1);
        JFrame l = new JFrame("历史战局列表");
        Container c = l.getContentPane();
        l.setBounds(410, 110, 830, 580);
        l.setVisible(true);
        l.setResizable(false);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys8.png")).getImage());
        bg.setBounds(0,0,830,580);
        c.add(bg,-1);

        JTextArea ar = new JTextArea();
        ar.setFont(new Font("微软雅黑",Font.PLAIN,21));
        if (s != null)
            ar.setText(s);
        else
            ar.setText("这页没有历史记录咯，请在前页找一下");

        JScrollPane js = new JScrollPane(ar);
        ar.setOpaque(false);
        js.setOpaque(false);
        js.getViewport().setOpaque(false);
        c.add(js,0);
    }
    public void hxj(){
        JFrame l = new JFrame("战局查询");
        Container c = l.getContentPane();

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys3.png")).getImage());
        bg.setBounds(0,0,400,300);
        c.add(bg,-1);

        l.setLayout(null);
        l.setBounds(460,160,400,300);
        l.setVisible(true);
        l.setResizable(false);

        JButton jb = new JButton("查询");
        jb.setBounds(86,100,80,30);
        c.add(jb,0);
        JLabel lb = new JLabel("查找战局ID");
        lb.setBounds(0,44,100,15);
        c.add(lb,0);
        JTextField jt = new JTextField();
        jt.setBounds(86,44,220,21);
        c.add(jt,0);
        lb.setFont(new Font("华文行楷",Font.PLAIN,17));
        jb.setFont(new Font("华文行楷",Font.PLAIN,17));

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String s = jt.getText();
                id = Integer.valueOf(s).intValue();
                try {
                    hx();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //               l.dispose();
            }
        });
    }
    public void hx() throws IOException {
        String s = Rank_and_History.Get_Detail_History(round.user,id);
        JFrame l = new JFrame("历史战局详情");
        Container c = l.getContentPane();
        l.setBounds(410, 110, 830, 580);
        l.setVisible(true);
        l.setResizable(false);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys8.png")).getImage());
        bg.setBounds(0,0,830,580);
        c.add(bg,-1);

        JTextArea ar = new JTextArea();
        ar.setFont(new Font("微软雅黑",Font.PLAIN,20));
        if (s != null)
            ar.setText(s);
        else
            ar.setText("未找到该历史战局");
        JScrollPane js = new JScrollPane(ar);
        ar.setOpaque(false);
        js.setOpaque(false);
        js.getViewport().setOpaque(false);
        c.add(js,0);
    }
    public void hi() {
        JFrame l = new JFrame("历史记录");
        Container c = l.getContentPane();
        c.setLayout(null);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys2.png")).getImage());
        bg.setBounds(0,0,800,600);
        c.add(bg,-1);

        l.setBounds(400,100,800,600);
        l.setVisible(true);
        l.setResizable(false);

        JButton ran = new JButton("排行榜"),hl = new JButton("历史战局列表"),hx = new JButton("历史战局详情"),ex = new JButton("返回");
        ran.setFont(new Font("华文行楷",Font.BOLD,21));
        hl.setFont(new Font("华文行楷",Font.BOLD,21));
        hx.setFont(new Font("华文行楷",Font.BOLD,21));
        ex.setFont(new Font("华文行楷",Font.BOLD,21));
        ran.setBounds(300, 100, 200, 50);
        hl.setBounds(300, 180, 200, 50);
        hx.setBounds(300, 260, 200, 50);
        ex.setBounds(300, 340, 200, 50);

        c.add(ran,0);
        c.add(hl,0);
        c.add(hx,0);
        c.add(ex,0);
        ran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    ra();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hlj();
            }
        });
        hx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                    hxj();
            }
        });
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ls.setVisible(true);
                l.dispose();
            }
        });
    }

    public void mai() {
        //JFrame l = new JFrame("主菜单");
        //l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = ls.getContentPane();
        c.setLayout(null);
        ls.setResizable(false);

        BackgroundPanel bg;
        bg = new BackgroundPanel((new ImageIcon("src/ys2.png")).getImage());
        bg.setBounds(0,0,800,600);
        c.add(bg,-1);

        ls.setBounds(400,100,800,600);
        ls.setVisible(true);
        JButton st = new JButton("开始"),his = new JButton("历史"),ex = new JButton("退出");
        st.setFont(new Font("华文行楷",Font.BOLD,21));
        his.setFont(new Font("华文行楷",Font.BOLD,21));
        ex.setFont(new Font("华文行楷",Font.BOLD,21));
        st.setBounds(340, 100, 100, 50);
        his.setBounds(340, 230, 100, 50);
        ex.setBounds(340, 360, 100, 50);
        c.add(st,0);
        c.add(his,0);
        c.add(ex,0);

        st.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    sta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ls.setVisible(false);
            }
        });
        his.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hi();
                ls.setVisible(false);
            }
        });
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ls.dispose();
                s.setVisible(true);
            }
        });
    }

    public void log() {
        JFrame l = new JFrame("登录");
        //l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(500, 150, 400, 300);
        Container c = l.getContentPane();

        BackgroundPanel bg,bgm;
        bg = new BackgroundPanel((new ImageIcon("src/ys3.png")).getImage());
        bg.setBounds(0, 0, 400, 300);
        l.add(bg,-1);

        c.setLayout(null);
        l.setVisible(true);
        l.setResizable(false);

        JTextField jt = new JTextField();
        JPasswordField jp = new JPasswordField();
        jt.setColumns(20);
        jp.setColumns(20);
        JLabel lb = new JLabel("账号"),lbe = new JLabel("密码");
        JButton ac = new JButton("确认");
        lb.setFont(new Font("华文行楷",Font.PLAIN,17));
        lbe.setFont(new Font("华文行楷",Font.PLAIN,17));
        ac.setFont(new Font("华文行楷",Font.PLAIN,17));
        lb.setBounds(46, 13, 100, 15);
        lbe.setBounds(46,44,100,15);
        jt.setBounds(86, 11, 220, 21);
        jp.setBounds(86, 42, 220, 21);
        ac.setBounds(86, 100, 80, 30);
        c.add(jt,0);
        c.add(jp,0);
        c.add(lb,0);
        c.add(lbe,0);
        c.add(ac,0);
        ac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //System.out.println("llllllllllllllllll");
                char ch[] = jp.getPassword();
                lpass = new String(ch);
//				System.out.println(lpass);
                lnam = jt.getText();
//				System.out.println(lnam);
                try {
                    User user = new User();
                    user.debug();
                    user.init(lnam, lpass);
                    user.debug();
                    user = account.Login(user);
                    if (user == null){
                        JDialog jd = new JDialog();
                        jd.setBounds(550, 200, 600, 250);
                        Container dc = jd.getContentPane();
                        BackgroundPanel abg;
                        abg = new BackgroundPanel((new ImageIcon("src/ys4.png")).getImage());
                        abg.setBounds(0, 0, 600, 250);
                        jd.add(abg,-1);
                        JLabel dl = new JLabel();
                        dl.setText("登录失败,请检查密码或用户名是否正确");
                        dl.setFont(new Font("华文行楷",Font.PLAIN,28));
                        dc.add(dl,0);
                        jd.setVisible(true);
                        //System.out.println(lnam+"\n"+lpass+"\n"+rnam+'\n'+rpass);
                    }
                    else{
                        round.user = user;
                        s.setVisible(false);
                        mai();
                    }
                } catch (IOException e) {
             //       e.printStackTrace();
                }
//                if (lpass.equals(rpass) && lnam.equals(rnam)){
////					dl.setText("登录成功");
////					dc.add(dl);
//                    s.setVisible(false);
//                    mai();
//                }
//                else {
//                    JDialog jd = new JDialog();
//                    jd.setBounds(550, 200, 600, 250);
//                    Container dc = jd.getContentPane();
//                    BackgroundPanel abg;
//                    abg = new BackgroundPanel((new ImageIcon("src/ys4.png")).getImage());
//                    abg.setBounds(0, 0, 600, 250);
//                    jd.add(abg,-1);
//                    JLabel dl = new JLabel();
//                    dl.setText("登录失败,请检查密码或用户名是否正确");
//                    dl.setFont(new Font("华文行楷",Font.PLAIN,28));
//                    dc.add(dl,0);
//                    jd.setVisible(true);
//                    //System.out.println(lnam+"\n"+lpass+"\n"+rnam+'\n'+rpass);
//                }
                l.dispose();
                //隐藏
//                addWindowListener(new WindowAdapter() {
//                    public void windowClosing(WindowEvent e) {
//                        int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
//                                JOptionPane.YES_NO_OPTION);
//                        if (a == 0) {
//                            System.exit(0); //关闭
//                        }
//                    }
//                });
            }

        });
        //flag = false;
    }
    public void tie() {
        JFrame l = new JFrame("绑定");
        //l.setUndecorated(true);
        //l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(500, 150, 400, 300);
        Container c = l.getContentPane();
        BackgroundPanel bg,bgm;
        bg = new BackgroundPanel((new ImageIcon("src/ys3.png")).getImage());
        bg.setBounds(0, 0, 400, 300);
        l.add(bg,-1);
        c.setLayout(null);
        l.setVisible(true);
        l.setResizable(false);

        JTextField jt = new JTextField();
        JPasswordField jp = new JPasswordField();
        jt.setColumns(20);
        jp.setColumns(20);
        JLabel lb = new JLabel("教务处账号"),lbe = new JLabel("教务处密码");
        JButton ac = new JButton("绑定");
        lb.setFont(new Font("华文行楷",Font.PLAIN,17));
        lbe.setFont(new Font("华文行楷",Font.PLAIN,17));
        ac.setFont(new Font("华文行楷",Font.PLAIN,17));
        lb.setBounds(0, 13, 100, 15);
        lbe.setBounds(0,44,100,15);
        jt.setBounds(86, 11, 220, 21);
        jp.setBounds(86, 42, 220, 21);
        ac.setBounds(86, 100, 80, 30);
        c.add(jt,0);
        c.add(jp,0);
        c.add(lb,0);
        c.add(lbe,0);
        c.add(ac,0);
        ac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                char ch[] = jp.getPassword();
                rpass = new String(ch);
//				System.out.println(rpass);
                rnam = jt.getText();
//				System.out.println(rnam);
                JDialog jd = new JDialog();
                BackgroundPanel abg;
                abg = new BackgroundPanel((new ImageIcon("src/ys4.png")).getImage());
                abg.setBounds(0, 0, 500, 200);
                jd.add(abg,-1);
                jd.setBounds(550, 200, 500, 200);
                Container dc = jd.getContentPane();
                jd.setVisible(true);
                JLabel dl = new JLabel();
                dl.setText("绑定成功");
                dl.setFont(new Font("华文行楷",Font.PLAIN,28));
                dc.add(dl,0);
                l.dispose();
            }

        });
    }
    public void res() {
        JFrame l = new JFrame("注册");
        //l.setUndecorated(true);
        //l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(500, 150, 400, 300);
        Container c = l.getContentPane();
        BackgroundPanel bg,bgm;
        bg = new BackgroundPanel((new ImageIcon("src/ys3.png")).getImage());
        bg.setBounds(0, 0, 400, 300);
        l.add(bg,-1);
        c.setLayout(null);
        l.setVisible(true);
        l.setResizable(false);

        JTextField jt = new JTextField();
        JPasswordField jp = new JPasswordField();
        jt.setColumns(20);
        jp.setColumns(20);
        JLabel lb = new JLabel("账号"),lbe = new JLabel("密码");
        JButton ac = new JButton("注册"),ti = new JButton("绑定");
        lb.setFont(new Font("华文行楷",Font.PLAIN,17));
        lbe.setFont(new Font("华文行楷",Font.PLAIN,17));
        ac.setFont(new Font("华文行楷",Font.PLAIN,17));
        ti.setFont(new Font("华文行楷",Font.PLAIN,17));
        lb.setBounds(46, 13, 100, 15);
        lbe.setBounds(46,44,100,15);
        jt.setBounds(86, 11, 220, 21);
        jp.setBounds(86, 42, 220, 21);
        ac.setBounds(86, 100, 80, 30);
        ti.setBounds(246, 100, 80, 30);
        c.add(jt,0);
        c.add(jp,0);
        c.add(lb,0);
        c.add(lbe,0);
        c.add(ac,0);
        c.add(ti,0);
        ac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                char ch[] = jp.getPassword();
                rpass = new String(ch);
//				System.out.println(rpass);
                rnam = jt.getText();

                try {
                    User user = new User();
                    user.init(rnam, rpass);
                    if (account.register(user)){
                        JDialog jd = new JDialog();
                        jd.setBounds(550, 200, 500, 200);
                        Container dc = jd.getContentPane();
                        jd.setVisible(true);
                        JLabel dl = new JLabel();
                        BackgroundPanel abg;
                        abg = new BackgroundPanel((new ImageIcon("src/ys4.png")).getImage());
                        abg.setBounds(0, 0, 500, 200);
                        jd.add(abg,-1);
                        dl.setText("注册成功");
                        dl.setFont(new Font("华文行楷",Font.PLAIN,28));
                        dc.add(dl,0);
                        l.dispose();
                    }
                    else{
                        JDialog jd = new JDialog();
                        jd.setBounds(550, 200, 500, 200);
                        Container dc = jd.getContentPane();
                        jd.setVisible(true);
                        JLabel dl = new JLabel();
                        BackgroundPanel abg;
                        abg = new BackgroundPanel((new ImageIcon("src/ys4.png")).getImage());
                        abg.setBounds(0, 0, 500, 200);
                        jd.add(abg,-1);
                        dl.setText("注册失败");
                        dl.setFont(new Font("华文行楷",Font.PLAIN,28));
                        dc.add(dl,0);
                        l.dispose();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
				//System.out.println(rnam);

            }

        });
        ti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tie();
            }

        });
    }
    boolean flag = false;
    public Thirteen() {
        //s.setUndecorated(true);
//		s.setBounds(0,0,400,300);
//        AudioClip aau;
//        aau = Applet.newAudioClip(url);
//        aau.loop();
        //play();

        Container sc = s.getContentPane();
        sc.setLayout(null);
        s.setResizable(false);

        BackgroundPanel bg,bgm;
        bg = new BackgroundPanel((new ImageIcon("src/bg3.png")).getImage());
        bg.setBounds(0, 0, 800, 600);
        sc.add(bg,-1);

        s.setBounds(400,100,800,600);
////		JLabel jl = new JLabel(ic);
////		jl.setBounds(0,0,ic.getIconWidth(),ic.getIconHeight());
////		sc.add(jl,new Integer(Integer.MIN_VALUE));
////		JPanel jpa = new JPanel();
////		jpa = (JPanel)s.getContentPane();
////		jpa.setOpaque(false);
//		JPanel jpa = new JPanel(){
//			public void paint(Graphics g) {
//				ImageIcon ic = new ImageIcon("bg1.jpg");
//				Image img = ic.getImage();
//				g.drawImage(img, 0, 0, null);
//				s.setSize(ic.getIconWidth(), ic.getIconHeight());
//			}
//		};
//		//jl.setIcon(ic);
//		//jpa.add(jl);
//		s.add(jpa);
////		s.pack();
//		private ImageJPanel ip = new ImageJPanel();

        JButton login = new JButton("登录"),resit = new JButton("注册"),ti = new JButton("绑定");
        login.setBounds(170,200,150,50);
        resit.setBounds(170,270,150,50);
        ti.setBounds(170, 340, 150, 50);
        login.setFont(new Font("华文行楷",Font.BOLD,21));
        resit.setFont(new Font("华文行楷",Font.BOLD,21));
        ti.setFont(new Font("华文行楷",Font.BOLD,21));
        //login.setContentAreaFilled(false);
        //login.setBorder(bored);
        sc.add(resit,0);
        sc.add(login,0);
        sc.add(ti,0);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (flag == false) {
                    //flag = true;
                    log();
                }

            }
        });
        resit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (flag == false) {
                    //flag = true;
                    res();
                }

            }
        });
        ti.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (flag == false) {
                    //flag = true;
                    tie();
                }

            }
        });
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class BackgroundPanel extends JPanel
    {
        Image im;
        public BackgroundPanel(Image im)
        {
            this.im=im;
            this.setOpaque(true);
        }
        //Draw the back ground.
        public void paintComponent(Graphics g)
        {
            super.paintComponents(g);
            g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);

        }
    }
/*    public static void play(){
        try{
            URL cb;
            File f = new File("C:\\Users\\14220\\Desktop\\新建文件夹\\_13w\\src\\china.wav"); // 引号里面的是音乐文件所在的路径
            cb = f.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(cb);

            aau.play();
            aau.loop();//循环播放
            System.out.println("可以播放");
            // 循环播放 aau.play()
            //单曲 aau.stop()停止播放

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        new Thirteen();

    }
}
