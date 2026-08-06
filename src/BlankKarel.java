import stanford.karel.*;
public class BlankKarel extends SuperKarel {

    public int getWidth(){
        int width  = 1;
        while (frontIsClear()) {
            move();
            width++;}
        return width;}

    public int getHeight(){//when finished , karel facing west
        int height = 1;
        turnLeft();
        while (frontIsClear()) {
            move();
            height++;}
        turnLeft();
        return height;}

    public void keepMoving(){
        while (frontIsClear()) move();
    }

    public void divideDiagonallyHelper(int width) {
        int i=1;
        while(i<width){
        turnLeft();
        move();
        turnRight();
        moveAndPlaceBeeper();
        i++;}
    }

    public void divideDiagonally(int w , int h) {

        if(w==2 && h==2){
            if(noBeepersPresent())
                putBeeper();
            divideDiagonallyHelper(w);
        }
        else{
            if(noBeepersPresent()) putBeeper();
            divideDiagonallyHelper(w);
            turnAround();
            keepMoving();
            if (noBeepersPresent()) putBeeper(); turnLeft(); divideDiagonallyHelper(w);}
    }

    public void drawLines(int point, int beepers) {


        int[] segments = getSegments(point, beepers);
        for (int steps : segments) {
            for (int i = 0; i < steps; i++) {
                move();
            }
            if (noBeepersPresent()) {
                putBeeper();
            }
        }
    }

    private int[] getSegments(int point, int beepers) {
        switch (beepers) {
            case 3: return new int[]{point, point + 1, point + 1};                    // dashed line without edge beeper
            case 4: return new int[]{point - 1, point, point, point};                // dashed line without edge beeper
            case 5: return new int[]{0, point, point, point, point};                // dashed line with edge beeper
            case 6: return new int[]{point, 1, point + 1, 1, point + 1, 1};        // double lines
            default: return new int[]{};
        }
    }

    public void dashedLineWithoutEdgeBeeper() {
        while(frontIsClear()) {
         moveAndPlaceBeeper();
         move();
        }
    }

    public void heightOrWidthLessThan2(int dimension){

        if(dimension<=7){
            dashedLineWithoutEdgeBeeper ();}
        else if (dimension>7) {

            if(dimension%4==3)       drawLines(dimension/4 ,3);         // dashed line without edge beeper
            else if(dimension%4==0)  drawLines(dimension/4 ,4);        // dashed line without edge beeper
            else if(dimension%4==2)  drawLines((dimension-6)/4 , 6);  // double lines
            else if(dimension%4==1)  drawLines(dimension/4 , 5);     // dashed line with edge beeper
        }

    }

    public void placeBeeper(){
        if(noBeepersPresent())
            putBeeper();
    }

    public void moveAndPlaceBeeper(){
        move();
        placeBeeper();
    }

    public void drawLShape(boolean flag){

            if(flag) {turnLeft();}

            moveAndPlaceBeeper();

            if(flag) {turnRight();}
            else {turnLeft();}

            move();
    }

    public void drawInvertedLShape(boolean flag){

        if(flag) {turnRight();}
        else {turnLeft();}

        moveAndPlaceBeeper();

        if(flag) {turnLeft();}
        else {turnRight();}

        move();
    }

    public void zigZag(int numOfBeepers  , boolean flag) {

        if(numOfBeepers==3){
            drawLShape(flag);
            drawInvertedLShape(flag);

            if(flag) {turnLeft();}
            else {turnRight();}

            moveAndPlaceBeeper();
        }

        if(numOfBeepers==4){
            drawLShape(flag);
            drawInvertedLShape(flag);

            if(!flag)turnRight();

            drawLShape(flag);

            if(flag){turnRight();}
            else{turnLeft();}

            moveAndPlaceBeeper();
        }
    }

    public void fillGaps(int gaps , boolean flag ) {

        while (gaps != 0) {
           if(flag) {turnLeft();}
           else {turnRight();}

            move();
            if (noBeepersPresent()) putBeeper();gaps--;

            if(flag) {turnLeft();}
            else {turnRight();}

            move();
            if (noBeepersPresent()) putBeeper();gaps--;
            }
    }

    public void heightOrWidthEqualsTwo(int dimension , boolean flag ){

        if(dimension<=6){
            if(dimension==3||dimension==4 ) zigZag(dimension , flag);

            else {
                zigZag(4 , flag);

                if(dimension==5){
                    fillGaps(2 ,flag);
                }
                if(dimension==6) {
                    fillGaps(2, flag);

                    if(flag) {turnRight();}
                    else {turnLeft();}

                    moveAndPlaceBeeper();

                    if(flag) {turnRight();}
                    else {turnLeft();}

                    moveAndPlaceBeeper();
                }
            }
        }

        else if(dimension>6){

            if(dimension==7){
                if(!flag) {turnLeft();}

                dashedLineWithoutEdgeBeeper();

                if(flag) {turnLeft();}
                else {turnRight();}

                move();

                if(flag) {turnLeft();}
                else {turnRight();}

                dashedLineWithoutEdgeBeeper();
            }
            else if(dimension%4==0){
                if(!flag) {turnLeft();}

                drawLines(dimension/4 ,4);

                if(flag) {turnLeft();turnLeft();}
                else {turnRight();turnRight();}

                while (frontIsClear()) move();

                if(flag) {turnRight();}
                else {turnLeft();}

                move();

                if(flag) {turnRight();}
                else {turnLeft();}
                drawLines(dimension/4 ,4);
            }

            else if(dimension%4==1){
                if(!flag) {turnLeft();}

                drawLines(dimension/4 , 5);
                while (frontIsClear()) move();

                if(flag) {turnLeft();}
                else {turnRight();}

                move();

                if(flag) {turnLeft();}
                else {turnRight();}

                drawLines(dimension/4 , 5);
            }
            else if(dimension%4==2){
                if(!flag) {turnLeft();}

                drawLines((dimension-6)/4 , 6);
                while (frontIsClear()) move();

                if(flag) {turnLeft();}
                else {turnRight();}

                move();

                if(flag) {turnLeft();}
                else {turnRight();}

                drawLines((dimension-6)/4 , 6);
            }
            else if(dimension%4==3 ){
                if(!flag) {turnLeft();}

                drawLines(dimension/4 ,3);
                while (frontIsClear()) move();

                if(flag) {turnLeft();}
                else {turnRight();}

                move();

                if(flag) {turnLeft();}
                else {turnRight();}

                drawLines(dimension/4 ,3);
            }
        }
    }

    public void moveToPoint(int point){
        for(int i=0 ; i<point;i++){
            move();}
        if(noBeepersPresent()) putBeeper();

    }

    public void keepMovingWithBeepers(){
        while (frontIsClear()) {
            moveAndPlaceBeeper();
        }
    }

    public void drawVerticalLines( boolean doubleLines , int point) {

        moveToPoint(point);
        turnLeft();
        keepMovingWithBeepers();

        if(doubleLines){
            turnLeft();move();turnLeft();

            if(noBeepersPresent()) putBeeper();
            keepMovingWithBeepers();
        }
    }
    public void drawHorizontalLines(boolean doubleLines , int point){

        turnLeft();keepMoving();turnLeft();
        moveToPoint(point);
        turnLeft();
        keepMovingWithBeepers();

        if(doubleLines){

            turnLeft(); move();turnLeft();

            if(noBeepersPresent()) putBeeper();
            keepMovingWithBeepers();
        }
    }

    public void drawPlusSign(int w , int h){

        if(h%2 !=0 && w%2 !=0 ) {
            drawVerticalLines(false,w/2);
            drawHorizontalLines(false,h/2);
        }
        else if(h%2 ==0 && w%2 ==0){
            drawVerticalLines(true,w/2);
            drawHorizontalLines(true,h/2);
        }
        else if (h%2!=0 && w%2==0){
            drawVerticalLines(true,w/2);
            drawHorizontalLines(false,h/2);
        }
        else if(h%2==0 && w%2!=0){
            drawVerticalLines(false,w/2);
            drawHorizontalLines(true,h/2);
        }
    }


    public void select(int w , int h ) {

        if(w==1&&h==1) System.out.println("karel cant divide the map");

        if(w==h) divideDiagonally(w,h);   //NxN cases

        else if((w==1&&h==2) || (w==2&&h==1) ){    // 2x1 and 1x2
            if(noBeepersPresent())
                putBeeper();
            else {move();
            if (noBeepersPresent()) putBeeper();}
        }

        else if(h==1) heightOrWidthLessThan2(w);  // 1xN cases
        else if(w==1)
        {
            turnLeft();
            heightOrWidthLessThan2(h);   // Nx1 cases
        }

        else if(h==2) heightOrWidthEqualsTwo(w,true);  //heightEqualsTwo(w);  // 2xN cases
        else if(w==2) heightOrWidthEqualsTwo(h,false); //widthEqualsTwo(h);   // Nx2 cases

        else if(h>2 && w>2) drawPlusSign(w , h ) ;


    }
    public void run() {
       int width = getWidth();
       int height =getHeight();

        System.out.println("width =  " + width);
        System.out.println("height = " + height);

        select(width,height);
    }
}