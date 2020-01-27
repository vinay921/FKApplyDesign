import java.util.*;
interface person1
{
    boolean type(displayer d,checker m);
}
interface checker1
{
    void whowins(displayer d);

    boolean completed(int p,displayer d);
}
interface displayer1
{
    void create();
    void display();
}
class displayer implements displayer1
{

    int[][] bd;
    int n;
    int[] r;
    int[] c;
    Scanner s=new Scanner(System.in);
    public void create()
    {
        System.out.println("enter the size of square block");
        n=s.nextInt();
        bd=new int[n][n];
        r=new int[n];
        c=new int[n];
    }
    public void display()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                System.out.print(bd[i][j]+" ");
            System.out.println("");
        }


    }
}
class person implements person1
{
    Scanner s=new Scanner(System.in);
    public boolean type(displayer d,checker m)
    {
        int x=s.nextInt();
        int y=s.nextInt();
        if(x<0||y<0||x>=d.n||y>=d.n)
        {
            System.out.println("not a valid position");
            return false;
        }
        if(d.bd[x][y]!=0)
        {
            System.out.println("already filled..chose another position");
            return false;

        }
        d.bd[x][y]=m.z;
        d.r[x]+=m.z;
        d.c[y]+=m.z;
        return true;


    }

}
class checker implements checker1
{
    int  z=1;
    boolean comp=false;

    public boolean completed(int p,displayer d)
    {
        int k=d.n;
        for(int i=0;i<k;i++)
        {
            if(d.r[i]==p*k)
                return true;
        }
        for(int i=0;i<k;i++)
        {
            if(d.c[i]==p*k)
                return true;
        }
        int cx=0;
        for(int i=0;i<k;i++)
        {
            if(d.bd[i][i]==p)
                cx++;
        }
        if(cx==k)
            return true;
        cx=0;
        for(int i=0;i<k;i++)
        {
            if(d.bd[i][k-1-i]==p)
                cx++;
        }
        if(cx==k)
            return true;

        return false;
    }

    public void whowins(displayer d)
    {
        if(completed(1,d))
        {
            System.out.println("A WINS");
            comp=true;
        }
        else if(completed(-1,d))
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

}

class compu implements person1
{
    Random r=new Random();

    public boolean type(displayer d,checker m)
    {
        int x,y,k=d.n;
        do{
            x=r.nextInt(k);
            y=r.nextInt(k);
        }while(d.bd[x][y]!=0);
        d.bd[x][y]=-1;
        d.r[x]+=-1;
        d.c[y]+=-1;
        System.out.println("computer choose "+x+" "+y);
        return true;
    }

}
public class Main
{
    public static void main(String args[])
    {
        System.out.println("WHATS YOUR UPTO BUDDY::");
        System.out.println("1. GAME BETWEEN TWO HUMANS");
        System.out.println("2. GAME BETWEEN COMPUTER");
        Scanner s=new Scanner(System.in);
        int q=s.nextInt();
        displayer d=new displayer();
        d.create();

        checker m=new checker();

        person a=new person();
        person1 b;
        if(q==1)
        {
            b=new person();
        }
        else
        {
            b=new compu();
        }
        int x,y;
        while(!m.comp)
        {
            if(m.z==1)
            {
                System.out.println("persom a turn");

                if(a.type(d,m))
                    m.z=-1;

            }
            else
            {
                System.out.println("person b turn");
                if(b.type(d,m))
                    m.z=1;

            }
            d.display();
            m.whowins(d);


        }

    }
}
