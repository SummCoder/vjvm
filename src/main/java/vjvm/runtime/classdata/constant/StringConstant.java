package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class StringConstant extends Constant{
  private final int stringIndex;
  private final JClass thisClass;
  private UTF8Constant utf8;

  @SneakyThrows
  StringConstant(DataInput input, JClass thisClass){
    stringIndex = input.readUnsignedShort();
    this.thisClass = thisClass;
  }

  private UTF8Constant utf8(){
    if (utf8 == null){
      utf8 = (UTF8Constant) thisClass.constantPool().constant(stringIndex);

    }
    return utf8;
  }

  @Override
  public String toString(){
    return String.format("String: \"%s\"", StringEscapeUtils.escapeJava(utf8().value()));
  }
}
