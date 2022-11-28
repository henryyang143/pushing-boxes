import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Window extends JFrame {
    private Gameload Gameload;
    private BattleField canvas;int sum=1;//设置关卡数目
    JFrame frame=new JFrame();
    public Window()
    {

        Play0 pl=new Play0("music.mp3");
        pl.start();//打开时就开始播放音乐
        Gameload=new Gameload(sum);
        canvas=new BattleField(Gameload);


        frame.setLayout(new BorderLayout());
        frame.add(canvas,BorderLayout.CENTER);
        frame.setSize(700,690);//设置窗口大小

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton exit=new JButton("退出");//设置退出游戏按钮
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                pl.stop();
                exit.setFocusable(false);
            }
        });//设置窗口关闭
        JButton next=new JButton("下一关");//设置下一关按钮

        JButton refresh=new JButton("重新开始");//设置重新开始按钮
        refresh.addActionListener(new ActionListener() {//按下”重新开始“按钮的监视器
            @Override
            public void actionPerformed(ActionEvent e) {
                Gameload.init(sum);
                canvas.repaint();
                refresh.setFocusable(false);
            }
        });
        JButton musicstop=new JButton("关闭音乐");//设置关闭音乐的按钮

        JPanel buttons=new JPanel();//新建一个JPanel 容纳所有的按钮
        JPanel lbadtxt=new JPanel(new BorderLayout());//显示相关字体和文本框
        JPanel txtandfield=new JPanel();//新建



        JTextField mission=new JTextField(2);
        mission.setEditable(false);
        JLabel missions=new JLabel("关卡数");
        mission.setText(String.valueOf(sum));//显示相关的关卡数
        txtandfield.add(missions);
        txtandfield.add(mission);

        JLabel titles=new JLabel("<html>狼<br>狼<br>追<br>羊<br>羊<br>",JLabel.CENTER);//设置标题按钮
        Font f=new Font("微软雅黑",Font.BOLD,40);//设置字体
        titles.setFont(f);
        titles.setForeground(Color.red);

        lbadtxt.add(titles,BorderLayout.CENTER);
        lbadtxt.add(txtandfield,BorderLayout.NORTH);


        buttons.add(exit);//设置按钮控件，并放置在button panel里
        buttons.add(next);
        buttons.add(refresh);
        buttons.add(musicstop);



        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sum++;//关卡数控件


                if(sum<=2){
                    mission.setText(String.valueOf(sum));//显示相关的关卡数
                Gameload.init(sum);
                canvas.repaint();
                    next.setFocusable(false);

                }
                else{
                    JOptionPane.showMessageDialog(null, "关卡数超出，程序退出", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);

                }
                next.setFocusable(false);
            }
        });//控件

        musicstop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pl.stop();
                musicstop.setFocusable(false);
            }
        });



        frame.add(buttons,BorderLayout.SOUTH);//放在界面的南部（即下部）

        frame.add(lbadtxt,BorderLayout.EAST);//放在界面的东部（即右部）


        frame.addKeyListener(new KeyListener(){//创建监视器
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode()==KeyEvent.VK_UP)
                {
                    Gameload.moveUp();//监控键盘，如果按下方向上键，则执行向上走的方法

                }else if(event.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    Gameload.moveDown();

                }else if(event.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    Gameload.moveLeft();

                }else if(event.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    Gameload.moveRight();
                }
                canvas.repaint();
                if(Gameload.isEnd()) {
                    JOptionPane.showMessageDialog(null, "游戏结束");
                    System.exit(0);

                }
            }
            public void keyReleased(KeyEvent event) {
            }
            public void keyTyped(KeyEvent event) {
            }

        });


        frame.setVisible(true);
        frame.setFocusable(true);
    }
    public static void main(String[] args) {
        Window show=new Window();

    }
}
