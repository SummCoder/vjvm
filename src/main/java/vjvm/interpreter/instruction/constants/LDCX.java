package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.*;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.*;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {
  private final ConstantPool pool;
  private final int index;
  private final String name;

  public static LDCX LDC(ProgramCounter pc, MethodInfo method) {
    var cp = method.jClass().constantPool();
//    var constant = ((FloatConstant) cp.constant(pc.ubyte())).value();
    return new LDCX(cp, pc.ubyte(), "ldc");
  }

  public static LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
    var cp = method.jClass().constantPool();
//    var constant =  cp.constant(pc.ushort());
    return new LDCX(cp, pc.ushort(), "ldc_w");
  }

  public static LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
    var cp = method.jClass().constantPool();
//    var constant = ((DoubleConstant) cp.constant(pc.ubyte())).value();
    return new LDCX(cp, pc.ushort(), "ldc2_w");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (pool.constant(index).getClass().getSimpleName().equals("IntegerConstant")){
      int value = ((IntegerConstant) pool.constant(index)).value();
      stack.pushInt(value);
    } else if (pool.constant(index).getClass().getSimpleName().equals("FloatConstant")) {
      float value = ((FloatConstant) pool.constant(index)).value();
      stack.pushFloat(value);
    } else if (pool.constant(index).getClass().getSimpleName().equals("DoubleConstant")) {
      double value = ((DoubleConstant) pool.constant(index)).value();
      stack.pushDouble(value);
    } else if (pool.constant(index).getClass().getSimpleName().equals("LongConstant")) {
      long value = ((LongConstant) pool.constant(index)).value();
      stack.pushLong(value);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
