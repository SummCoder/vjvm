package vjvm.interpreter.instruction.stacks;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX_XX extends Instruction {
  private final Function<OperandStack, Slots> popFunc;
  private final BiConsumer<OperandStack, Slots> pushFunc;
  private final String name;
  public static DUPX_XX DUP_X1(ProgramCounter pc, MethodInfo method){
    return new DUPX_XX(s -> s.popSlots(2), OperandStack::pushSlots, "dup_x1");
  }

  public static DUPX_XX DUP_X2(ProgramCounter pc, MethodInfo method){
    return new DUPX_XX(s -> s.popSlots(3), OperandStack::pushSlots, "dup_x2");
  }

  public static DUPX_XX DUP2_X1(ProgramCounter pc, MethodInfo method){
    return new DUPX_XX(s -> s.popSlots(3), OperandStack::pushSlots, "dup2_x1");
  }

  public static DUPX_XX DUP2_X2(ProgramCounter pc, MethodInfo method){
    return new DUPX_XX(s -> s.popSlots(3), OperandStack::pushSlots, "dup2_x2");
  }
  @Override
  public void run(JThread thread){
    OperandStack stack = thread.top().stack();
    Slots value = popFunc.apply(stack);
    Slots value2 = popFunc.apply(stack);
    pushFunc.accept(stack, value);
    pushFunc.accept(stack, value2);
    pushFunc.accept(stack, value);
  }

  @Override
  public String toString(){
    return "dupx_xx";
  }
}
