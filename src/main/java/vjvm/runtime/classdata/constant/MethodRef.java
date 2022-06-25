package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.MethodInfo;

import java.io.DataInput;

public class MethodRef extends Constant{
//  private final int classIndex;
//  private final int nameAndTypeIndex;
//  private final JClass self;
//  private final boolean interface_;
//
//  private ClassRef classRef;
//  private NameAndTypeConstant nameAndType;
//  private MethodInfo method;
//
//  @SneakyThrows
//  MethodRef(DataInput input,JClass self,boolean interface_){
//    classIndex = input.readUnsignedShort();
//    nameAndTypeIndex = input.readUnsignedShort();
//    this.self = self;
//    this.interface_ = interface_;
//  }
//
//  public JClass jClass(){
//    return classRef().value();
//  }
//
//  private ClassRef classRef(){
//    if (classRef == null)
//      classRef = (ClassRef) self.constantPool().constant(classIndex);
//    return classRef;
//  }
//
//  private NameAndTypeConstant nameAndType(){
//    if (nameAndType == null){
//      nameAndType = (NameAndTypeConstant) self.constantPool().constant(nameAndTypeIndex);
//    }
//    return nameAndType;
//  }
//
//  public MethodInfo value(){
//    if (method != null){
//      return method;
//    }
//
//    var pair = nameAndType().value();
//    method = jClass().findMethod(pair.getLeft(),pair.getRight());
//    if (method == null){
//      throw new Error("No such method");
//    }
//    return method;
//  }
}
