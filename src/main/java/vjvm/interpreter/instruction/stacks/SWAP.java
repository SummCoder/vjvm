package vjvm.interpreter.instruction.stacks;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

public class SWAP extends Instruction {
  public SWAP(ProgramCounter pc, MethodInfo method){

  }

  @Override
  public void run(JThread thread){
    OperandStack stack = thread.top().stack();
    Slots value = stack.popSlots(1);
    Slots value2 = stack.popSlots(1);
    stack.pushSlots(value);
    stack.pushSlots(value2);
  }

  @Override
  public String toString(){
    return "swap";
  }
}
