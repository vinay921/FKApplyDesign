import java.util.*;
interface manager{
    void create();
    void display();
    void undo();
    boolean check(int x,int y);
}
interface human {
    //int x=0,y=0;
    void type(sqmanager m);
}
interface human1{
    void type(hexmanager m);
}
interface god{

    void whowins(sqmanager m);
}
interface god1 {

    void whowins(hexmanager m);
}
class astack {
    int arr[];
    int top, size, len;
    public astack(int n) {
        size = n;
        len = 0;
        arr = new int[size];
        top = -1;
    }
    public void push(int i,int j) {
        if(top + 1 < size )
            arr[++top] = i;
        len++ ;
        if(top + 1 < size )
            arr[++top] = j;
        len++ ;
    }
    public int pop() {
        len-- ;
        return arr[top--];

    }
}
class sqjudge implements god {
    public boolean over(int p, int i, int j, int[][] b, int kk) {
        for (i = 0; i < kk; i++) {
            int x = 0;
            for (j = 0; j < kk; j++) {
                x += b[i][j];
            }
            if (x == p * kk)
                return true;
        }
        for (i = 0; i < kk; i++) {
            int x = 0;
            for (j = 0; j < kk; j++) {
                x += b[j][i];
            }
            if (x == p * kk)
                return true;
        }
        return false;
    }
    public int c1(int i, int j, int[][] b, int kk) {
        if (over(1, i, j, b, kk))
            return 1;
        else if (over(-1, i, j, b, kk))
            return -1;
        else return 0;
    }
    public int verify(int n, int[][] b, int kk) {
        if (n == kk) {
            return c1(0, 0, b, kk);
        }

        int[][] ex = new int[n / kk][n / kk];
        for (int i = 0; i < n; i = i + kk) {
            for (int j = 0; j < n; j = j + kk) {
                ex[i / kk][j / kk] = c1(i, j, b, kk);
            }

        }
        return verify(n / kk, ex, kk);
    }
    public void whowins(sqmanager m) {
        int ans = verify(m.total_size, m.board, m.size_of_each);
        if (ans == 1) {
            System.out.println("a wins");
            m.completed = true;
        } else if (ans== -1) {
            System.out.println("b wins");
            m.completed = true;
        } else {
            if (m.count == m.row_size * m.col_size) {
                System.out.println("TIE ");
                m.completed = true;
            }
        }
    }
}
class hexjudge implements god1{

    public int verify(int n,int[][] bd,int xx,int yy) {
        int aa = xx, bb = yy, p = bd[aa][bb], l = 0;
            while (aa >= 0 && bb >= 0 && bd[aa][bb] != -2 && bd[aa][bb] == p) {
            l++;
            aa = aa - 2;
            bb = bb - 2;
            }
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            while (aa < (2 * n - 1) && bb < (4 * n - 3) && bd[aa][bb] != -2 && bd[aa][bb] == p) {
                l++;
                aa = aa + 2;
                bb = bb + 2;
            }
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            int d1 = 0;
            l--;
            aa = xx;
            bb = yy;
            while (aa >= 0 && bb < 4 * n - 3 && bd[aa][bb] != -2 && bd[aa][bb] == p) {
                d1++;
                aa = aa - 1;
                bb = bb + 1;
            }
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            while (aa < 2 * n - 1 && bb >= 0 && bd[aa][bb] != -2 && bd[aa][bb] == p) {
                d1++;
                aa = aa + 1;
                bb = bb - 1;
            }
            d1--;
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            int d2 = 0;
            aa = xx;
            bb = yy;
            while (aa < 2 * n - 1 && bb < 4 * n - 3 && bd[aa][bb] != -2 && bd[aa][bb] == p) {
                d2++;
                aa = aa + 1;
                bb = bb + 1;
            }
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            while (aa >= 0 && bb >= 0 && bd[aa][bb] != -2 && bd[aa][bb] == p) {
                d2++;
                aa = aa - 1;
                bb = bb - 1;
            }
            d2--;
            aa = xx;
            bb = yy;
            p = bd[aa][bb];
            System.out.println(l + " " + d1 + " " + d2);
            if (l >= n || d1 >= n || d2 >= n)
                return p;
            else
                return 0;
        }

    public void whowins(hexmanager d)
    {
        int y=Main.stack.pop();
        int x=Main.stack.pop();
        Main.stack.push(x,y);
        int ax=verify(d.total_size,d.board,x,y);
        if(ax==1)
        {
            System.out.println("A WINS");
            d.completed=true;
        }
        else if(ax==-1)
        {
            System.out.println("B WINS");
            d.completed=true;
        }
        else
        {
            if(d.count==3*d.total_size*d.total_size-3*d.total_size+1)
            {
                System.out.println("TIE");
                d.completed=true;
            }

        }
    }
}
class computer implements human{
    Random r=new Random();
    int x,y;
    public void type(sqmanager m){
        do{
            int number=r.nextInt(3*m.total_size*m.total_size-3*m.total_size+1);
            for(int i=0;i<m.row_size;i++){
                for(int j=0;j<m.col_size;j++){
                    if(m.board[i][j]==number){
                        x=i;
                        y=j;
                        break;
                    }
                }
            }
        }while(!m.check(x,y));
        m.board[x][y]=-1;
        Main.stack.push(x,y);
    }
}
class hexcomputer implements human1{
    Random r=new Random();
    int x,y;
    public void type(hexmanager m){
        do{
            int number=r.nextInt(3*m.total_size*m.total_size-3*m.total_size+1);
            for(int i=0;i<m.row_size;i++){
                for(int j=0;j<m.col_size;j++){
                    if(m.board[i][j]==number){
                        x=i;
                        y=j;
                        break;
                    }

                }
            }
        }while(!m.check(x,y));
        m.board[x][y]=-1;
        Main.stack.push(x,y);
    }
}
class person implements human{
    Scanner s=new Scanner(System.in);
    int x,y;
    public void type(sqmanager m){
        do{
            System.out.println("select the number where you want to insert");
            int number=s.nextInt();

            for(int i=0;i<m.row_size;i++){
                for(int j=0;j<m.col_size;j++){
                    if(m.board[i][j]==number){
                        x=i;
                        y=j;
                        break;
                    }
                }
            }
        }while(!m.check(x,y));
        m.board[x][y]=m.chance;
        Main.stack.push(x,y);
    }
}
class hexperson implements human1{
    Scanner s=new Scanner(System.in);
    int x,y;
    public void type(hexmanager m){
        do{
            System.out.println("select the number where you want to insert");
            int number=s.nextInt();

            for(int i=0;i<m.row_size;i++){
                for(int j=0;j<m.col_size;j++){
                    if(m.board[i][j]==number){
                        x=i;
                        y=j;
                        break;
                    }

                }
            }
        }while(!m.check(x,y));
        m.board[x][y]=m.chance;
        Main.stack.push(x,y);
    }
}
class sqmanager implements manager{
    int[][] board;
    int size_of_each,total_size,row_size,col_size;
    boolean completed;
    int chance=1,count;
    Scanner s=new Scanner(System.in);
    public void create(){
        System.out.println("enter the size of square block of each tic-tac-toe");
        size_of_each=s.nextInt();
        System.out.println("enter the total size of square block");
        total_size=s.nextInt();
        board=new int[total_size][total_size];
        row_size=total_size;
        col_size=total_size;
        Main.stack=new astack(total_size*total_size*2);
        int num=3;
        for(int i=0;i<row_size;i++){
            for(int j=0;j<col_size;j++){
                board[i][j]=num;
                num++;
            }
        }
    }
    public void display(){
        for(int i=0;i<row_size;i++){
            for(int j=0;j<col_size;j++)
                if(board[i][j]==1)
                    System.out.print("X ");
                else if(board[i][j]==-1)
                    System.out.print("O ");
                else if(board[i][j]!=0)
                    System.out.print(board[i][j]+" ");
                else
                    System.out.print("  ");
            System.out.println("");
        }
    }
    public void undo(){
        System.out.println("do u want to undo changes, if yes press 1 else 2");
        int ans=s.nextInt();
        if(ans==1){
            int y=Main.stack.pop();
            int x=Main.stack.pop();
            board[x][y]=0;
            y=Main.stack.pop();
            x=Main.stack.pop();
            board[x][y]=0;
        }
    }
    public boolean check(int x,int y){
        if(board[x][y]==1||board[x][y]==-1) {
            System.out.println("already choosen , take another number");
            return false;
        }else {
            return true;
        }
    }
}
class hexmanager implements manager{
    int[][] board;
    int[][] std_board;
    int total_size,row_size,col_size;
    boolean completed=false;
    int chance=1,count;
    Scanner s=new Scanner(System.in);
    public void create(){
        System.out.println("enter the length of size of hexogon");
        total_size=s.nextInt();
        board=new int[2*total_size-1][4*total_size-3];
        std_board=new int[2*total_size-1][4*total_size-3];
        int al=-1,index=0;
        Main.stack=new astack(2*total_size*total_size*total_size);
        for(int i=0;i<2*total_size-1;i++){
            if(i<total_size) {
                index = 0;
                al = -1;
                for (int j = 0; j < 4 * total_size - 3; j++) {
                    if ((j < Math.abs(total_size - i - 1)) || (j >= Math.abs(3 * total_size - 2 + i))) {
                        board[i][j] = -2;
                    } else if (al == -1) {
                        al = 0;
                        index = j;
                    }
                }
                index++;
                while (index < 4 * total_size - 3) {
                    board[i][index] = -2;
                    index += 2;
                }
            }else{
                for(int j=0;j<4*total_size-3;j++){
                    board[i][j]=board[2*total_size-2-i][j];
                }
            }
        }
        int b=3;
        for(int i=0;i<2*total_size-1;i++){
            for(int j=0;j<4*total_size-3;j++){
                if(board[i][j]!=-2){
                    board[i][j]=b;
                    b++;
                }
            }
        }
        for(int i=0;i<2*total_size-1;i++){
            for(int j=0;j<4*total_size-3;j++){
                std_board[i][j]=board[i][j];
            }
        }
        row_size=2*total_size-1;
        col_size=4*total_size-3;
    }

    public void display(){
        for(int i=0;i<2*total_size-1;i++){
            for(int j=0;j<4*total_size-3;j++){
                if(board[i][j]!=-2)
                    System.out.print(board[i][j]);
                else
                    System.out.print(" ");

            }
            System.out.println("");
        }
    }
    public void undo(){
        System.out.println("do u want to undo changes, if yes press 1 else 2");
        int ans=s.nextInt();
        if(ans==1){
            int y=Main.stack.pop();
            int x=Main.stack.pop();
            board[x][y]=0;
            y=Main.stack.pop();
            x=Main.stack.pop();
            board[x][y]=0;
        }
    }
    public boolean check(int x, int y){
        if(board[x][y]==1||board[x][y]==-1) {
            System.out.println("already choosen , take another number");
            return false;
        }else {
            return true;
        }
    }
}
public class Main {
    static astack stack;
    public static void sqtictactoe(){
        Scanner s=new Scanner(System.in);
        sqmanager g=new sqmanager();
        sqjudge jud=new sqjudge();
        g.create();
        g.display();
        System.out.println("whats your upto buddy");
        System.out.println("1 . game between two humans");
        System.out.println("2 . game between human and computer");
        int choice1=s.nextInt();
        human a=new person();
        human b;
        if(choice1==1)
            b=new person();
        else
            b=new computer();
        while(!g.completed){
            if(g.chance==1){
                System.out.println("person a's turn");
                g.undo();
                System.out.println("choose any number");
                a.type(g);
                g.chance=-1;
                g.count++;

            }else{
                System.out.println("person b's turn");
                if(choice1==1) {
                    g.undo();
                }
                b.type(g);
                g.chance=1;
                g.count++;
            }
            g.display();
            jud.whowins(g);

        }
    }
    public static void hextictactoe(){
        Scanner s=new Scanner(System.in);
        hexmanager  g=new hexmanager();
        hexjudge    jud=new hexjudge();
        g.create();
        System.out.println("hello");
        g.display();
        System.out.println("whats your upto buddy");
        System.out.println("1 . game between two humans");
        System.out.println("2 . game between human and computer");
        int choice1=s.nextInt();
        hexperson a=new hexperson();
        human1 b;

        if(choice1==1)
            b=new hexperson();
        else
            b=new hexcomputer();
        while(!g.completed){
            if(g.chance==1){
                System.out.println("person a's turn");
                g.undo();
                System.out.println("choose any number");
                a.type(g);
                g.chance=-1;
                g.count++;
            }else{
                System.out.println("person b's turn");
                if(choice1==1) {
                    g.undo();
                }

                b.type(g);
                g.chance=1;
                g.count++;
            }
            g.display();
            jud.whowins(g);

        }
    }
    public static void main(String[] args) {
        System.out.println("type of game you want to play");
        System.out.println("1 . square tic-tac-toe");
        System.out.println("2 . hexogonal tic-tac-toe");
        Scanner s=new Scanner(System.in);
        int choice=s.nextInt();

        if(choice==1){
            sqtictactoe();
        }else{
            hextictactoe();
        }

    }
}
