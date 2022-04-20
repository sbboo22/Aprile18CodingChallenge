package com.revature;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        projects: a, b, c, d, e, f
//        dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
//        Output: f, e, a, b, d, c
        LinkedList<Character> processes = new LinkedList<Character>();
        String getProcess = "";
        String getDependcy = "";
        char [] dependencyStore;
        boolean goodInput = false;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter processes format = (xyz): ");
        while (!goodInput){
            getProcess = userInput.next();
            for (int i = 0; i < getProcess.length(); i ++){
                if (getProcess.charAt(i) == ','){
                    goodInput = false;
                    break;
                }else {
                    processes.add(getProcess.charAt(i));
                    goodInput = true;
                }
            }
        }

        ArrayList<char[]> dependencies = new ArrayList<>();
        boolean isProcessR = true;
        boolean isProcessL = true;
        while(!getDependcy.equals("quit")){
            System.out.println("please enter your dependency (\"quit\" - to quit): ");
            getDependcy = new String();
            getDependcy = userInput.next();
            if(getDependcy.length() == 2) {
                    isProcessR = false;
                    isProcessL=false;
                    for (int i = 0; i < processes.size(); i++) {
                        if (isProcessR || processes.get(i).equals(getDependcy.charAt(0)))
                            isProcessR = true;
                        else
                            isProcessR = false;
                        if (isProcessL || processes.get(i).equals(getDependcy.charAt(1)))
                            isProcessL = true;
                        else
                            isProcessL = false;
                        if(isProcessL && isProcessR) {
                            dependencies.add(getDependcy.toCharArray());
                            break;
                        }

                        if (i == processes.size() - 1)
                            isProcessR = false;
                    }
            }
            if(getDependcy == "quit"){
                break;
            }
            if (!isProcessL || !isProcessR && !getDependcy.equals("quit"))
                System.out.println("invalid dependency");

            if (getDependcy.length() > 2 && !getDependcy.equals("quit"))
                System.out.println("invalid dependency");
            if(getDependcy.length() < 2 && getDependcy != "quit")
                System.out.println("invalid dependency");
        }

        System.out.print("\nDependencies: ");
        for (char[] d : dependencies){
            System.out.print(d[0] + " | " + d[1] + " || ");
        }
        System.out.println("");
        RunProcess pro1 = new RunProcess();
        pro1.solve(processes, dependencies);

    }
}
