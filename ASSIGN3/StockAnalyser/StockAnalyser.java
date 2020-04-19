
// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 3 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** The program contains two methods for analysing the prices of a stock over 
 *  the course of a day on the stock market.  There are several things that
 *  an invester may be interested in:
 *  The opening and closing prices (the first and the last prices for the day)
 *    and whether the stock rose or fell in price, and by how much
 *  The maximum and the minimum prices at any point during the day
 *  The average price during the day.
 *
 *  The program has two methods.  They both read a sequence of prices from
 *  the user (through the terminal window).
 *  One method computes and prints out the six numbers of interest:
 *    maximum, minimum, and average price.
 *    open and close price,
 *    amount it rose (or fell)
 *  The other method plots the prices through the day as a simple bar graph.
 */
public class StockAnalyser{

    /**
     * analysePrices reads a sequence of prices from the user  and prints out
     *   the maximum, minimum, and average price over the day
     *   the opening and closing prices,
     *   the amount the price rose or fell,
     * The sequence is terminated by any word (non-number) such as "close" or "end".
     * All the prices are in cents (as positive integers) 
     * The method must keep track of the maximum and minimum prices, the count and
     * sum of the prices, and the opening price.
     * It will need variables for each of these quantities, all of which 
     * need to be initialised to an appropriate value.
     * It will need a loop to keep reading the prices until there isn't an integer next.
     * [Core]
     *   - There is guaranteed to be at least one price,
     *   - You only need to print the maximum, minimum, and average price
     *   - Hint, keep track of the sum of the prices using a variable of type double
     * [Completion]
     *   - Print the opening and closing prices as well, along with the rise/fall.
     *   - The method should work even if there were no prices for the day
     * [Challenge] The method should also compute and print out
     *   - the standard deviation of the prices
     *   - the average size of the change between prices (where a rise of 10c is
     *     the same size change as a fall of 10c)
     *   - the second highest and second lowest price
     */
    public void analysePrices() {
        UI.clearText(); 

        int min = Integer.MAX_VALUE; 
        int max = Integer.MIN_VALUE; 
        double sum = 0;
        int count = 0;
        int open = 0;
        int close = 0;

        UI.println("Enter integers and end with 'done'");

        while(UI.hasNextInt()){
            int input = UI.nextInt(); //reads along the users input
            sum = sum + input; //inputs being added together

            if(input>max){ 
                max = input; // if the input is greater than the maximum, the maximum becomes the input
            }
            if(input<min){
                min = input; //if the minimum is less than the minimum, the minimum becomes the input
            }

            if(count==0){
                open = input;
            }

            count++; //counts the number of numbers inputted as it scans across the inputs
            close = input;
        }

        UI.println("Min: " + min);
        UI.println("Max: " + max);
        UI.println("Average: " + (sum/count));
        UI.println("Opening number: " + open);
        UI.println("Closing number: " + close);
        UI.println("Rise/Fall: " + (close-open));

        UI.nextLine(); // to clear out the input
    }

    /**
     * Reads a sequence of prices (integers) from the user (using Scanner
     * and the terminal window) and plots a bar graph of them, using narrow 
     * rectangles whose heights are equal to the price.
     * The sequence is terminated by any word (non-number) such as "close" or "end".
     * The method may assume that there are at most 24 numbers.
     * The method will need a loop to keep reading the prices until there isn't a number next.
     *  Each time round the loop, it needs to read the next price and work out where
     *  to draw the rectangle for the bar. 
     * [Core]
     *   - assume that all the numbers are between 0 and 380
     * [Completion]
     *   - Any price greater than 380 should be plotted 
     *      as if it were just 380.
     *   - The graph should also have a horizontal green line at the 
     *      height of the opening price and a red line at the height of the closing price.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels
     *   - Make the method ask for a maximum price first, then scale the y-axis and values 
     *     so that the largest numbers just fit on the graph.
     *   - The numbers on the y axis should reflect the scaling.
     */
    public void plotPrices() {
        UI.clearText();
        UI.clearGraphics();

        int x = 100; //start horizontal point
        int y = 450; //start y point //CHANGED FROM 40
        int width = 30; //width of each bar
        int count = 0;
        int open = 0;
        int close = 0;
        // int xAxis = 0;
        // int yAxis = 0;

        UI.println("Enter integers and end with 'done'");

        while(UI.hasNextInt()){
            int input = UI.nextInt(); //reads along the users input

            if(input>380){
                UI.setColor(Color.orange);
                UI.fillRect(x,y-380,width,380); //the rectangle
                UI.setColor(Color.black);
                UI.drawRect(x,y-380,width,380);
                UI.drawString("***",x,y-380);
            }
            else{
                UI.setColor(Color.orange);
                UI.fillRect(x,y-input,width,input); //the rectangle
                UI.setColor(Color.black);
                UI.drawRect(x,y-input,width,input); //outline of the rectangle
            }

            if(count==0){
                open = input;
            }

            close = input; 

            count++; //counts the number of numbers inputted as it scans across the inputs

            x=x+30;
        }

        UI.drawLine(x,y,y,y); //x axis of graph

        UI.setColor(Color.green);
        UI.drawLine(100,open,y,open); //opening price
        UI.setColor(Color.red);
        UI.drawLine(100,close,y,close); //closing price

        UI.nextLine(); // to clear out the input
        //UI.println("Done");
    }

    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public StockAnalyser(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Analyse Prices", this::analysePrices );
        UI.addButton("Plot Prices", this::plotPrices );
        UI.addButton("Quit", UI::quit );
    }
}
