package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP<T> extends Instruction {
  private final String name;

  private final Function<OperandStack, T> popFunc;
  private final BiFunction<T, T, Integer> compareFunc;
  public static LCMP<Long> LCMP(ProgramCounter pc, MethodInfo method){
    return new LCMP<Long>("lcmp", OperandStack::popLong, Long::compare);
  }

  @Override
  public void run(JThread thread){
    OperandStack stack = thread.top().stack();
    T right = popFunc.apply(stack);
    T left = popFunc.apply(stack);
    stack.pushInt(compareFunc.apply(left, right));
  }

  @Override
  public String toString(){
    return name;
  }
}
