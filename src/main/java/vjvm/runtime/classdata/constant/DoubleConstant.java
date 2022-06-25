package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;

import java.io.DataInput;

public class DoubleConstant extends Constant{
  private final double value;

  @SneakyThrows
  DoubleConstant(DataInput input){value = input.readDouble();}

  public Double value(){return value;}

  @Override
  public String toString(){return String.format("Double: %a", value);}
}
