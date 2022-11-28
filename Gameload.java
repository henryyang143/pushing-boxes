import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Gameload {
    private boolean exist=true;
    // 游戏角色的坐标位置
    private int x;
    private int y;
    // 游戏为20x20的地图
    private String field[][] = new String[20][20];
    // 记录推箱子的目标位置
    private String target[][] = new String[20][20];
    public void init(int num) {
        try {


            BufferedReader reader = new BufferedReader(new FileReader("field" + String.valueOf(num) + ".txt"));
            // 读取文件的一行
            String line = reader.readLine();
            int j = 0;
            while (line != null) {
                // 以空格为分隔符号，分割一行数据；
                String strs[] = line.split(" ");
                for (int i = 0; i < 20; i++) {//初次设计的是20*20矩阵 以后根据要求重新编辑文件
                    field[i][j] = strs[i];
                    if (strs[i].equals("T")) {
                        // 记录目标位置
                        target[i][j] = "T";
                    } else {
                        // 非目标位置记为O
                        target[i][j] = "O";
                    }
                    // 记录角色的初始位置
                    if (strs[i].equals("H")) {
                        this.x = i;
                        this.y = j;
                    }
                }
                line = reader.readLine();
                j++;
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Gameload(int num) {
        // 从field文件加载地图信息
        try {
            BufferedReader reader = new BufferedReader(new FileReader("field" + String.valueOf(num) + ".txt"));
            // 读取文件的一行
            String line = reader.readLine();
            int j = 0;
            while (line != null) {
                // 以空格为分隔符号，分割一行数据；
                String strs[] = line.split(" ");
                for (int i = 0; i < 20; i++) {//初次设计的是20*20矩阵 以后根据要求重新编辑文件
                    field[i][j] = strs[i];
                    if (strs[i].equals("T")) {
                        // 记录目标位置
                        target[i][j] = "T";
                    } else {
                        // 非目标位置记为O
                        target[i][j] = "O";
                    }
                    // 记录角色的初始位置
                    if (strs[i].equals("H")) {
                        this.x = i;
                        this.y = j;
                    }
                }
                line = reader.readLine();
                j++;
            }
            reader.close();
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
    //角色向左移动
    public void moveLeft() {
        int a = x;
        int b = y;
        if (!field[a - 1][b].equals("W")) {//判断二维数组前一位是否为墙壁
            if (field[a - 1][b].equals("B")) {
                if (!field[a - 2][b].equals("B") && !field[a - 2][b].equals("W")) {
                    if (target[a][b].equals("T")) {//如果任务位置为目标位置
                        field[a - 1][b] = "H";//将人物向左平移一格
                        field[a][b] = "T";//将目标位置定在原来人物位置上
                        field[a - 2][b] = "B";
                        x = a - 1;//将人物位置x坐标平移一位
                    } else {//否则将人物向左平移
                        field[a - 1][b] = "H";
                        field[a][b] = "E";//将背景赋值为
                        field[a - 2][b] = "B";//将箱子向左平移两个
                        x = a - 1;
                    }
                }
            } else {//如果人物位置的左边不为墙壁位置
                if (target[a][b].equals("T")) {
                    field[a - 1][b] = "H";//当位置为目标位置时，将H向左移动
                    field[a][b] = "T";//将位置设置为T
                    x = a - 1;//向左位置设置
                } else {
                    field[a - 1][b] = "H";//不为目标位置时，将H向左移动
                    field[a][b] = "E";//将位置设置为背景
                    x = a - 1;//向左位置设置
                }
            }
        }
    }
    //角色向右移动
    public void moveRight() {
        int a = x;
        int b = y;
        if (!field[a + 1][b].equals("W")) {
            if (field[a + 1][b].equals("B")) {
                if (!field[a + 2][b].equals("B") && !field[a + 2][b].equals("W")) {
                    if (target[a][b].equals("T")) {
                        field[a + 1][b] = "H";
                        field[a][b] = "T";
                        field[a + 2][b] = "B";
                        x = a + 1;
                    } else {
                        field[a + 1][b] = "H";
                        field[a][b] = "E";
                        field[a + 2][b] = "B";
                        x = a + 1;
                    }
                }
            } else {
                if (target[a][b].equals("T")) {
                    field[a + 1][b] = "H";
                    field[a][b] = "T";
                    x = a + 1;
                } else {
                    field[a + 1][b] = "H";
                    field[a][b] = "E";
                    x = a + 1;
                }
            }
        }
    }
    //角色向上移动
    public void moveUp() {
        int a = x;
        int b = y;
        if (!field[a][b - 1].equals("W")) {
            if (field[a][b - 1].equals("B")) {
                if (!field[a][b - 2].equals("B") && !field[a][b - 2].equals("W")) {
                    if (target[a][b].equals("T")) {
                        field[a][b - 1] = "H";
                        field[a][b] = "T";
                        field[a][b - 2] = "B";
                        y = b - 1;
                    } else {
                        field[a][b - 1] = "H";
                        field[a][b] = "E";
                        field[a][b - 2] = "B";
                        y = b - 1;
                    }
                }
            } else {
                if (target[a][b].equals("T")) {
                    field[a][b - 1] = "H";
                    field[a][b] = "T";
                    y = b - 1;
                } else {
                    field[a][b - 1] = "H";
                    field[a][b] = "E";
                    y = b - 1;
                }
            }
        }
    }
    //角色向下移动
    public void moveDown() {
        int a = x;
        int b = y;
        if (!field[a][b + 1].equals("W")) {
            if (field[a][b + 1].equals("B")) {
                if (!field[a][b + 2].equals("B") && !field[a][b + 2].equals("W")) {
                    if (target[a][b].equals("T")) {
                        field[a][b + 1] = "H";
                        field[a][b] = "T";
                        field[a][b + 2] = "B";
                        y = b - 1;
                    } else {
                        field[a][b + 1] = "H";
                        field[a][b] = "E";
                        field[a][b + 2] = "B";
                        y = b + 1;
                    }
                }
            } else {
                if (target[a][b].equals("T")) {
                    field[a][b + 1] = "H";
                    field[a][b] = "T";
                    y = b + 1;
                } else {
                    field[a][b + 1] = "H";
                    field[a][b] = "E";
                    y = b + 1;
                }
            }
        }
    }
    //判断游戏是否结束
    public boolean isEnd() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (field[i][j].equals("T"))
                    return false;
            }
        return true;
    }
    public String[][] getfield() {
        return field;
    }

}
