package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassRef extends Constant{
  private final int index;
  private final JClass self;

  private String name;
  private JClass ref;

  @SneakyThrows
  ClassRef(DataInput input, JClass thisClass){
    index = input.readUnsignedShort();
    this.self = thisClass;
  }

  public ClassRef(String name, JClass thisClass){
    this.name = name;
    this.self = thisClass;
    this.index = 0;
  }

  public JClass value(){
    if (ref != null){
      return ref;
    }

    if (name().equals(self.thisClass().name())){
      ref = self;
    }else {
      ref = self.classLoader().loadClass(Descriptors.of(name()));
    }

    return ref;
  }
  public String name(){
    if (name == null){
      name = ((UTF8Constant) self.constantPool().constant(index)).value();

    }
    return name;
  }

  @Override
  public String toString(){
    return String.format("Class: %s", name());
  }
}
