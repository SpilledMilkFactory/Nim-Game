/*
Christopher Sutton                                  **Note To Professor: I have found its easiest to test in LMS when all input values are 1 due to the
Unit 5 homework Nim Game                            random nature of the program (first input value can be 0 or 1 for coin toss)**
11/20/2018
the first player to rid themselves of all marbles wins. cannot take less than one or more than half
*/

import java.util.Random;
import java.util.Scanner;

public class Nim
{
	Random rand = new Random();
	public static void main(String[] args)
	{
		Random rand = new Random();
		Scanner kbd = new Scanner(System.in);
		int total =  10+ rand.nextInt(91), takePC, takeNPC;		//variables deal directly with pile size
		int coinToss1, coinChoice1, coinToss2;                          //variables deal directly with coin toss
		int flag = 1;
		System.out.println("The Starting pool of marbles is: "+total);
		
		System.out.println("Choose heads or tails to see who goes first: Heads=0 Tails=1: ");
		coinToss1 = rand.nextInt(2);						
		coinChoice1 = kbd.nextInt();
		
		
		while (coinChoice1<0 || coinChoice1>1)
		 {
                    System.out.println("Invalid coin toss choice.");
                    System.out.println("Please Enter valid coin toss choice: ");
                    coinChoice1 = kbd.nextInt();
		 }
		 
		if (coinToss1==coinChoice1 && coinToss1==0)                     // result of first coin toss for order of turns
                    System.out.println("It is Heads! You go first!");
		else if  (coinToss1!=coinChoice1 && coinToss1==0 )
                    System.out.println("It was Heads! You go second!");
		else if (coinToss1==coinChoice1 && coinToss1==1)
                    System.out.println("It is Tails! You go first!");
		else if (coinToss1!=coinChoice1 && coinToss1==1)
                    System.out.println("It was Tails! You go second!");

		
		System.out.println("Now I will flip a coin to see what difficulty level the computer with play on.");
		coinToss2 = rand.nextInt(2);                                    //second coin toss for difficulty (no choice, its completely random)
		
		for(int counter=0; counter<=2;counter++)
		{
                    System.out.println(".");
		}
                    if (coinToss2==0)
                    System.out.println("The computer will play in Smart Mode.");
		else
                    System.out.println("The computer will play in Stupid Mode.");
		
                   
                //Start of various outcomes when User goes first    
		while (coinToss1==coinChoice1 && coinToss1==0 ||                //game starts here if user goes first
			coinToss1==coinChoice1 && coinToss1==1)
		{	
			while (smartMode(coinToss2) && total>1)                 //if user goes first and program runs in Smart Mode.
			{
				System.out.println("Enter the number of marbles you want to take from the pile: ");
				takePC= kbd.nextInt();
				if (takePC>total/2 || takePC<1)
				{	
                                    System.out.println("Error, invalid choice.");
                                    System.out.println("Please enter a valid number of marbles to take: ");
                                    takePC= kbd.nextInt();
				}
				total = total-takePC;
				System.out.println("The new total is: "+total);
				flag = 1;
                                
                                while (computerTurn(flag) && total>1)           //computer's turn when it goes second in Smart Mode.
                                {
                                    takeNPC=total;
                                    takeNPC= getComputerSmartMove(takeNPC);    
                                    System.out.println("The computer makes its move.");
                                    for(int counter=0; counter<=2;counter++)
                                    {
                                        System.out.println(".");
                                    }
                                    System.out.println("The computer takes: "+takeNPC);
                                    total = total-takeNPC;
                                    System.out.println("The new total is: "+total);
                                    flag = 0; 
                                }
			}
			while (!(smartMode(coinToss2)) && total>1)              //if user goes first and program runs in Stupid Mode.
			{
				System.out.println("Enter the number of marbles you want to take from the pile: ");
				takePC= kbd.nextInt();
				if (takePC>total/2 || takePC<1)
				{	
                                    System.out.println("Error, invalid choice.");
                                    System.out.println("Please enter a valid number of marbles to take: ");
                                    takePC= kbd.nextInt();
				}
				total = total-takePC;
				System.out.println("The new total is: "+total);
				flag = 1;
                                
                                
                                while (computerTurn(flag) && total>1)            //computer's turn when it goes second in Stupid Mode.
                                {
                                    takeNPC=total;
                                    takeNPC= getComputerStupidMove(takeNPC);    
                                    System.out.println("The computer makes its move.");
                                    for(int counter=0; counter<=2;counter++)
                                    {
                                        System.out.println(".");
                                    }
                                    System.out.println("The computer takes: "+takeNPC);
                                    total = total-takeNPC;
                                    System.out.println("The new total is: "+total);
                                    flag = 0; 
                                }
			}     

			if (total==1)                                            //taking the last turn for either side.
			{       
                            if (!(computerTurn(flag)))
                            {
				System.out.println("Enter the number of marbles you want to take from the pile: ");
				takePC= kbd.nextInt();
				while (takePC<1 || takePC>1)
				{
				System.out.println("Error, invalid choice.");
				System.out.println("Please enter a valid number of marbles to take: ");
				takePC = kbd.nextInt();
				}
				total = total-takePC;
                                System.out.println("The new total is: "+total);
				System.out.println("You Lose. Better luck next time.");
				System.exit(0);
                            }
                            if (computerTurn(flag))
                            {
                               takeNPC = 1;                                      //if computer gets last turn (1 marble left) it will always take one and total will always equal zero by end of turn
                               total = 0;                                        //so there is no point in having extra maths here and I've just set the variables to their end results.
                               System.out.println("The computer makes its move.");
                               for(int counter=0; counter<=2;counter++)
                               {
                                   System.out.println(".");
                               }
                               System.out.println("The computer takes: "+takeNPC);
                               System.out.println("The new total is: "+total);
                               System.out.println("You Win!");
                               System.exit(0);
                            }
                                    
			}		
		}//^^End of various outcomes when User goes first^^  
                
                //Start of various outcomes when Computer goes first.
                while (coinToss1!=coinChoice1 && coinToss1==0 ||		//game starts here if computer goes first
			coinToss1!=coinChoice1 && coinToss1==1)
		{
                    while (smartMode(coinToss2) && total>1)			//if computer goes first and program runs in Smart Mode.
                    {
                        while ((computerTurn(flag)) && total>1)
                        {    
                            takeNPC=total;
                            takeNPC= getComputerSmartMove(takeNPC);    
                            System.out.println("The computer makes its move.");
                            for(int counter=0; counter<=2;counter++)
                            {
                                System.out.println(".");                               
                            }
                            System.out.println("The computer takes: "+takeNPC);
                            total = total-takeNPC;
                            System.out.println("The new total is: "+total);
                            flag = 0;
                        }
                       
                         
                        while (!(computerTurn(flag)) && total>1)
                        {
                            System.out.println("Enter the number of marbles you want to take from the pile: ");
				takePC= kbd.nextInt();                           // users turn when user goes second in Smart Mode.
				if (takePC>total/2 || takePC<1)
				{	
                                    System.out.println("Error, invalid choice.");
                                    System.out.println("Please enter a valid number of marbles to take: ");
                                    takePC= kbd.nextInt();
				}
				total = total-takePC;
				System.out.println("The new total is: "+total);
				flag = 1; 
                           
                        }  
                        
                    }         
                
                    while (!(smartMode(coinToss2)) && total>1)                                                                  
                    {
                        
                            takeNPC=total;                                      //if computer goes first and program runs in Stupid Mode.
                            takeNPC= getComputerStupidMove(takeNPC);    
                            System.out.println("The computer makes its move.");
                            for(int counter=0; counter<=2;counter++)
                            {
                                System.out.println("."); 
                            }
                            System.out.println("The computer takes: "+takeNPC);
                            total = total-takeNPC; 
                            System.out.println("The new total is: "+total);
                              flag = 0;
                              
                        
                      
                        
                        while (!(computerTurn(flag)) && total>1)
                        {
                            System.out.println("Enter the number of marbles you want to take from the pile: "); 
				takePC= kbd.nextInt();                          // users turn when user goes second in Stupid Mode.
				if (takePC>total/2 || takePC<1)
				{	
                                    System.out.println("Error, invalid choice.");
                                    System.out.println("Please enter a valid number of marbles to take: ");
                                    takePC= kbd.nextInt();
				}
				total = total-takePC;
				System.out.println("The new total is: "+total);
				flag = 1;
                           
                        }     
                    }
                    
                    if (total==1)                                               //taking the last turn for either side.
                    {       
                        if (!(computerTurn(flag)))                      
                        {
                            System.out.println("Enter the number of marbles you want to take from the pile: ");
                            takePC= kbd.nextInt();
                                
			while (takePC<1 || takePC>1)
			{
                            System.out.println("Error, invalid choice.");
                            System.out.println("Please enter a valid number of marbles to take: ");
                            takePC = kbd.nextInt();
			}
                                
			total = total-takePC;
                        System.out.println("The new total is: "+total);
                        System.out.println("You Lose. Better luck next time.");
                        System.exit(0);
                        }
                        
                        if (computerTurn(flag))
                        {
                            takeNPC = 1;                                        //if computer gets last turn (1 marble left) it will always take 1 and total will always equal 0 by end of turn
                            total = 0;                                          //so there is no point in having extra maths here and I've just set the variables to their end results.
                            System.out.println("The computer makes its move.");
                            for(int counter=0; counter<=2;counter++)
                            {
                                System.out.println(".");
                            }
                                System.out.println("The computer takes: "+takeNPC);
                                System.out.println("The new total is: "+total);
                                System.out.println("You Win!");
                                System.exit(0);
                        }              
                    }
                } //^^End of various outcomes when computer goes first^^
            }

	//Start of all included methods
	public static int getComputerStupidMove(int num)                        //algorithm for the Stupid Move choice
	{
		Random rand = new Random();
                num = 1+ rand.nextInt(num/2);
		return num;
	}
	
	public static int getComputerSmartMove(int num)                         //algorithm for the Smart Move choice
	{   
            Random rand = new Random();
            int randomLegalMove=0; 
            
            if(num==63 || num==31 || num==15 || num==7 || num==3)
		{
			randomLegalMove= 1+ rand.nextInt(num/2);
			num=num-randomLegalMove;
		}
		else if (num>((int)Math.pow(2,6)-1))                            //is total greater than 63?
		{
			randomLegalMove= num-((int)Math.pow(2,6)-1);
			num = (int)Math.pow(2,6)-1;
		}
		else if (num<((int)Math.pow(2,6)-1) && num>((int)Math.pow(2,5)-1))
		{                                                               //is total greater than 31 but less than 63?           
			randomLegalMove= num-((int)Math.pow(2,5)-1);
			num = (int)Math.pow(2,5)-1;
		}
		else if  (num<((int)Math.pow(2,5)-1) && num>((int)Math.pow(2,4)-1))
		{                                                               //is total greater than 15 but less than 31?
			randomLegalMove= num-((int)Math.pow(2,4)-1);
			num = (int)Math.pow(2,4)-1;
		}
		else if (num<((int)Math.pow(2,4)-1) && num>((int)Math.pow(2,3)-1))
		{                                                               //is total greater than 7 but less than 15?
			randomLegalMove= num-((int)Math.pow(2,3)-1);
			num = (int)Math.pow(2,3)-1;
		}
		else if (num<((int)Math.pow(2,3)-1) && num>((int)Math.pow(2,2)-1))
		{                                                               //is total greater than 3 but less than 7?
			randomLegalMove= num-((int)Math.pow(2,2)-1);
			num = (int)Math.pow(2,2)-1;
		}
                else if (num<3)
                {    
                    randomLegalMove=1;                                          //if total is <= 3 players can ONLY take 1 regardless due to the rules of the game.
                }
		return randomLegalMove;
	}
	public static boolean computerTurn(int flag)                            //used simply to trigger the change of turn
	{
		if (flag==1)
			return true;
		else
			return false;
	}
	
	public static boolean smartMode(int difficultyMode)                     //tests whether to use Smart Mode or Stupid mode
	{
		if (difficultyMode==0)                         //Smart mode is 0
			return true;
		else
			return false;
	}
        //^^End of all included Methods^^
}	 




/*
Sample Output:

####(user goes first, comp in stupid mode) (loss)########################################################

The Starting pool of marbles is: 72
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It is Heads! You go first!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Stupid Mode.
Enter the number of marbles you want to take from the pile: 
30
The new total is: 42
The computer makes its move.
.
.
.
The computer takes: 21
The new total is: 21
Enter the number of marbles you want to take from the pile: 
1
The new total is: 20
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 19
Enter the number of marbles you want to take from the pile: 
2
The new total is: 17
The computer makes its move.
.
.
.
The computer takes: 7
The new total is: 10
Enter the number of marbles you want to take from the pile: 
5
The new total is: 5
The computer makes its move.
.
.
.
The computer takes: 2
The new total is: 3
Enter the number of marbles you want to take from the pile: 
1
The new total is: 2
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 1
Enter the number of marbles you want to take from the pile: 
1
The new total is: 0
You Lose. Better luck next time.


####(user goes first, comp in stupid mode) (win)############################################################


The Starting pool of marbles is: 36
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It is Heads! You go first!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Stupid Mode.
Enter the number of marbles you want to take from the pile: 
16
The new total is: 20
The computer makes its move.
.
.
.
The computer takes: 3
The new total is: 17
Enter the number of marbles you want to take from the pile: 
8
The new total is: 9
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 8
Enter the number of marbles you want to take from the pile: 
4
The new total is: 4
The computer makes its move.
.
.
.
The computer takes: 2
The new total is: 2
Enter the number of marbles you want to take from the pile: 
1
The new total is: 1
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 0
You Win!

####(user goes 1st, comp smart mode) (with error readouts)(loss)############################################################

run:
The Starting pool of marbles is: 19
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It is Heads! You go first!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Smart Mode.
Enter the number of marbles you want to take from the pile: 
0
Error, invalid choice.
Please enter a valid number of marbles to take: 
1
The new total is: 18
The computer makes its move.
.
.
.
The computer takes: 3
The new total is: 15
Enter the number of marbles you want to take from the pile: 
7
The new total is: 8
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 7
Enter the number of marbles you want to take from the pile: 
4
Error, invalid choice.
Please enter a valid number of marbles to take: 
3
The new total is: 4
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 3
Enter the number of marbles you want to take from the pile: 
1
The new total is: 2
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 1
Enter the number of marbles you want to take from the pile: 
1
The new total is: 0
You Lose. Better luck next time.


####(user goes first,comp in smart mode)(win)############################################################

The Starting pool of marbles is: 62
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It is Heads! You go first!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Smart Mode.
Enter the number of marbles you want to take from the pile: 
31
The new total is: 31
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 30
Enter the number of marbles you want to take from the pile: 
15
The new total is: 15
The computer makes its move.
.
.
.
The computer takes: 5
The new total is: 10
Enter the number of marbles you want to take from the pile: 
3
The new total is: 7
The computer makes its move.
.
.
.
The computer takes: 3
The new total is: 4
Enter the number of marbles you want to take from the pile: 
1
The new total is: 3
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 2
Enter the number of marbles you want to take from the pile: 
1
The new total is: 1
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 0
You Win!

####(user second, stupid mode) (win)############################################################

The Starting pool of marbles is: 85
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It was Tails! You go second!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Stupid Mode.
The computer makes its move.
.
.
.
The computer takes: 33
The new total is: 52
Enter the number of marbles you want to take from the pile: 
20
The new total is: 32
The computer makes its move.
.
.
.
The computer takes: 8
The new total is: 24
Enter the number of marbles you want to take from the pile: 
12
The new total is: 12
The computer makes its move.
.
.
.
The computer takes: 4
The new total is: 8
Enter the number of marbles you want to take from the pile: 
4
The new total is: 4
The computer makes its move.
.
.
.
The computer takes: 2
The new total is: 2
Enter the number of marbles you want to take from the pile: 
1
The new total is: 1
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 0
You Win!


####(user second, comp stupid) (loss)############################################################

The Starting pool of marbles is: 69
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It was Tails! You go second!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Stupid Mode.
The computer makes its move.
.
.
.
The computer takes: 27
The new total is: 42
Enter the number of marbles you want to take from the pile: 
12
The new total is: 30
The computer makes its move.
.
.
.
The computer takes: 4
The new total is: 26
Enter the number of marbles you want to take from the pile: 
12
The new total is: 14
The computer makes its move.
.
.
.
The computer takes: 4
The new total is: 10
Enter the number of marbles you want to take from the pile: 
2
The new total is: 8
The computer makes its move.
.
.
.
The computer takes: 3
The new total is: 5
Enter the number of marbles you want to take from the pile: 
1
The new total is: 4
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 3
Enter the number of marbles you want to take from the pile: 
1
The new total is: 2
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 1
Enter the number of marbles you want to take from the pile: 
1
The new total is: 0
You Lose. Better luck next time.


####(user second, comp smart mode) (loss)############################################################

The Starting pool of marbles is: 82
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It was Tails! You go second!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Smart Mode.
The computer makes its move.
.
.
.
The computer takes: 19
The new total is: 63
Enter the number of marbles you want to take from the pile: 
31
The new total is: 32
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 31
Enter the number of marbles you want to take from the pile: 
15
The new total is: 16
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 15
Enter the number of marbles you want to take from the pile: 
7
The new total is: 8
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 7
Enter the number of marbles you want to take from the pile: 
3
The new total is: 4
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 3
Enter the number of marbles you want to take from the pile: 
1
The new total is: 2
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 1
Enter the number of marbles you want to take from the pile: 
1
The new total is: 0
You Lose. Better luck next time.

####(user second, comp smart mode)(win)############################################################


The Starting pool of marbles is: 31
Choose heads or tails to see who goes first: Heads=0 Tails=1: 
0
It was Tails! You go second!
Now I will flip a coin to see what difficulty level the computer with play on.
.
.
.
The computer will play in Smart Mode.
The computer makes its move.
.
.
.
The computer takes: 10
The new total is: 21
Enter the number of marbles you want to take from the pile: 
6
The new total is: 15
The computer makes its move.
.
.
.
The computer takes: 7
The new total is: 8
Enter the number of marbles you want to take from the pile: 
1
The new total is: 7
The computer makes its move.
.
.
.
The computer takes: 2
The new total is: 5
Enter the number of marbles you want to take from the pile: 
2
The new total is: 3
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 2
Enter the number of marbles you want to take from the pile: 
1
The new total is: 1
The computer makes its move.
.
.
.
The computer takes: 1
The new total is: 0
You Win!


*/