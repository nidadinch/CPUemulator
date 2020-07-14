 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 *
 * @author nidadinc
 * 23.06.2020
 */

class Instruction {
    private String instructionName;

    public String getInstructionName() {
        return this.instructionName;
    }

    public Instruction(String instructionName) {
        this.instructionName = instructionName;
    }
}

class  InstructionWithValue extends Instruction {
    private int value;
    public int getValue() {
        return value;
    }

    public InstructionWithValue(String instructionName, int value) {
        super(instructionName);
        this.value = value;
    }
}



public class CPUemulator {
    // AC ve AX'i swap eden fonksiyon için swap integer'ı tanımlandı
    public static int swap;
    public static int flag = 0;
    public static int AC;
    public static int PC;
    public static int AX;

    public static String scanned;

    public static int[] memory = new int[256];
    //instructionlar list'e atılacak.
    public static List <Instruction> instructionList = new ArrayList<Instruction>();

    public static Stack <Integer> stack = new Stack();

    //m x n * n x p matrisler için integer değerler tanımlandı
    public static int m;
    public static int n;
    public static int p;

    //matris boyutu kullanıcıdan alınıyor
    static Scanner userInput = new Scanner(System.in);


    public static void main(String[] args) {
        try {

            if(args.length != 1){
                System.out.println("Lutfen dosya yolunu dogru veriniz");
                if(args.length==0){
                    System.out.println("Dosya yolu girilmedi");
                }else
                    System.out.println("Birden fazla parametre girildi");
            }

            //Instructionların yazıldığı dosya okunacak
            String textfile= args[0];
            File file = new File(textfile);
            BufferedReader buffRead = new BufferedReader(new FileReader(file));
            String readline = "";

            // Random matrix oluşturmak için kullanıcıdan alınacak olan veriler için metodlar çağırıldı.
            getMatrixRowCol();
            matrixSize();

            while((readline=buffRead.readLine()) != null){
                scanned=readline;
                String[] splitarray = readline.toUpperCase().split(" ", 3);
                if(splitarray[1].equals("HALT")) {
                    instructionList.add(new Instruction(splitarray[1]));
                    break;
                }
                if (splitarray.length == 2){
                    // ExecuteGivenCommand(Integer.parseInt(splitarray[0]), splitarray[1]);
                    instructionList.add(new Instruction(splitarray[1]));
                }
                else {
                    // ExecuteGivenCommand(splitarray[1],Integer.parseInt(splitarray[2]));
                    instructionList.add(new InstructionWithValue(splitarray[1],Integer.parseInt(splitarray[2])));
                }

            }
            PC = 0;
            Instruction currentInstruction = instructionList.get(PC);
            while (currentInstruction.getInstructionName() != "HALT") {

                currentInstruction = instructionList.get(PC);
                if (currentInstruction instanceof InstructionWithValue) {
                    ExecuteGivenCommand((InstructionWithValue) currentInstruction);
                }else {

                    ExecuteGivenCommand(currentInstruction);
                }if (currentInstruction.getInstructionName().equalsIgnoreCase("HALT")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Midterm_20160808047.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Random oluşturulacak matrisler için satır ve sutun sayıları kullanıcıdan alınıyor
    public static void getMatrixRowCol() {
        System.out.println("Please enter number of rows for Matrix 1 (Integer)");
        m = userInput.nextInt();
        System.out.println("Please enter number of columns for Matrix 1 (Integer)");
        n = userInput.nextInt();
        System.out.println("The number of lines is generated automatically for the matrix...");
        System.out.println("Please enter number of rows for Matrix 2 (Integer)");
        p = userInput.nextInt();
        System.out.println("Creating matrices...");

        matrix1((int) m, (int) n);
        matrix2((int) n, (int) p);
    }


    // Random oluşturulan 1.matrix memory'nin belirli kısımlarına yerleştiriliyor
    // Birinci matris Memory'nin yandaki kısımlarında tutuluyor:  satır değeri-sütun değeri - > 11-12-21-22-31-32..
    public static void matrix1(int m, int n) {
        for (int i = 0; i < (m * n); i++) {
            int counter = 0;
            int z = 1;
            int AddInt = 10;
            while (counter < m * n) {
                counter++;
                if (counter % n == 0) {
                    memory[AddInt + z ] =  1 + (int) (Math.random() * 10);
                    AddInt += 10;
                    z = 0;

                } else {
                    memory[AddInt + z ] =  1 + (int) (Math.random() * 10);
                    z++;
                }
            }

        }

    }
    // Random oluşturulan 2.matrix memory'nin belirli kısımlarına yerleştiriliyor
    // İkinci matris Memory'nin yandaki kısımlarında tutuluyor:  satır değeri-sütun değeri - > 211-212-221-222-231-232..
    public static void matrix2(int n, int p) {
        for (int i = 0; i < (n * p); i++) {

            int counter = 0;
            int z = 1;
            int addInt = 210;
            while (counter < m * p) {
                counter++;
                if (counter % p == 0) {
                    memory[addInt + z ] =  1 + (int) (Math.random() * 10);
                    addInt += 10;
                    z = 0;
                } else {
                    memory[addInt + z ] =  1 + (int) (Math.random() * 10);
                    z++;
                }
            }

        }

    }


    //Programın farklı boyutlarda çalışabilmesi adına m,n,p değerleri Memory'e atılıyor.
    public static void matrixSize(){

        memory[140] = m;
        memory[141] = n;
        memory[142] = n;
        memory[143] = p;

        memory[150] = 1;
        memory[151] = 1;
        memory[152] = 1;
    }

    public static void START(){
        System.out.println("Program has been started with this command : " + "START");
        PC ++;
    }

    public static void HALT(){

        System.out.println( "Program has been ended with this command : " + "HALT");
        PC ++;
    }

    /* Her metod için ayrı ayrı switch-case ya da while loop yazmamak adına Java Reflection kullandım,
    Bu sayede yaklaşık olarak 30 satır koddan kar ettim */

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

    }

    public static void LOAD(int parameter){
        AC = parameter;
        PC ++;
    }

    public static void LOADM(int parameter){
        AC = memory[parameter];
        PC ++;
    }

    public static void STORE(int parameter){
        memory[parameter] = AC;
        PC ++;
    }

    public static void CMPM(int parameter){
        if (AC > memory[parameter]){
            flag = 1;
        } else if (AC < memory[parameter]){
            flag = -1;
        } else if(AC == memory[parameter])
            flag = 0;
        PC++;

    }

    public static void CJMP(int parameter){
        if (flag >0){
            PC = parameter;
        }
        else PC ++;
    }

    public static void JMP(int parameter){
        PC = parameter;

    }

    public static void ADD(int parameter){
        AC += parameter;
        PC ++;
    }

    public static void ADDM(int parameter){
        AC += memory[parameter];
        PC ++;
    }

    public static void SUBM(int parameter){
        AC -= memory[parameter];
        PC ++;
    }

    public static void SUB(int parameter){
        AC -= parameter;
        PC ++;
    }

    public static void MUL(int parameter){
        AC = AC * parameter;
        PC ++;
    }

    public static void MULM(int parameter){
        AC  = AC * memory[parameter];
        PC ++;
    }

    public static void DISP(){
        System.out.print(AC + " ");
        PC ++;
    }

    public static void PUSH(int parameter) {

        stack.push(parameter);
        PC ++;
    }

    public static void POP() {

        AC = stack.pop();
        PC ++;
    }

    public static void RETURN() {

        PC = stack.pop();
    }

    public static void DASC() {

        char asciiOfAC = (char)AC;
        System.out.print(asciiOfAC);
        PC ++;
    }

    public static void LOADI() {

        AC = memory[AX];
        PC ++;
    }

    public static void STOREI() {

        memory[AX] = AC;
        PC ++;
    }

    public static void SWAP() {
        swap = AC;
        AC = AX;
        AX = swap;
        PC ++;
    }


}

