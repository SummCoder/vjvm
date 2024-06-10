package vjvm.interpreter.instruction.comparisons;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.Objects;
import java.util.function.BiPredicate;
public class IFCOND extends Instruction {
  private final BiPredicate<Integer, Integer> pred;
  private final String name;
  private final int offset;

  private IFCOND(BiPredicate<Integer, Integer> pred, String name, ProgramCounter pc){
    this.pred = pred;
    this.name = name;
    this.offset = pc.short_() - 3;
  }
  public static IFCOND IFEQ(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> Objects.equals(x, 0), "ifeq", pc);
  }

  public static IFCOND IFNE(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> !Objects.equals(x, 0), "ifne", pc);
  }

  public static IFCOND IFLT(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> x < 0, "iflt", pc);
  }

  public static IFCOND IFLE(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> x <= 0, "ifle", pc);
  }

  public static IFCOND IFGE(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> x >= 0, "ifge", pc);
  }

  public static IFCOND IFGT(ProgramCounter pc, MethodInfo method){
    return new IFCOND((x, y) -> x > 0, "ifgt", pc);
  }

  @Override
  public void run(JThread thread){
    OperandStack stack = thread.top().stack();
//    int right = stack.popInt();
    int left = stack.popInt();
    ProgramCounter pc = thread.pc();
    if (pred.test(left, 0)){
      pc.move(offset);
    }
  }

  @Override
  public String toString(){
    return String.format("%s %d", name, offset);
  }
}
