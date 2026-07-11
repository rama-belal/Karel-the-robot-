import stanford.karel.*;

public class BlankKarel extends SuperKarel {

    public int getWidth() {
        int width  = 1;
        while (frontIsClear()) {
            move();
            width++;
        }
        return width;}

    public int getHeight() {//when finished , karel facing west
        int height = 1;
        turnLeft();
        while (frontIsClear()) {
            move();
            height++;
        }
        turnLeft();
        return height;}

    public void beeper() {
        while (frontIsClear())
        {
            move();
            if(noBeepersPresent())
            {
                putBeeper();
            }
        }
    }
    public void evenWidthEvenHeight(int w ) {   // case 1 : even width even height --> double line beepers{

        int point = w/2 ;
        for (int i=0 ; i<point; i++)
        {
           move();
        }
        if(noBeepersPresent())
        {
            putBeeper();
        }
        turnLeft();
        beeper() ;
        turnLeft();
        move();
        turnLeft();
        if(noBeepersPresent())
        {
            putBeeper();
        }
        beeper();
    }

    public void oddWidthOddHeight(int w , int h ) {  // case 2 : odd width odd height --> 4 chambers,2 lines of beepers

        int point = (w/2);
        for(int i=0 ; i<point ; i++)
        {
            move();
        }
        if(noBeepersPresent())
        {
            putBeeper();
        }
        turnLeft();
        beeper();
        turnLeft();
        if(frontIsClear())
        {
            move();
        }
        while (frontIsClear())
            move();
        turnLeft();
        for(int i=0 ; i<(h/2) ; i++)
        {
            move();
        }
        if (noBeepersPresent())
            putBeeper();
        turnLeft();
        beeper();
    }

    public void evenWidthOddHeight(int h )//case 3 --> even width odd height --> one vertical line of beepers
    {

        int point = h/2;
        turnLeft();
        for(int i=0 ; i<point;i++)
        {
            move();
        }
        if(noBeepersPresent())
            putBeeper();
        turnRight();
        beeper();
    }

    public void oddWidthEvenHeight(int w)//working
    {
        int point = w/2;
        for(int i=0 ; i<point;i++)
        {
            move();
        }
        if(noBeepersPresent())
            putBeeper();
        turnLeft();
        beeper();

    }

    public void run() {
       int width = getWidth();
       int height =getHeight();

        System.out.println("width = " + width);
        System.out.println("height = " + height);

       if(width==2&&height==2)
           System.out.println("karel cant divide the map");

       else if (width==1 || height==1)
           System.out.println("karel cant divide the map");

       else if(width%2==0 && height%2==0)
           evenWidthEvenHeight(width);

       else if(width%2!=0 && height%2!=0)
           oddWidthOddHeight(width, height);

       else if(height%2!=0 && width%2==0)
           evenWidthOddHeight(height);

       else if(width%2!=0 && height%2==0)
           oddWidthEvenHeight(width);
    }
}
