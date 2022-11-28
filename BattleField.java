
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//游戏使用的场地初始化，即战场
public class BattleField extends JPanel {
    private BufferedImage box;
    private BufferedImage field;
    private BufferedImage hero;
    private BufferedImage target;
    private BufferedImage wall;
    private Gameload gl;
    public Graphics g;
    public BattleField(Gameload gl){
        this.gl=gl;

        try{
            box= ImageIO.read(new File("box.png"));
            field=ImageIO.read(new File("field.png"));
            hero=ImageIO.read(new File("hero.png"));
            target=ImageIO.read(new File("target.png"));
            wall=ImageIO.read(new File("wall.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void paint(Graphics g){
        this.g=g;

        BufferedImage image=null;
        for(int j=0;j<20;j++)
            for(int i=0;i<20;i++)
            {
                if(gl.getfield()[i][j].equals("B"))
                {
                    image=box;//将图像设置为box
                }else if(gl.getfield()[i][j].equals("E"))
                {
                    image=field;//将图像设置为底图
                }else if(gl.getfield()[i][j].equals("H")) {
                    image = hero;//将图像设置为人物
                }
                else if(gl.getfield()[i][j].equals("W")) {
                    image = wall;//将图像设置为墙面
                }
                else if (gl.getfield()[i][j].equals("T")){
                    image=target;//将图像设置为目标位置
                        }

                g.drawImage(image, 0+i*image.getWidth(), 0+j*image.getHeight(),null);
            }
    }

}


