package vjvm.runtime;

import java.nio.ByteBuffer;
import java.util.Optional;

import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  private final ByteBuffer buf;
  private final Class<?>[] type;
  public Slots(int slotSize) {
    // TODO: initialize data structures
//    throw new UnimplementedError();
    buf = ByteBuffer.allocate(slotSize * 4);
    type = new Class[slotSize];
  }

  public int int_(int index) {
//     TODO: return the int at index
//    throw new UnimplementedError();
    if (type[index] != Integer.class){
      throw new ClassCastException(String.format("slot %d is not an %s", index, type[index]));
    }
    return buf.getInt(index * 4);
  }

  public void int_(int index, int value) {
    // TODO: set the int at index
//    throw new UnimplementedError();
    type[index] = Integer.class;
    buf.putInt(index * 4, value);
  }

  public long long_(int index) {
    // TODO: return the long at index
//    throw new UnimplementedError();
    if (type[index] != Long.class){
      throw new ClassCastException(String.format("slot %d is not an %s", index, type[index]));
    }
    return buf.getLong(index * 4);
  }

  public void long_(int index, long value) {
    // TODO: set the long at index
//    throw new UnimplementedError();
    type[index] = Long.class;
    type[index + 1] = null;
    buf.putLong(index * 4, value);
  }

  public float float_(int index) {
    // TODO: return the float at index
//    throw new UnimplementedError();
    if (type[index] != Float.class){
      throw new ClassCastException(String.format("slot %d is not an %s", index, type[index]));
    }
    return buf.getFloat(index * 4);
  }

  public void float_(int index, float value) {
    // TODO: set the float at index
//    throw new UnimplementedError();
    type[index] = Float.class;
//    type[index + 1] = null;
    buf.putFloat(index * 4, value);
  }

  public double double_(int index) {
    // TODO: return the double at index
//    throw new UnimplementedError();
    if (type[index] != Double.class){
      throw new ClassCastException(String.format("slot %d is not an %s", index, type[index]));
    }
    return buf.getDouble(index * 4);
  }

  public void double_(int index, double value) {
    // TODO: set the double at index
//    throw new UnimplementedError();
    type[index] = Double.class;
    type[index + 1] = null;
    buf.putDouble(index * 4, value);
  }

  public byte byte_(int index) {
    // TODO: return the byte at index
//    throw new UnimplementedError();
    return (byte) int_(index);
  }

  public void byte_(int index, byte value) {
    // TODO: set the byte at index
//    throw new UnimplementedError();
    int_(index, value);
  }

  public char char_(int index) {
    // TODO: return the char at index
//    throw new UnimplementedError();
    return (char) int_(index);
  }

  public void char_(int index, char value) {
    // TODO: set the char at index
//    throw new UnimplementedError();
    int_(index, value);
  }

  public short short_(int index) {
    // TODO: return the short at index
//    throw new UnimplementedError();
    return (short) int_(index);
  }

  public void short_(int index, short value) {
    // TODO: set the short at index
//    throw new UnimplementedError();
    int_(index, value);
  }

  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    if (type[index] == null)
      return Optional.empty();
    switch (type[index].getSimpleName()){
      case "Integer":
        return Optional.of(int_(index));
      case "Long":
        return Optional.of(long_(index));
      case "Float":
        return Optional.of(float_(index));
      case "Double":
        return Optional.of(double_(index));
      case "Byte":
        return Optional.of(byte_(index));
      case "Short":
        return Optional.of(short_(index));
      default:
        throw new ClassCastException(String.format("unexpected type %s", type[index]));
    }
  }

  public int size() {
    // TODO: return the size in the number of slots
//    throw new UnimplementedError();
    return buf.limit() / 4;
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
//    throw new UnimplementedError();
    if (dest == this && destBegin > begin){
      for (int i = length - 1; i >= 0; --i){
        type[destBegin + i] = type[begin + i];
        buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
      }
    }else {
      for (int i = 0; i < length; ++i){
        dest.type[destBegin + i] = type[begin + i];
        dest.buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
      }
    }
  }
  @Override
  public String toString(){
    StringBuilder bu = new StringBuilder("[");
    for (int i = 0; i < size(); ++i){
      bu.append(int_(i));
      if (i != size() - 1){
        bu.append(", ");
      }
    }
    bu.append(']');
    return bu.toString();
  }
}
