package vjvm.interpreter.instruction.store;

public interface TriConsumer<T1, T2, T> {
  void accept(T1 a, T2 b, T c);
}
