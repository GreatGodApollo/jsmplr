package xyz.brettb.smplr;

import java.util.List;
import java.util.Map;

class SMPLRClass implements SMPLRCallable {
    final String name;
    final SMPLRClass superclass;
    private final Map<String, SMPLRFunction> methods;

    SMPLRClass(String name, SMPLRClass superclass, Map<String, SMPLRFunction> methods) {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    SMPLRFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        SMPLRInstance instance = new SMPLRInstance(this);
        SMPLRFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        SMPLRFunction initalizer = findMethod("init");
        if (initalizer == null) return 0;
        return initalizer.arity();
    }

    @Override
    public String toString() {
        return "<class " + name + ">";
    }
}
