
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nidadinch
 */

class Instruction {
    private String instructionName;
    public String getInstructionName() { return this.instructionName; }

    public Instruction(String instructionName) {
        this.instructionName = instructionName;
    }
}

class  InstructionWithValue extends Instruction
{
    private int value;

    public int getValue() { return value; }

    public InstructionWithValue(String instructionName, int value) {
        super(instructionName);
        this.value = value;
    }
}



public class Midterm_20160808047{

    public static int flag=0;    
    public static int AC;
    public static int PC;

    public static String scanned;

    public static HashMap<Integer, Integer> memory =new HashMap<>();

    public static List<Instruction> instructionList = new ArrayList<Instruction>();

    public static void main(String[] args) {
        try {

            if(args.length != 1){
                System.out.println("Lütfen dosya yolunu doğru veriniz");
                if(args.length==0){
                    System.out.println("Dosya yolu girilmedi");
                }else
                    System.out.println("Birden fazla parametre girildi");
          //    return;
            }
            String textfile= args[0];
            File file=new File(textfile);
            BufferedReader buffread=new BufferedReader(new FileReader(file));
            String readline = "";

            while((readline=buffread.readLine())!=null){
                scanned=readline;
                String[] splitarray=readline.toUpperCase().split(" ", 3);
                if (splitarray.length == 2){
                //    ExecuteGivenCommand(Integer.parseInt(splitarray[0]), splitarray[1]);
                    instructionList.add(new Instruction(splitarray[1]));
                }
                else {
                  // ExecuteGivenCommand(splitarray[1],Integer.parseInt(splitarray[2]));
                    instructionList.add(new InstructionWithValue(splitarray[1],Integer.parseInt(splitarray[2])));
                }

            }
            PC = 0;
            Instruction currentInstruction = instructionList.get(PC);
            while (currentInstruction.getInstructionName() != "HALT")
            {
                PC ++;

                if (currentInstruction instanceof InstructionWithValue)
                {
                   ExecuteGivenCommand((InstructionWithValue) currentInstruction);
                }
                else
                {
                  ExecuteGivenCommand(currentInstruction);
                }
                if (currentInstruction.getInstructionName().equalsIgnoreCase("HALT"))
                {
                    return;
                }
                currentInstruction = instructionList.get(PC);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void START(){
        System.out.println("Program has been started with this command : " + "START");
    }
 
    public static void HALT(){
        System.out.println("Program has been ended with this command : " + "HALT");
    }

    
 public static void ExecuteGivenCommand(Instruction instruction) throws NoSuchMethodException {
     Method method;

     method = Midterm_20160808047.class.getDeclaredMethod(instruction.getInstructionName());


     try {
          method.invoke(null);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
        }
 
}
public static void ExecuteGivenCommand(InstructionWithValue instruction) throws NoSuchMethodException {
    Method method;

    method = Midterm_20160808047.class.getDeclaredMethod(instruction.getInstructionName(), int.class);



    try {
        method.invoke(null,instruction.getValue());
    } catch (IllegalAccessException ex) {
        Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
        Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
        Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
    }
 
}    public static void LOAD(int parameter){
        AC = parameter;

    }

    public static void LOADM(int parameter){
        AC = memory.get(parameter);

    }

    public static void STORE(int parameter){
        memory.put(parameter, AC);

    }

    public static void CMPM(int parameter){
        if (AC > memory.get(parameter)){
            flag = 1;
        } else if (AC < memory.get(parameter)){
            flag = -1;
        } else{
            flag = 0;
        }

    }

    public static void CJMP(int parameter){
        if (flag >= 0){
            PC = parameter;
        }
    }

    public static void JMP(int parameter){
        PC = parameter;
    }

    public static void ADD(int parameter){
        AC += parameter;

    }

    public static void ADDM(int parameter){
        AC += memory.get(parameter);

    }

    public static void SUBM(int parameter){
        AC -= memory.get(parameter);

    }

    public static void SUB(int parameter){
        AC -= parameter;

    }

    public static void MUL(int parameter){
        AC *= parameter;

    }

    public static void MULM(int parameter){
        AC *= memory.get(parameter);

    }

    public static void DISP(){
        System.out.print(AC);
     //   System.out.println("PC :" + PC);
     //   System.out.println("FLAG :" + flag);

    }

}

