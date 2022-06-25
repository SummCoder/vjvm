package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant extends Constant{
  private final float value;

  @SneakyThrows
  FloatConstant(DataInput input){value = input.readFloat();}

  public Float value(){return value;}

  @Override
  public String toString(){return String.format("Float: %a", value);}
}
