import java.util.*;
interface person1
{
    boolean type(displayer d,checker m);
}
interface checker1
{
    void whowins(displayer d);

    boolean completed(int p,int i,int j,int[][] b,int kk);
}
interface displayer1
{
    void create();
    void display();
}
class astack
{
    int arr[];
    int top, size, len;
    
    public astack(int n)
    {
        size = n;
        len = 0;
        arr = new int[size];
        top = -1;
    }
   
    public boolean isEmpty()
    {
        return top == -1;
    }
    
    public boolean isFull()
    {
        return top == size -1 ;        
    }

    public int getSize()
    {
        return len ;
    }

    
    public void push(int i,int j)
    {
        if(top + 1 >= size)
            throw new IndexOutOfBoundsException("Overflow Exception");
        if(top + 1 < size )
            arr[++top] = i;
        len++ ;
        if(top + 1 >= size)
            throw new IndexOutOfBoundsException("Overflow Exception");
        if(top + 1 < size )
            arr[++top] = j;
        len++ ;
    }
   
    public int pop()
    {
        if( isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        len-- ;
        return arr[top--]; 
         
    }    

    
}
 
class displayer implements displayer1
{

    int[][] bd;
    int n,k;
    
    Scanner s=new Scanner(System.in);
    public void create()
    {
        System.out.println("enter the size of square block of every tictactoe");
        k=s.nextInt();
        System.out.println("enter the total size of square block"+"multiples of "+k);
        n=s.nextInt();
        Main.as=new astack(n*n*2);
        bd=new int[n][n];
       
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
        //System.out.println("helo");
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
        Main.as.push(x,y);
        d.bd[x][y]=m.z;
        
        //count++;
        return true;


    }

}
class checker implements checker1
{
    int  z=1;
    boolean comp=false;
    
    public boolean completed(int p,int i,int j,int[][] b,int kk)
    {
        //return ((b[i][j]+b[i][j+1]+b[i][j+2]==p*3)||(b[i+1][j+0]+b[i+1][j+1]+b[i+1][j+2]==p*3)|| (b[i+2][j+0]+b[i+2][j+1]+b[i+2][j+2]==p*3) || (b[i+0][j+0]+b[i+1][j+0]+b[i+2][j+0]==p*3)|| (b[i+0][j+1]+b[i+1][j+1]+b[i+2][j+1]==p*3)|| (b[i+0][j+2]+b[i+1][j+2]+b[i+2][j+2]==p*3)|| (b[i+0][j+0]+b[i+1][j+1]+b[i+2][j+2]==p*3)|| (b[i+2][j+0]+b[i+1][j+1]+b[i+0][j+2]==p*3));
         for(i=0;i<kk;i++)
         {
             int x=0;
             for(j=0;j<kk;j++)
             {
                 x+=b[i][j];
             }
             if(x==p*kk)
             return true;
         }
         for(i=0;i<kk;i++)
         {
             int x=0;
             for(j=0;j<kk;j++)
             {
                 x+=b[j][i];
             }
             if(x==p*kk)
             return true;
         }
         return false;
    }
    public int check1(int i,int j,int[][] b,int kk)
    {
        if(completed(1,i,j,b,kk))
        return 1;
        else  if(completed(-1,i,j,b,kk))
        return -1;
        else return 0;
        
    }
    public int check(int n,int[][] b,int kk)
    {
        if(n==kk)
        {
            return check1(0,0,b,kk);
            
        }
        
        int[][] ex=new int[n/kk][n/kk];
         for(int i=0;i<n;i=i+kk)
         {
             for(int j=0;j<n;j=j+kk)
             {
                ex[i/kk][j/kk]=check1(i,j,b,kk); 
             }
             
         }
         return check(n/kk,ex,kk);
    }

    public void whowins(displayer d)
    {
       // System.out.println(d.n);
        int x=check(d.n,d.bd,d.k);
        if(x==1)
        {
            System.out.println("A WINS");
            comp=true;
        }
        else if(x==-1)
        {
            System.out.println("B WINS");
            comp=true;
        }
        else
        {
            
            if(Main.count==d.n*d.n)
            {
                System.out.println("TIE"+d.n);
                comp=true;
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
        Main.as.push(x,y);
        System.out.println("computer choose "+x+" "+y);
        return true;
    }

}
public class Main
{
    static int count=0;
    static astack as;
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
        int aa,bb;
        //System.out.println("helo");
        while(!m.comp)
        {
            //System.out.println("helo");
            if(m.z==1)
            {
                System.out.println("persom a turn");
                System.out.println("IF U WANT TO UNDO CHANGES press 1 else 2");
                int xx=s.nextInt();
                if(xx==1)
                {
                    bb=as.pop();
                    aa=as.pop();
                    d.bd[aa][bb]=0;
                    bb=as.pop();
                    aa=as.pop();
                    d.bd[aa][bb]=0;
                }
                else{
                    System.out.println("persom a turn");
                if(a.type(d,m))
                   {
                       m.z=-1;
                       count++;
                   }
                }

            }
            else
            {
                System.out.println("person b turn");
                if(b.type(d,m))
                   { 
                       m.z=1;
                       count++;
                   }

            }
            d.display();
            m.whowins(d);


        }

    }
}
