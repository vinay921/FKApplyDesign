
import java.util.*;
class game
{
    public static int  z=1;
    int[][] bd=new int[3][3];
    public static boolean comp=false;
    public boolean completed(int p)
    {
        return ((bd[0][0]+bd[0][1]+bd[0][2]==p*3)||(bd[1][0]+bd[1][1]+bd[1][2]==p*3)|| (bd[2][0]+bd[2][1]+bd[2][2]==p*3) || (bd[0][0]+bd[1][0]+bd[2][0]==p*3)|| (bd[0][1]+bd[1][1]+bd[2][1]==p*3)|| (bd[0][2]+bd[1][2]+bd[2][2]==p*3)|| (bd[0][0]+bd[1][1]+bd[2][2]==p*3)|| (bd[2][0]+bd[1][1]+bd[0][2]==p*3));
    }
    public void display()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                System.out.print(bd[i][j]+" ");
            System.out.println("");
        }


    }
    public void whowins()
    {
        if(completed(1))
        {
            System.out.println("A WINS");
            comp=true;
        }
        else if(completed(-1))
        {
            System.out.println("B WINS");
            comp=true;
        }
        else
        {
            if(comp)
            {
                System.out.println("TIE");
            }

        }
    }
    public void type(int x,int y,int q)
    {
        if(x<0||y<0||x>2||y>2)
        {
            System.out.println("not a valid position");
            return;
        }
        if(bd[x][y]!=0)
        {
            if(q==1)
            {
                System.out.println("chose another position");
                return;
            }
            else
            {
                return;
            }
        }
        if(z==-1)
            System.out.println("computer choose "+x+" "+y);
        bd[x][y]=z;
        if(z==1)
            z=-1;
        else
            z=1;
    }

}
public class Main
{
    public static void main(String args[])
    {
        System.out.println("1. GAME BETWEEN TWO HUMANS");
        System.out.println("2. GAME BETWEEN COMPUTER");
        Scanner s=new Scanner(System.in);
        game g=new game();
        Random r=new Random();
        int x=0,y=0,q=0;
        q=s.nextInt();
        while(!g.comp)
        {
            if(g.z==1)
                System.out.println("persom a turn");
            else
                System.out.println("person b turn");
            if(g.z==1)
            {
                x=s.nextInt();
                y=s.nextInt();
            }else
            {
                x=r.nextInt(3);
                y=r.nextInt(3);
            }
            g.type(x,y,q);
            g.display();
            g.whowins();

        }



    }
}
