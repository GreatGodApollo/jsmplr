package xyz.brettb.smplr;

import java.util.HashMap;
import java.util.Map;

public class SMPLRInstance {
    private SMPLRClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    SMPLRInstance(SMPLRClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return "<instance " + klass.name + ">";
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        SMPLRFunction method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }
}
