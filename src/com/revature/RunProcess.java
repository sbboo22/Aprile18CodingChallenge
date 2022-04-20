package com.revature;

import java.util.*;

public class RunProcess {
    private LinkedList<Character> process = new LinkedList<Character>();
    private List<char[]> dependencies = new ArrayList<>();

    private LinkedList<char[]> runorders = new LinkedList<char[]>();

    public void solve(LinkedList<Character> process, ArrayList<char[]> dependencies){
        this.dependencies = dependencies;
        this.process = process;
        char[] valid = new char[process.size()];
        boolean flag;
        boolean solveable = true;
        int count = 0;
        for(char p : process){
            flag = false;
            for(int i = 0; i< dependencies.size(); i++){
                if (p == dependencies.get(i)[1]){
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                valid[count] = p;
                count = count+ 1;
            }
        }

        for (char[] d : dependencies){
            for (char p : process){
                if (p == d[1]){
                    for (int i = 0; i < valid.length; i ++){
                        if(d[0] == valid[i]){
                            try {
                                valid[count] = p;
                                count = count + 1;
                                break;
                            }catch (ArrayIndexOutOfBoundsException e){
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int x = 0; x < valid.length && solveable == true; x++) {
            solveable = false;
            for (char y : process) {
                if (y == valid[x]) {
                    solveable = true;
                    break;
                }if(process.size() - 1 == x && !solveable);{
                    solveable = false;
                }
            }
        }

        if (solveable) {
            System.out.println("Here is a way to start the program: ");
            for (char x : valid)
                System.out.print(x + " ");
        }else{
            System.out.println("This process cannot run");
        }

    }
    private boolean validOrder(LinkedList<Character> process, char[] valid){
        boolean completerun = true;
        boolean flag = false;
        for(Character p : process){
            flag = false;
            for (int i = 0; i < valid.length; i ++){
                if (p == valid[i]){
                    flag = true;
                    break;
                }if(i == valid.length-1){
                    completerun = false;
                }
            }
        }

        return completerun;
    }
}
